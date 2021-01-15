package com.krishna.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.krishna.product.entity.Product;
import com.krishna.product.repository.ProductRepository;

public class ProductRepositoryImpl {
	
	@Autowired
	ProductRepository productRepo;
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	    private int batchSize;
	
	public void bulkSave(List<Product> products) {
		List<Product> batchProduct = productRepo.saveAll(products);
			
		
	}
	public List<Product> findByMultipleParameter(Optional<String> type,Optional<String> properties,Optional<Double> minPrice,Optional<Double> maxPrice,Optional<String> City){
		
		String percentage="%";
		String locType = type.map(Object::toString).orElse("");
		String locProperties = properties.map(Object::toString).orElse("");	
		Double locMinPrice = minPrice.map(p->p.doubleValue()).orElse(0.0);	
		Double locMaxPrice = maxPrice.map(p->p.doubleValue()).orElse(1000.0);
		String locCity = City.map(Object::toString).orElse("");
		locCity = percentage.concat(locCity.concat(percentage));
		
		List<Product> products = productRepo.findByTypeAndProperties(locType, locProperties, locMinPrice,locMaxPrice,locCity);
		return products;
		
	}
}
