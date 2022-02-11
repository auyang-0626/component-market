package com.yang.component.market.manager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.component.market.manager.dao.ModuleDao;
import com.yang.component.market.manager.dto.module.ModuleDetailResponse;
import com.yang.component.market.manager.dto.module.ModuleResponse;
import com.yang.component.market.manager.dto.module.ModuleSaveParam;
import com.yang.component.market.manager.entity.module.ModulePojo;

/**
 * @author yangguangyue
 * Created on 2022-01-05
 */
@Service
public class ModuleService {

    @Autowired
    private ModuleDao moduleDao;

    public Long save(ModuleSaveParam param) {

        ModulePojo entity = param.convert();
        if (param.getId() != null) {
            moduleDao.updateById(entity);
        } else {
            moduleDao.insert(entity);
        }
        return entity.getId();
    }


    public List<ModuleResponse> listAllByApp(Long appId) {
        return moduleDao.listAllByApp(appId).stream()
                .map(ModuleResponse::build)
                .collect(Collectors.toList());
    }

    public ModuleDetailResponse detail(Long id) {
        return ModuleDetailResponse.build(moduleDao.selectById(id));
    }
}
