package com.josecastillon.similarproducts.app.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.josecastillon.similarproducts.app.entities.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductsConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(ProductsConsumer.class);

	@Value("${webclient.controller.path}")
	private String controllerPath;
	
	@Autowired
	private WebClient webClientProducts;
	
	/**
	 * Recupera la lista con los ids de los productos similares a otro
	 * @param id Id del producto
	 * @return
	 */
	public Flux<String> findListIdSimilarById(String id) {
		String uri = controllerPath + id + "/similarids";
		log.info("Buscamos similares a " + id);
		return webClientProducts.get().uri(uri).retrieve().bodyToMono(String[].class).onErrorResume(e -> {
			log.error("Error en la búsqueda de la lista: " + e.getMessage());
			return Mono.empty();
		}).flatMapMany(ids -> Flux.fromArray(ids));
	}
	
	/**
	 * Recupera un producto
	 * @param id Id del producto a recuperar
	 * @return
	 */
	public Mono<Product> findById(String id) {
		log.info("Buscamos el producto " + id);
		return webClientProducts.get().uri(controllerPath + "{id}", id).retrieve().bodyToMono(Product.class).onErrorResume(e -> {
			log.error("Error en la búsqueda del producto: " + e.getMessage());
			return Mono.empty();
		});
	}

}
