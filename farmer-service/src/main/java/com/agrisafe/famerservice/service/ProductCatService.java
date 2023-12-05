package com.agrisafe.famerservice.service;

import com.agrisafe.common.dto.request.ProductCatCreateReq;
import com.agrisafe.common.dto.response.ProductCatRes;

import java.util.List;

public interface ProductCatService {
    ProductCatRes createProductCat(ProductCatCreateReq productCatCreateReq);

    List<ProductCatRes> getProductCategories();

    ProductCatRes getProductCat(Long productId);

    ProductCatRes updateProductCat(ProductCatCreateReq productCatCreateReq, Long productId);

    void deleteProduct(Long productId);
}
