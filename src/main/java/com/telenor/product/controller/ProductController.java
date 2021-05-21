package com.telenor.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telenor.product.configuration.ProductConfigurator;
import com.telenor.product.entity.Product;
import com.telenor.product.exception.ResourceNotFoundException;
import com.telenor.product.service.ProductReaderService;
import com.telenor.product.service.ProductRepositoryImpl;

@RestController
@RequestMapping("/api/")
public class ProductController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ProductController.class);
	@Autowired
	ProductRepositoryImpl productServiceImpl;
	
		
	/**
	 * Getting data from H2 database
	 * @param Type
	 * @param Properties
	 * @param Min_Price
	 * @param Max_Price
	 * @param City
	 * @return
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "product", method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<Product>>> getProducts(@RequestParam(value = "type") Optional<String> Type,
			@RequestParam(value = "properties") Optional<String> Properties,
			@RequestParam(value = "min_price") Optional<Double> Min_Price,
			@RequestParam(value = "max_price") Optional<Double> Max_Price,
			@RequestParam(value = "store_address") Optional<String> storeAddress) {
		Map<String, List<Product>> result = new HashMap<String, List<Product>>();
		List<Product> products = null;
		LOGGER.info("Getting the data from Product repository");
		products = productServiceImpl.findByMultipleParameter(Type, Properties, Min_Price, Max_Price, storeAddress);
		if (products.isEmpty()) {
			LOGGER.info("No data available for the request");
			throw new ResourceNotFoundException("Data is not available");
			
		}
		result.put("data", products);
		return new ResponseEntity<Map<String, List<Product>>>(result, HttpStatus.OK);
	}
}
