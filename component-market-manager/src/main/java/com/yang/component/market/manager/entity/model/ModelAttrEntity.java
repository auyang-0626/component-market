package com.yang.component.market.manager.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yang.component.market.manager.kind.AttrKind;

import lombok.Data;

/**
 * @author yangguangyue
 * Created on 2022-01-14
 */
@Data
@TableName("ad_model")
public class ModelAttrEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long modelId;

    private String attrName;
    private String attrNameCn;

    private AttrKind attrKind;
    private String attrValue;
    private boolean pk;
    private String restrictConfig;
    private String attrDesc;
}
