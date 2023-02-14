package com.josecastillon.similarproducts.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josecastillon.similarproducts.app.entities.Product;
import com.josecastillon.similarproducts.app.services.SimilarProductsService;

import jakarta.el.MethodNotFoundException;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/product")
public class SimilarProductsController {
	
	@Autowired
	private SimilarProductsService similarProductsService;

	/**
	 * Devuelve los productos similares a otro
	 * @param id Id del producto a buscar sus similares
	 * @return
	 */
	@GetMapping(path = "/{id}/similar")
	public Flux<Product> findSimilarById(@PathVariable String id) {
		return similarProductsService.findSimilarById(id);
	}
	
}
