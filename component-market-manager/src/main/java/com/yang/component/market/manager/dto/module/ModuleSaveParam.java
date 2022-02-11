package com.yang.component.market.manager.dto.module;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.yang.component.market.manager.entity.module.ModulePojo;
import com.yang.component.market.manager.util.WebContext;

import lombok.Data;

/**
 * @author yangguangyue
 * Created on 2022-01-05
 */

@Data
public class ModuleSaveParam {

    private Long id;
    @NotNull(message = "appId不能为空！")
    private Long appId;
    @Pattern(regexp = "^[0-9a-zA-Z_]{2,50}$", message = "应用名称只能是字母数字下划线组成,长度为2-50")
    @NotNull(message = "模块名称不能为空")
    private String moduleName;
    @Size(min = 2, max = 50, message = "模块中文名长度必须在2-50之间")
    @NotNull(message = "模块中文名称不能为空")
    private String moduleNameCn;
    @NotNull(message = "模块描述不能为空")
    private String moduleDesc;

    public ModulePojo convert() {
        ModulePojo pojo = new ModulePojo();
        BeanUtils.copyProperties(this, pojo);
        pojo.setModuleOwner(WebContext.getLoginUser().getId());
        return pojo;
    }
}
