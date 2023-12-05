package com.agrisafe.famerservice.controller;

import com.agrisafe.common.dto.request.ProductCatCreateReq;
import com.agrisafe.common.dto.response.ProductCatRes;
import com.agrisafe.common.dto.response.ResponseMessage;
import com.agrisafe.famerservice.service.ProductCatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-category")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCatService productCatService;

    @PostMapping
    public ProductCatRes createProductCategories(@Valid @RequestBody ProductCatCreateReq productCatCreateReq) {
        return productCatService.createProductCat(productCatCreateReq);
    }

    @GetMapping
    public List<ProductCatRes> getProductCategories() {
        return productCatService.getProductCategories();
    }

    @GetMapping("/{product_cat_id}")
    public ProductCatRes getProductCategory(@PathVariable("product_cat_id") Long productCatId) {
        return productCatService.getProductCat(productCatId);
    }

    @PutMapping("/{product_cat_id}")
    public ProductCatRes updateProductCategory(@PathVariable("product_cat_id") Long productCatId,
                                               @Valid @RequestBody ProductCatCreateReq productCatCreateReq) {
        return productCatService.updateProductCat(productCatCreateReq, productCatId);
    }

    @DeleteMapping("/{product_cat_id}")
    public ResponseMessage deleteProductCategory(@PathVariable("product_cat_id") Long productCatId) {
        productCatService.deleteProduct(productCatId);
        return new ResponseMessage("product category deleted successfully");
    }
}
