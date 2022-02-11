package com.yang.component.market.manager.entity.model;

/**
 * @author yangguangyue
 * Created on 2022-01-16
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yang.component.market.manager.kind.RelationKind;

import lombok.Data;

@Data
@TableName("ad_entity_relation")
public class ModelRelationEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private RelationKind relationKind;

    private Long fromModelId;
    private Long fromAttrId;

    private Long toModelId;
    private Long toAttrId;
}
