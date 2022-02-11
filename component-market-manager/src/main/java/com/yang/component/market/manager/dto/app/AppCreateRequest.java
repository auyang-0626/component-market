package com.yang.component.market.manager.dto.app;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.yang.component.market.manager.entity.app.AppPojo;
import com.yang.component.market.manager.kind.AppKind;
import com.yang.component.market.manager.util.WebContext;

import lombok.Data;

@Data
public class AppCreateRequest {

    private Long id;
    /**
     * 应用名称
     */
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z_]{2,50}$", message = "应用名称只能是字母数字下划线组成,长度为2-50")
    private String appName;

    /**
     * 类别
     */
    @NotNull
    private AppKind appKind;
    /**
     * 应用名称
     */
    @NotBlank
    private String appNameCn;

    /**
     * 描述信息
     */
    @NotBlank
    private String appDesc;

    /**
     * logo
     */
    @NotBlank
    private String logoUrl;

    public AppPojo toPojo() {
        AppPojo pojo = new AppPojo();
        pojo.setId(this.getId());
        pojo.setAppName(this.getAppName());
        pojo.setAppNameCn(this.getAppNameCn());
        pojo.setAppKind(this.getAppKind());
        pojo.setAppDesc(this.getAppDesc());
        pojo.setAppLogoUrl(this.getLogoUrl());
        pojo.setAppOwner(WebContext.getLoginUser().getId());
        return pojo;
    }
}
