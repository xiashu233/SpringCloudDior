package com.dior.cloudback.controller;

import com.dior.bean.CommonResult;
import com.dior.bean.PmsBaseCatalog1;
import com.dior.bean.PmsProductInfo;
import com.dior.cloudback.util.QiniuCloudUtil;
import com.dior.service.CatalogService;
import com.dior.service.ProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.List;
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

    @RequestMapping("saveProc")
    public CommonResult saveProc(PmsProductInfo pmsProductInfo, @RequestParam("imgList")String imgList, ModelMap map){
        int procId = procService.addProcInfo(pmsProductInfo);
        if (procId>0){
            pmsProductInfo.setId(procId + "");
            procService.addProcImage(pmsProductInfo.getId(),imgList);

            return new CommonResult(200,"新增商品信息成功");
        }

        return new CommonResult(500,"新增商品信息失败");
    }
}
