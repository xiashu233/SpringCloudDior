package com.dior.cloudback.controller;

import com.alibaba.fastjson.JSON;
import com.dior.bean.CommonResult;
import com.dior.bean.PmsBaseCatalog1;
import com.dior.bean.PmsProductInfo;
//import com.dior.cloudback.util.QiniuCloudUtil;
import com.dior.cloudback.util.QiniuCloudUtil;
//import com.dior.cloudback.util.QiniuMediaUtilService;
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
@CrossOrigin
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

            //QiniuMediaUtilService qiniuMediaUtilService = new QiniuMediaUtilService();
            QiniuCloudUtil qiniuCloudUtil = new QiniuCloudUtil();
            try {
                //使用base64方式上传到七牛云
//                boolean b = qiniuMediaUtilService.uploadFile(image.getName(), image.getInputStream());
//                if (b){
//                    url = qiniuMediaUtilService.getFileResourceUrl(image.getName());
//                }
                url = qiniuCloudUtil.put64image(bytes,imageName);

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(url);
            return new CommonResult(200,"上传成功",url);
        } catch (IOException e) {
            return new CommonResult(500,"上传文件时发生异常，请稍后重试");
        }
    }

//    @PostMapping(value="uploadFile")
//    public CommonResult uploadFile(MultipartFile file) {
//
//        if (file.isEmpty()) {
//            return new CommonResult(400,"文件为空，请重新上传");
//        }
//
//        try {
//            String url = "";
//            byte[] bytes = file.getBytes();
//            String imageName = UUID.randomUUID().toString();
//
//            //QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
//            QiniuMediaUtilService qiniuMediaUtilService = new QiniuMediaUtilService();
//            try {
//                //使用base64方式上传到七牛云
//                boolean b = qiniuMediaUtilService.uploadFile(file.getName(), file.getInputStream());
//                if (b){
//                    url = qiniuMediaUtilService.getFileResourceUrl(file.getName());
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println(url);
//            return new CommonResult(200,"上传成功",url);
//        } catch (IOException e) {
//            return new CommonResult(500,"上传文件时发生异常，请稍后重试");
//        }
//    }


    @PostMapping("getAllProcByCatalog3/{catalog3}")
    public CommonResult getAllProcByCatalog3(@PathVariable("catalog3")String catalog3){
        List<PmsProductInfo> pmsProductInfoList = procService.getAllProcByCatalog3(catalog3);
        return new CommonResult(200,"获取商品信息成功",pmsProductInfoList);
    }

    @RequestMapping("saveProc")
    public CommonResult saveProc(PmsProductInfo pmsProductInfo, @RequestParam("imgList")String imgList,
                                 @RequestParam("defaultImgIndex")int defaultImgIndex,
                                 @RequestParam("attrMap")String attrMapJson,ModelMap map){

        if (pmsProductInfo.getId().equals("-1")){
            // 添加商品
            String procId = procService.addProcInfo(pmsProductInfo);

            if (StringUtils.isNotBlank(procId)){
                pmsProductInfo.setId(procId);
                procService.addProcImage(procId,imgList,defaultImgIndex);

                if (StringUtils.isNotBlank(attrMapJson)){
                    procService.addProcAttr(procId,attrMapJson);
                }

                return new CommonResult(200,"新增商品信息成功");
            }

            return new CommonResult(500,"新增商品信息失败");
        }else{
            // 修改商品
            procService.changeProcInfo(pmsProductInfo);
            procService.deleteProcImageByProcId(pmsProductInfo.getId());
            procService.addProcImage(pmsProductInfo.getId(),imgList,defaultImgIndex);
            procService.deleteProcAttrValueByProcId(pmsProductInfo.getId());
            if (StringUtils.isNotBlank(attrMapJson)){
                procService.addProcAttr(pmsProductInfo.getId(),attrMapJson);
            }
            return new CommonResult(200,"修改商品信息成功");
        }

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

    @PostMapping("search/{key}")
    public CommonResult search(@PathVariable("key")String key){
        //
        List<PmsProductInfo> pmsProductInfo = procService.getProcBySearchKey(key);
        if (pmsProductInfo != null){
            return new CommonResult(200,"获取商品信息成功",pmsProductInfo);
        }
        return new CommonResult(500,"获取商品信息失败");
    }



}
