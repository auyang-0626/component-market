package com.yang.component.market.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yang.component.market.manager.dto.Result;
import com.yang.component.market.manager.dto.app.AppCreateRequest;
import com.yang.component.market.manager.dto.app.AppDetailResponse;
import com.yang.component.market.manager.dto.app.AppPageRequest;
import com.yang.component.market.manager.dto.app.AppPageResponse;
import com.yang.component.market.manager.service.AppService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@Tag(name = "应用管理接口文档", description = "用户相关CRUD接口")
@RequestMapping("app")
@RestController
public class AppController {

    @Autowired
    private AppService appService;

    @Operation(summary = "保存应用")
    @PostMapping("save")
    public Result<Long> save(@RequestBody @Valid AppCreateRequest request) {
        return Result.ok(appService.save(request));
    }

    @Operation(summary = "返回当前用户所有有权限的应用")
    @PostMapping("page")
    public Result<IPage<AppPageResponse>> page(@RequestBody AppPageRequest pageRequest) {
        return Result.ok(appService.page(pageRequest));
    }

    @GetMapping("detail")
    public Result<AppDetailResponse> detail(@RequestParam("id") Long id) {
        return Result.ok(appService.detail(id));
    }

    @DeleteMapping("delete")
    public Result<Boolean> delete(@RequestParam("id") Long id) {
        appService.delete(id);
        return Result.ok(true);
    }
}
