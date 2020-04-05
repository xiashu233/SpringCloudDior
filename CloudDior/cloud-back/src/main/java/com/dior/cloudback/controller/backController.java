package com.dior.cloudback.controller;

import com.dior.bean.PmsBaseCatalog1;
import com.dior.bean.PmsProductAds;
import com.dior.service.AdsService;
import com.dior.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class backController {

    @Autowired
    CatalogService catalogService;
    @Autowired
    AdsService adsService;

    @RequestMapping("index")
    public String toIndex(){
        return "Index";
    }

    @RequestMapping("mainFrame")
    public String testFenli(){
        return "mainFrame";
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

    @RequestMapping("adManage")
    public String adManage(ModelMap map){
        List<PmsProductAds> pmsProductAds = adsService.getAllCatalog1Ads();
        map.put("pmsProductAds",pmsProductAds);
        return "adManage";
    }






}
