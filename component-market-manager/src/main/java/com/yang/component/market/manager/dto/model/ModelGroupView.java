package com.yang.component.market.manager.dto.model;

import java.util.List;

import com.yang.component.market.manager.entity.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangguangyue
 * Created on 2022-01-18
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModelGroupView {

    private String groupKey;

    private String groupName;

    private List<ModelEntity> models;
}
