package com.yang.component.market.manager.dto.model;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yang.component.market.manager.entity.model.ModelAttrEntity;
import com.yang.component.market.manager.entity.model.ModelEntity;
import com.yang.component.market.manager.entity.model.ModelRelationEntity;

import lombok.Data;

/**
 * @author yangguangyue
 * Created on 2022-01-18
 */
@Data
public class ModelView extends ModelEntity {

    private List<ModelAttrEntity> attrs;

    private List<ModelRelationEntity> relations;

    public static ModelView build(ModelEntity model) {
        ModelView view = new ModelView();
        BeanUtils.copyProperties(model, view);
        return view;
    }
}
