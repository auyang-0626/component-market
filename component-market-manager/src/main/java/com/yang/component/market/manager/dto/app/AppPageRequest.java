package com.yang.component.market.manager.dto.app;

import java.io.Serializable;

import com.yang.component.market.manager.dto.PageRequest;
import com.yang.component.market.manager.kind.AppKind;

import lombok.Data;

@Data
public class AppPageRequest implements Serializable {

    private String appName;

    private AppKind appKind;

    private String appNameCn;

    private PageRequest page;
}
