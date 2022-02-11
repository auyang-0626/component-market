package com.yang.component.market.manager.entity.module;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author yangguangyue
 * Created on 2022-01-05
 */
@Data
@TableName("ad_module")
public class ModulePojo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long appId;
    private String moduleName;
    private String moduleNameCn;
    private Long moduleOwner;
    private String moduleDesc;
}
