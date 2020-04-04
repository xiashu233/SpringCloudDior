package com.dior.service;

import com.dior.bean.PmsProductInfo;

import java.util.List;

public interface ProcService {
    String addProcInfo(PmsProductInfo pmsProductInfo);

    void addProcImage(String id, String imgList,int defaultImgIndex);

    void addProcAttr(String procId,String attrMapJson);

    List<PmsProductInfo> getAllProcByCatalog3(String catalog3);

    int deleteProc(String id);

    PmsProductInfo getProcById(String id);

    List<PmsProductInfo> getProcBySearchKey(String key);

    void changeProcInfo(PmsProductInfo pmsProductInfo);

    void deleteProcImageByProcId(String id);

    void deleteProcAttrValueByProcId(String id);
}
