package com.dior.cloudback.controller;

import com.dior.bean.CommonResult;
import com.dior.bean.PmsProductAds;
import com.dior.service.AdsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class adsController {
    @Autowired
    AdsService adsService;

    @PostMapping("/saveBanner")
    public CommonResult saveBanner(PmsProductAds pmsProductAds){
        String adsId = "";
        if (StringUtils.isNotBlank(pmsProductAds.getId())){
           int changeSize = adsService.changeBanner(pmsProductAds);
           if (changeSize > 0){
               return new CommonResult(200,"修改Banner成功！");
           }
            return new CommonResult(500,"修改Banner失败！");
        }else{
            adsId = adsService.saveBanner(pmsProductAds);
            if (StringUtils.isNotBlank(adsId)){
                return new CommonResult(200,"保存Banner成功！",adsId);
            }
            return new CommonResult(500,"保存Banner失败！");
        }

    }

    @PostMapping("/delBanner/{id}")
    public CommonResult delBanner(@PathVariable("id")String id){
       if (adsService.delBannerById(id)>0){
           return new CommonResult(200,"保存Banner成功！");
       }
        return new CommonResult(500,"保存Banner失败！");
    }
}
