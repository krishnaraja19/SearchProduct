package com.krishna.product.configuration;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;

import com.krishna.product.entity.Product;
import com.krishna.product.exception.ExceptionHelper;
import com.krishna.product.repository.ProductRepository;
import com.krishna.product.service.ProductReaderService;
import com.krishna.product.service.ProductRepositoryImpl;

@Configuration
public class ProductConfigurator {
	private static final Logger logger = LoggerFactory.getLogger(ProductConfigurator.class);

	@Autowired
	ProductReaderService dataReader;
	@Autowired
	ProductRepositoryImpl productServiceImpl;
	@Value("${datasource.file.path}")
	private Resource filePath;

	@EventListener(ApplicationReadyEvent.class)
	public void loadDataBeforeStartup() {
		List<Product> products = dataReader.readData(filePath);
		productServiceImpl.bulkSave(products);
	}

	@Bean
	public ProductRepositoryImpl productServiceImpl() {
		return new ProductRepositoryImpl();
	}
}
