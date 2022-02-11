package com.yang.component.market.manager.dto.module;

import org.springframework.beans.BeanUtils;

import com.yang.component.market.manager.entity.module.ModulePojo;

import lombok.Data;

/**
 * @author yangguangyue
 * Created on 2022-01-05
 */
@Data
public class ModuleDetailResponse extends ModulePojo {


    public static ModuleDetailResponse build(ModulePojo modulePojo) {

        ModuleDetailResponse response = new ModuleDetailResponse();
        BeanUtils.copyProperties(modulePojo, response);
        return response;
    }
}
