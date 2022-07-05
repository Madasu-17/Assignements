package com.zensar.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.zensar.dto.ProductDto;
import com.zensar.entity.Product;

public interface ProductService {
	public ProductDto getStudent(int productId);
	
	public List<ProductDto> getAllProducts(int pageNumber, int pageSize);

	public ProductDto insertProduct(ProductDto productDto);

	public void updateProduct(int productId, @RequestBody ProductDto productDto);

	public void deletProduct(int productId);

	List<Product> getByProductName(String productName);

	List<Product> getByProductCost(int productCost);

	List<Product> findByProductNameAndProductCost(String productName, int productCost);
}
