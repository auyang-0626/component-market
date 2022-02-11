package com.yang.component.market.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.component.market.manager.entity.module.ModulePojo;

/**
 * @author yangguangyue
 * Created on 2022-01-05
 */
@Mapper
public interface ModuleDao extends BaseMapper<ModulePojo> {

    String KEY_APP_ID = "app_id";

    default List<ModulePojo> listAllByApp(Long appId) {
        return selectList(
                new QueryWrapper<ModulePojo>()
                        .eq(KEY_APP_ID, appId)
        );
    }
}
