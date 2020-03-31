package com.dior.service;

import com.dior.bean.PmsBaseCatalog1;
import com.dior.bean.PmsBaseCatalog2;
import com.dior.bean.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {
    List<PmsBaseCatalog1> getAllCatalogOne();

    List<PmsBaseCatalog2> getAllCatalogTwo(String id);

    List<PmsBaseCatalog3> getAllCatalogThree(String id);

    int addCatalog1(String catalog1txt);

    int addCatalog2(int catalog1, String catalog2txt);

    int addCatalog3(int catalog2, String catalog3txt);
}
