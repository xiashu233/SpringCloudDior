package com.dior.cloudback.service.impl;

import com.dior.bean.PmsBaseCatalog1;
import com.dior.bean.PmsBaseCatalog2;
import com.dior.bean.PmsBaseCatalog3;
import com.dior.cloudback.mapper.Catalog1Mapper;
import com.dior.cloudback.mapper.Catalog2Mapper;
import com.dior.cloudback.mapper.Catalog3Mapper;
import com.dior.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    Catalog1Mapper catalog1Mapper;
    @Autowired
    Catalog2Mapper catalog2Mapper;
    @Autowired
    Catalog3Mapper catalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getAllCatalogOne() {
        List<PmsBaseCatalog1> pmsBaseCatalog1s = catalog1Mapper.selectAll();
        return pmsBaseCatalog1s;
    }

    @Override
    public List<PmsBaseCatalog2> getAllCatalogTwo(String id) {
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(id);
        List<PmsBaseCatalog2> catalog2List = catalog2Mapper.select(pmsBaseCatalog2);
        return catalog2List;
    }

    @Override
    public List<PmsBaseCatalog3> getAllCatalogThree(String id) {
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(id);
        List<PmsBaseCatalog3> catalog3List = catalog3Mapper.select(pmsBaseCatalog3);
        return catalog3List;
    }

    @Override
    public int addCatalog1(String catalog1txt) {
        PmsBaseCatalog1 pmsBaseCatalog1 = new PmsBaseCatalog1();
        pmsBaseCatalog1.setName(catalog1txt);
        return catalog1Mapper.insertSelective(pmsBaseCatalog1);
    }

    @Override
    public int addCatalog2(int catalog1, String catalog2txt) {
        PmsBaseCatalog2 pmsBaseCatalog2Ts = new PmsBaseCatalog2();
        pmsBaseCatalog2Ts.setCatalog1Id(catalog1 + "");
        pmsBaseCatalog2Ts.setName(catalog2txt);
        if (catalog2Mapper.select(pmsBaseCatalog2Ts).size() > 0){
            return -1;
        }

        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalog1 + "");
        pmsBaseCatalog2.setName(catalog2txt);
        return catalog2Mapper.insertSelective(pmsBaseCatalog2);
    }

    @Override
    public int addCatalog3(int catalog2, String catalog3txt) {
        PmsBaseCatalog3 pmsBaseCatalog3Ts = new PmsBaseCatalog3();
        pmsBaseCatalog3Ts.setCatalog2Id(catalog2 + "");
        pmsBaseCatalog3Ts.setName(catalog3txt);
        if (catalog3Mapper.select(pmsBaseCatalog3Ts).size() > 0){
            return -1;
        }

        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalog2 + "");
        pmsBaseCatalog3.setName(catalog3txt);
        return catalog3Mapper.insertSelective(pmsBaseCatalog3);
    }
}
