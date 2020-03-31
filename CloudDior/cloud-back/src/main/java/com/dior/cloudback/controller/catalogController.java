package com.dior.cloudback.controller;

import com.dior.bean.CommonResult;
import com.dior.bean.PmsBaseCatalog2;
import com.dior.bean.PmsBaseCatalog3;
import com.dior.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
public class catalogController {
    @Autowired
    CatalogService catalogService;

    @PostMapping("addCatalog")
    public CommonResult addCatalog(@RequestParam("catalog1")int catalog1, @RequestParam("catalog2")int catalog2,
                                   @RequestParam("catalog1txt")String catalog1txt, @RequestParam("catalog2txt")String catalog2txt,
                                   @RequestParam("catalog3txt")String catalog3txt){

        if (catalog1 == -1 && !StringUtils.isEmpty(catalog1txt)){
            catalog1 = catalogService.addCatalog1(catalog1txt);
        }
        if (catalog2 == -1 && catalog1 > 0 && !StringUtils.isEmpty(catalog2txt)){
            catalog2 = catalogService.addCatalog2(catalog1,catalog2txt);
        }
        int catalog3 = 0;
        if (catalog2 > 0){
            catalog3 = catalogService.addCatalog3(catalog2,catalog3txt);
        }
        if (catalog3 != -1 && !StringUtils.isEmpty(catalog3txt)){
            return new CommonResult(200,"添加数据成功");
        }
        return new CommonResult(500,"添加数据失败");
    }

    @PostMapping("/getcatalog2/{id}")
    public CommonResult getcatalog2(@PathVariable("id") String id){
        List<PmsBaseCatalog2> catalog2List = catalogService.getAllCatalogTwo(id);
        if (catalog2List.size() > 0){
            return new CommonResult(200,"获得了该id下面的所有二级分类",catalog2List);
        }
        return new CommonResult(500,"未获取到该id下面的数据");
    }

    @PostMapping("/getcatalog3/{id}")
    public CommonResult getcatalog3(@PathVariable("id") String id){
        List<PmsBaseCatalog3> catalog3List = catalogService.getAllCatalogThree(id);
        if (catalog3List.size() > 0){
            return new CommonResult(200,"获得了该id下面的所有三级分类",catalog3List);
        }
        return new CommonResult(500,"未获取到该id下面的数据");
    }
}
