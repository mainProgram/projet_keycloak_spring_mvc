package com.groupeisi.keycloak.controller;

import com.groupeisi.keycloak.dto.ProductDto;
import com.groupeisi.keycloak.service.IProductService;
import com.groupeisi.keycloak.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private IProductService productService = new ProductService();

    @GetMapping("/products")
    public String showFindAll(Model model) {

        try {
            Optional<List<ProductDto>> products = productService.findAll();
            if (products.isPresent()) {
                model.addAttribute("productList", products.get());
            } else {
                model.addAttribute("productList", new ArrayList<ProductDto>());
            }
        } catch (Exception e) {
            logger.error(" Erreur lors de la récupération des produits", e);
        }

        return "product/products";
    }

    @PostMapping("/products")
    public String save(
            @RequestParam("reference") String ref,
            @RequestParam("name") String name) {

        ProductDto productDto = new ProductDto();
        productDto.setReference(ref);
        productDto.setName(name);

        try {
            boolean productSaved = productService.save(productDto);
            if (productSaved) {
                logger.info("Produit enregistré avec succès");
            } else {
                logger.warn("Échec de l'enregistrement du produit");
            }
        } catch (Exception e) {
            logger.error("Erreur d'enregistrement du produit", e);
        }

        return "redirect:/products";
    }
}
