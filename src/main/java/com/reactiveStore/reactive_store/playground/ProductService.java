package com.reactiveStore.reactive_store.service;

import com.reactiveStore.reactive_store.playground.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService {

    private final List<Product> products =
            List.of(
                    new Product("P1", "Laptop", 50000.0),
                    new Product("P2", "Phone", 25000.0),
                    new Product("P3", "Mouse", 500.0)
            );

    // Reactive version
    public List<Product> findAll() {
//        return Flux.fromIterable(products);

        return products;
    }

    public Product findById(String id) {

//        return Flux.fromIterable(products)
//                .filter(p -> p.id().equals(id))
//                .next();

        return products.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}