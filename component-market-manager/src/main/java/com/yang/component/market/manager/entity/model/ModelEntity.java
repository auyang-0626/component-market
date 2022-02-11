package com.yang.component.market.manager.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yang.component.market.manager.kind.ModelKind;

import lombok.Data;

/**
 * @author yangguangyue
 * Created on 2022-01-14
 */
@Data
@TableName("ad_model")
public class ModelEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long moduleId;
    private ModelKind modelKind;
    private String modelName;
    private String modelNameCn;
    private String modelDesc;
}
