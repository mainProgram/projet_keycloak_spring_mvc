package com.groupeisi.keycloak.service;

import com.groupeisi.keycloak.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Optional<List<ProductDto>> findAll();

    boolean save(ProductDto productDto);
}
