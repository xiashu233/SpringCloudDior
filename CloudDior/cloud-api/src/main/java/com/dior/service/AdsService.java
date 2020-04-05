package com.dior.service;

import com.dior.bean.PmsProductAds;

import java.util.List;

public interface AdsService {
    String saveBanner(PmsProductAds pmsProductAds);

    List<PmsProductAds> getAllCatalog1Ads();

    int changeBanner(PmsProductAds pmsProductAds);

    int delBannerById(String id);
}
