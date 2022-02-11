package com.yang.component.market.manager.entity.app;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yang.component.market.manager.kind.AppKind;

import lombok.Data;

@Data
@TableName("ad_app")
public class AppPojo implements Serializable {

    private static final long serialVersionUID = 4760989154173502379L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 应用类型
     */
    private AppKind appKind;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用名称
     */
    private String appNameCn;

    /**
     * 应用名称
     */
    private String appLogoUrl;

    /**
     * 系统负责人
     */
    private Long appOwner;

    /**
     * 描述信息
     */
    private String appDesc;

}
