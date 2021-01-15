package com.krishna.product.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.krishna.product.entity.Product;

@Service
public class ProductReaderService {
	public List<Product> readData(Resource filePath) {

		List<Product> productList = new ArrayList<Product>();
		try (BufferedReader csvReader = new BufferedReader(new InputStreamReader(filePath.getInputStream()))) {

			productList = csvReader.lines().skip(1).map(mapToProduct).collect(Collectors.toList());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return productList;

	}

	private Function<String, Product> mapToProduct = (line) -> {
		String[] data = line.split(",");

		Product product = new Product();

		product.setType(data[0]);
		product.setProperties(data[1]);
		product.setPrice((Double.parseDouble(data[2])));
		product.setStoreAddress(data[3].substring(1) + "," + data[4].substring(0, data[4].length() - 1));

		return product;
	};

}
