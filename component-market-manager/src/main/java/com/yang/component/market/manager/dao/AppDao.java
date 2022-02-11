package com.yang.component.market.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yang.component.market.manager.dto.app.AppPageRequest;
import com.yang.component.market.manager.dto.app.AppPageResponse;
import com.yang.component.market.manager.entity.app.AppPojo;

/**
 * @author yangguangyue
 * Created on 2021-12-30
 */
@Mapper
public interface AppDao extends BaseMapper<AppPojo> {

    IPage<AppPageResponse> page(
            IPage<AppPageResponse> page,
            @Param("e") AppPageRequest param);
}
