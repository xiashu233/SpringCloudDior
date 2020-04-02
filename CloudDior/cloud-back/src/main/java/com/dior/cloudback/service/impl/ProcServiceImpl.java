package com.dior.cloudback.service.impl;

import com.alibaba.fastjson.JSON;
import com.dior.bean.PmsProductImage;
import com.dior.bean.PmsProductInfo;
import com.dior.bean.PmsProductSaleAttrValue;
import com.dior.cloudback.mapper.PmsProductImageMapper;
import com.dior.cloudback.mapper.PmsProductInfoMapper;
import com.dior.cloudback.mapper.PmsProductSaleAttrValueMapper;
import com.dior.service.ProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProcServiceImpl implements ProcService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public String addProcInfo(PmsProductInfo pmsProductInfo) {
        pmsProductInfoMapper.insertSelective(pmsProductInfo);
        return pmsProductInfo.getId();
    }

    @Override
    public void addProcImage(String productId, String imgList,int defaultImgIndex) {
        String[] imgs = imgList.split(",");
        for (int i = 1; i < imgs.length; i++) {
            PmsProductImage pmsProductImage = new PmsProductImage();
            if (i == (defaultImgIndex + 1)){
                pmsProductImage.setIsDefault("1");
            }
            pmsProductImage.setProductId(productId);
            pmsProductImage.setImgUrl(imgs[i]);
            pmsProductImage.setImgName(imgs[i]);
            pmsProductImageMapper.insertSelective(pmsProductImage);
        }

    }

    @Override
    public void addProcAttr(String procId,String attrMapJson) {
       Map<String,String> attrMap = JSON.parseObject(attrMapJson, Map.class);

        for (String attrItemIndex : attrMap.keySet()) {
            String attrItemValue = attrMap.get(attrItemIndex);
            String[] attrItemValues = attrItemValue.split(",");
            for (int i = 1; i < attrItemValues.length; i++) {
                PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
                pmsProductSaleAttrValue.setProductId(procId);
                pmsProductSaleAttrValue.setSaleAttrId(attrItemIndex);
                pmsProductSaleAttrValue.setSaleAttrValueName(attrItemValues[i]);

                pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }

        }
    }

    @Override
    public List<PmsProductInfo> getAllProcByCatalog3(String catalog3) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3);
        List<PmsProductInfo> pmsProductInfoList = pmsProductInfoMapper.select(pmsProductInfo);
        for (PmsProductInfo productInfo : pmsProductInfoList) {
            PmsProductImage pmsProductImage = new PmsProductImage();
            pmsProductImage.setProductId(productInfo.getId());
            List<PmsProductImage> images = pmsProductImageMapper.select(pmsProductImage);
            productInfo.setSpuImageList(images);

        }
        return pmsProductInfoList;
    }

    @Override
    public int deleteProc(String id) {
        int delCount = pmsProductInfoMapper.deleteByPrimaryKey(id);
        if (delCount > 0){
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(id);
            pmsProductSaleAttrValueMapper.delete(pmsProductSaleAttrValue);

            PmsProductImage pmsProductImage = new PmsProductImage();
            pmsProductImage.setProductId(id);
            pmsProductImageMapper.delete(pmsProductImage);
        }
        return delCount;
    }

    @Override
    public PmsProductInfo getProcById(String id) {

        PmsProductInfo pmsProductInfo = pmsProductInfoMapper.selectByPrimaryKey(id);
            if (pmsProductInfo != null){
                PmsProductImage pmsProductImage = new PmsProductImage();
                pmsProductImage.setProductId(id);
                List<PmsProductImage> images = pmsProductImageMapper.select(pmsProductImage);
                pmsProductInfo.setSpuImageList(images);

                PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
                pmsProductSaleAttrValue.setProductId(id);
                List<PmsProductSaleAttrValue> valueList = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
                pmsProductInfo.setSpuSaleAttrValueList(valueList);
            }


        return pmsProductInfo;
    }
}
