package com.dior.service;

import com.dior.bean.PmsProductInfo;

public interface ProcService {
    int addProcInfo(PmsProductInfo pmsProductInfo);

    void addProcImage(String id, String imgList);
}
