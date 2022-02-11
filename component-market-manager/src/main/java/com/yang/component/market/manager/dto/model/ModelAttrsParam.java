package com.yang.component.market.manager.dto.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.yang.component.market.manager.entity.model.ModelAttrEntity;

import lombok.Data;

/**
 * @author yangguangyue
 * Created on 2022-01-16
 */
@Data
public class ModelAttrsParam {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotNull(message = "属性不能为空")
    private List<ModelAttrEntity> attrs;
}
