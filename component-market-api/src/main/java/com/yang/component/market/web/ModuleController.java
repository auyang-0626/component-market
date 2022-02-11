package com.yang.component.market.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yang.component.market.manager.dto.Result;
import com.yang.component.market.manager.dto.module.ModuleDetailResponse;
import com.yang.component.market.manager.dto.module.ModuleResponse;
import com.yang.component.market.manager.dto.module.ModuleSaveParam;
import com.yang.component.market.manager.service.ModuleService;

/**
 * @author yangguangyue
 * Created on 2022-01-05
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("save")
    public Result<Long> save(@RequestBody @Valid ModuleSaveParam param) {
        return Result.ok(moduleService.save(param));
    }

    @GetMapping("listByApp")
    public Result<List<ModuleResponse>> listAllByApp(@RequestParam("appId") Long appId) {
        return Result.ok(moduleService.listAllByApp(appId));
    }

    @GetMapping("detail")
    public Result<ModuleDetailResponse> detail(@RequestParam("id") Long id) {
        return Result.ok(moduleService.detail(id));
    }
}
