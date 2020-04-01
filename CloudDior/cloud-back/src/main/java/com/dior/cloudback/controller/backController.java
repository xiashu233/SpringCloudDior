package com.dior.cloudback.controller;

import com.dior.bean.CommonResult;
import com.dior.bean.PmsBaseCatalog1;
import com.dior.bean.PmsBaseCatalog2;
import com.dior.bean.PmsBaseCatalog3;
import com.dior.cloudback.util.QiniuCloudUtil;
import com.dior.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class backController {

    @Autowired
    CatalogService catalogService;

    @RequestMapping("index")
    public String toIndex(){
        return "Index";
    }

    @RequestMapping("testFenli")
    public String testFenli(){
        return "testFenli";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("catalogManage")
    public String catalogManage(ModelMap map){
        List<PmsBaseCatalog1> catalog1List = catalogService.getAllCatalogOne();
        map.put("catalog1List",catalog1List);
        return "catalogManage";
    }

    @RequestMapping("productManage")
    public String productManage(ModelMap map){
        List<PmsBaseCatalog1> catalog1List = catalogService.getAllCatalogOne();
        map.put("catalog1List",catalog1List);
        return "productManage";
    }






}
