package com.zensar.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.zensar.dto.ProductDto;

import com.zensar.entity.Product;
import com.zensar.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDto getStudent(int productId) {
		Product product = productRepository.findById(productId).get();
		// ProductDto dto = mapToDto(product);
		return modelMapper.map(product, ProductDto.class);

	}

	@Override
	public List<ProductDto> getAllProducts(int pageNumber, int pageSize) {
		// List<Product> listOfProductDtos = productRepository.findAll();
		List<ProductDto> listOfProductDto = new ArrayList<ProductDto>();

		Page<Product> findAll = productRepository
				.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Direction.DESC, "productName")));
		List<Product> content = findAll.getContent();
		for (Product product : content) {
			// listOfProductDto.add(mapToDto(product));
			listOfProductDto.add(modelMapper.map(product, ProductDto.class));
		}
		return listOfProductDto;
	}

	@Override
	public ProductDto insertProduct(ProductDto productDto) {
		// Product product = mapToEntity(productDto);
		Product product = modelMapper.map(productDto, Product.class);
		Product insertedProduct = productRepository.save(product);
		// ProductDto dto1 = mapToDto(insertedProduct);
		return modelMapper.map(insertedProduct, ProductDto.class);

	}

	@Override
	public void updateProduct(int productId, ProductDto productDto) {
		// Product product = mapToEntity(productDto);
		Product product = modelMapper.map(productDto, Product.class);
		productRepository.save(product);
	}

	@Override
	public void deletProduct(int productId) {
		productRepository.deleteById(productId);

	}

	@Override
	public List<Product> getByProductName(String productName) {
		return productRepository.test(productName);
	}

	@Override
	public List<Product> getByProductCost(int productCost) {
		return productRepository.test1(productCost);
	}

	@Override
	public List<Product> findByProductNameAndProductCost(String productName, int productCost) {
		return productRepository.test2(productName, productCost);
	}

	/*
	 * public Product mapToEntity(ProductDto productDto) { Product product = new
	 * Product(); product.setProductId(productDto.getProductId());
	 * product.setProductName(productDto.getProductName());
	 * product.setProductCost(productDto.getProductCost()); return product;
	 * 
	 * }
	 * 
	 * public ProductDto mapToDto(Product product) { Product dto = new Product();
	 * dto.setProductId(product.getProductId());
	 * dto.setProductName(product.getProductName());
	 * dto.setProductCost(product.getProductCost()); return dto; }
	 */

}
