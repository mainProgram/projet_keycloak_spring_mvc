package com.groupeisi.keycloak.service;

import com.groupeisi.keycloak.dao.IProductDao;
import com.groupeisi.keycloak.dao.ProductDao;
import com.groupeisi.keycloak.dto.ProductDto;
import com.groupeisi.keycloak.entity.ProductEntity;
import com.groupeisi.keycloak.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService{

    private IProductDao productDao = new ProductDao();

    @Override
    public Optional<List<ProductDto>> findAll() {
        List<ProductEntity> productEntityList = productDao.list(new ProductEntity());

        return Optional.of(ProductMapper.toListProductDto(productEntityList));
    }

    @Override
    public boolean save(ProductDto productDto) {
        return productDao.save(ProductMapper.toProductEntity(productDto));
    }

}
