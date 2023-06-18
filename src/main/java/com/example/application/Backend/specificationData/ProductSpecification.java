package com.example.application.Backend.specificationData;

import com.example.application.Backend.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification{
    public static Specification<Product> hasName(String name) {
        return (root, query, builder) -> builder.equal(root.get("name"), name);
    }
    public static Specification<Product> hasCategory(String category) {
        return (root, query, builder) -> builder.equal(root.get("category"), category);
    }
    public static Specification<Product> hasPrice(Double price) {
        return (root, query, builder) -> builder.equal(root.get("price"), price);
    }
}
