package com.groupeisi.keycloak.dto;

public class ProductDto {
    private String reference;
    private String name;

    public ProductDto() {
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
