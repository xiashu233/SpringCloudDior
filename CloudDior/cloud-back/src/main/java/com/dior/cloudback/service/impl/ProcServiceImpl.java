package com.dior.cloudback.service.impl;

import com.dior.bean.PmsProductInfo;
import com.dior.cloudback.mapper.PmsProductImageMapper;
import com.dior.cloudback.mapper.PmsProductInfoMapper;
import com.dior.service.ProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcServiceImpl implements ProcService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Override
    public int addProcInfo(PmsProductInfo pmsProductInfo) {
        return 0;
    }

    @Override
    public void addProcImage(String catalog3Id, String imgList) {

    }
}
