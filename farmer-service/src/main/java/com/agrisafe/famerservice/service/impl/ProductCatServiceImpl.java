package com.agrisafe.famerservice.service.impl;

import com.agrisafe.common.dto.request.ProductCatCreateReq;
import com.agrisafe.common.dto.response.ProductCatRes;
import com.agrisafe.common.exception.APIException;
import com.agrisafe.common.exception.ResourceNotFoundException;
import com.agrisafe.common.model.ProductCategory;
import com.agrisafe.famerservice.repository.ProductCategoryRepo;
import com.agrisafe.famerservice.service.ProductCatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCatServiceImpl implements ProductCatService {
    private final ProductCategoryRepo productCategoryRepo;

    @Override
    public ProductCatRes createProductCat(ProductCatCreateReq productCatCreateReq) {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepo.
                findByName(productCatCreateReq.getName());

        if (optionalProductCategory.isPresent()) throw new APIException("product category name is taken");

        ProductCategory productCategory = ProductCategory.builder().name(productCatCreateReq.getName()).build();

        ProductCategory savedProductCat = productCategoryRepo.save(productCategory);

        return productCatToResMapper(savedProductCat);
    }

    @Override
    public List<ProductCatRes> getProductCategories() {
        return productCategoryRepo.findAll().stream().map(this::productCatToResMapper).toList();
    }

    @Override
    public ProductCatRes getProductCat(Long productId) {
        ProductCategory productCategory = productCategoryRepo
                .findById(productId).orElseThrow(() -> new ResourceNotFoundException("product category", "id", String.valueOf(productId)));
        return productCatToResMapper(productCategory);
    }

    @Override
    public ProductCatRes updateProductCat(ProductCatCreateReq productCatCreateReq, Long productId) {
        ProductCategory existingProductCategory = productCategoryRepo
                .findById(productId).orElseThrow(() -> new ResourceNotFoundException("product category", "id", String.valueOf(productId)));
        existingProductCategory.setName(productCatCreateReq.getName());
        ProductCategory updatedProductCategory = productCategoryRepo.save(existingProductCategory);
        return productCatToResMapper(updatedProductCategory);
    }

    @Override
    public void deleteProduct(Long productId) {
        ProductCategory productCategory = productCategoryRepo
                .findById(productId).orElseThrow(() -> new ResourceNotFoundException("product category", "id", String.valueOf(productId)));
        productCategoryRepo.delete(productCategory);
    }

    private ProductCatRes productCatToResMapper(ProductCategory productCategory) {
        return ProductCatRes.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .createdAt(productCategory.getCreatedAt())
                .updatedAt(productCategory.getUpdateAt())
                .build();
    }
}
