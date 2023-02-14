package com.josecastillon.similarproducts.app.services;

import com.josecastillon.similarproducts.app.entities.Product;

import reactor.core.publisher.Flux;

public interface SimilarProductsService {
	
	public Flux<Product> findSimilarById(String id);
	
}
