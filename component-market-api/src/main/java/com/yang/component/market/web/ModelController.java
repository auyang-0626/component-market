package com.yang.component.market.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yang.component.market.manager.dto.Result;
import com.yang.component.market.manager.dto.model.ModelAttrsParam;
import com.yang.component.market.manager.dto.model.ModelBaseInfoParam;
import com.yang.component.market.manager.dto.model.ModelGroupView;
import com.yang.component.market.manager.dto.model.ModelView;
import com.yang.component.market.manager.service.ModelService;

/**
 * @author yangguangyue
 * Created on 2022-01-16
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @PostMapping("saveBaseInfo")
    public Result<Long> saveBaseInfo(@RequestBody ModelBaseInfoParam param) {
        return Result.ok(modelService.saveBaseInfo(param));
    }

    @PostMapping("saveAttrs")
    public Result<Long> saveAttrs(List<ModelAttrsParam> params) {
        return Result.ok(modelService.saveAttrs(params));
    }

    @GetMapping("detail")
    public Result<ModelView> detail(@RequestParam("id") Long id) {
        return Result.ok(modelService.detail(id));
    }

    @GetMapping("groups")
    public Result<List<ModelGroupView>> modelGroups(@RequestParam("moduleId") Long moduleId) {
        return Result.ok(modelService.modelGroups(moduleId));
    }
}
