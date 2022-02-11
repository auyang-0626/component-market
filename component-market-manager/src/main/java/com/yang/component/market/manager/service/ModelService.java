package com.yang.component.market.manager.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yang.component.market.manager.dao.ModelDao;
import com.yang.component.market.manager.dto.model.ModelAttrsParam;
import com.yang.component.market.manager.dto.model.ModelBaseInfoParam;
import com.yang.component.market.manager.dto.model.ModelGroupView;
import com.yang.component.market.manager.dto.model.ModelView;
import com.yang.component.market.manager.entity.model.ModelEntity;
import com.yang.component.market.manager.kind.ModelKind;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangguangyue
 * Created on 2022-01-16
 */
@Slf4j
@Service
public class ModelService {

    @Autowired
    private ModelDao modelDao;


    public Long saveBaseInfo(ModelBaseInfoParam param) {
        if (param.getId() != null) {
            modelDao.updateById(param);
        } else {
            modelDao.insert(param);
        }
        return param.getId();
    }

    public ModelView detail(Long id) {
        ModelEntity model = modelDao.selectById(id);
        Assert.notNull(model, "找不到对应的实体");
        return ModelView.build(model);
    }

    public List<ModelGroupView> modelGroups(Long moduleId) {
        List<ModelEntity> entities = modelDao.selectByModuleId(moduleId);

        return Arrays.stream(ModelKind.values())
                .map(kind -> ModelGroupView.builder()
                        .groupKey(kind.getCode())
                        .groupName(kind.getDesc())
                        .models(entities.stream().filter(e -> e.getModelKind() == kind).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }


    public Long saveAttrs(List<ModelAttrsParam> params) {

        return null;
    }
}
