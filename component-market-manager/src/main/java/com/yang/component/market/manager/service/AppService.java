package com.yang.component.market.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang.component.market.manager.dao.AppDao;
import com.yang.component.market.manager.dto.app.AppCreateRequest;
import com.yang.component.market.manager.dto.app.AppDetailResponse;
import com.yang.component.market.manager.dto.app.AppPageRequest;
import com.yang.component.market.manager.dto.app.AppPageResponse;
import com.yang.component.market.manager.entity.app.AppPojo;

@Service
public class AppService {

    @Autowired
    private AppDao appDao;

    public Long save(AppCreateRequest request) {
        AppPojo app = request.toPojo();
        if (app.getId() != null) {
            appDao.updateById(app);
        } else {
            appDao.insert(app);
        }

        return app.getId();
    }

    public IPage<AppPageResponse> page(AppPageRequest param) {

        IPage<AppPageResponse> page = new Page<>(param.getPage().getCurrent(), param.getPage().getSize());

        IPage<AppPageResponse> data = appDao.page(page, param);
        return data;
    }

    public AppDetailResponse detail(Long id) {
        AppPojo app = appDao.selectById(id);
        Assert.notNull(app, "找不到对应的实体");
        return AppDetailResponse.build(app);
    }

    public void delete(Long id) {
        appDao.deleteById(id);
    }
}
