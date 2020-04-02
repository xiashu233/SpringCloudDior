package com.dior.cloudback.controller;

import com.alibaba.fastjson.JSON;
import com.dior.bean.CommonResult;
import com.dior.bean.PmsBaseCatalog1;
import com.dior.bean.PmsProductInfo;
import com.dior.cloudback.util.QiniuCloudUtil;
import com.dior.service.CatalogService;
import com.dior.service.ProcService;
import com.qiniu.util.Json;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class procController {

    @Autowired
    CatalogService catalogService;
    @Autowired
    ProcService procService;

    @RequestMapping(value="uploadImg", method= RequestMethod.POST)
    public CommonResult uploadImg(MultipartFile image) {

        if (image.isEmpty()) {
            return new CommonResult(400,"文件为空，请重新上传");
        }

        try {
            String url = "";
            byte[] bytes = image.getBytes();
            String imageName = UUID.randomUUID().toString();

            QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
                url = qiniuUtil.put64image(bytes, imageName);

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(url);
            return new CommonResult(200,"上传成功",url);
        } catch (IOException e) {
            return new CommonResult(500,"上传文件时发生异常，请稍后重试");
        }
    }


    @PostMapping("getAllProcByCatalog3/{catalog3}")
    public CommonResult getAllProcByCatalog3(@PathVariable("catalog3")String catalog3){
        List<PmsProductInfo> pmsProductInfoList = procService.getAllProcByCatalog3(catalog3);
        return new CommonResult(200,"获取商品信息成功",pmsProductInfoList);
    }

    @RequestMapping("saveProc")
    public CommonResult saveProc(PmsProductInfo pmsProductInfo, @RequestParam("imgList")String imgList,
                                 @RequestParam("defaultImgIndex")int defaultImgIndex,
                                 @RequestParam("attrMap")String attrMapJson,ModelMap map){

        String procId = procService.addProcInfo(pmsProductInfo);

        if (StringUtils.isNotBlank(procId)){
            pmsProductInfo.setId(procId);
            procService.addProcImage(procId+"",imgList,defaultImgIndex);

            Map<String,String> attrMap = new HashMap();
            if (StringUtils.isNotBlank(attrMapJson)){

                procService.addProcAttr(procId,attrMapJson);
            }

            return new CommonResult(200,"新增商品信息成功");
        }

        return new CommonResult(500,"新增商品信息失败");
    }


    @PostMapping("deleteProc/{id}")
    public CommonResult deleteProc(@PathVariable("id")String id){
        // 返回删除总数
        int delCount = procService.deleteProc(id);
        if (delCount > 0){
            return new CommonResult(200,"删除商品信息成功");
        }
        return new CommonResult(500,"删除商品信息失败");
    }

    @PostMapping("getProcById/{id}")
    public CommonResult getProcById(@PathVariable("id")String id){
        //
        PmsProductInfo pmsProductInfo = procService.getProcById(id);
        if (pmsProductInfo != null){
            return new CommonResult(200,"获取商品信息成功",pmsProductInfo);
        }
        return new CommonResult(500,"获取商品信息失败");
    }


}
