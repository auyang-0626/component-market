package com.yang.component.market.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.component.market.manager.entity.model.ModelEntity;

/**
 * @author yangguangyue
 * Created on 2022-01-18
 */
@Mapper
public interface ModelDao extends BaseMapper<ModelEntity> {

    String MODULE_ID = "module_id";


    default List<ModelEntity> selectByModuleId(Long moduleId) {
        return selectList(
                new QueryWrapper<ModelEntity>()
                        .eq(MODULE_ID, moduleId)
        );
    }
}
