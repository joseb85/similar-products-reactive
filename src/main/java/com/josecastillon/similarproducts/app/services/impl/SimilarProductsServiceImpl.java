package com.josecastillon.similarproducts.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josecastillon.similarproducts.app.consumers.ProductsConsumer;
import com.josecastillon.similarproducts.app.entities.Product;
import com.josecastillon.similarproducts.app.services.SimilarProductsService;

import reactor.core.publisher.Flux;

@Service
public class SimilarProductsServiceImpl implements SimilarProductsService {
		
	@Autowired
	private ProductsConsumer productsConsumer;
	
	/**
	 * Recupera los productos similares a un producto
	 * @param id Id del producto a buscar
	 */
	@Override
	public Flux<Product> findSimilarById(String id) {
		return this.productsConsumer.findListIdSimilarById(id)
				.flatMap(idI -> this.productsConsumer.findById(idI));
	}

}
