package com.zensar.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.ProductDto;
import com.zensar.entity.ErrorResponse;
import com.zensar.entity.Product;
import com.zensar.exceptions.NoSuchProductExistsException;
import com.zensar.exceptions.ProductAlreadyExistsException;
import com.zensar.service.ProductService;

@RestController
@RequestMapping(value = "/product-api", produces = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE })
public class ProductController {
	@Autowired
	private ProductService productService;

	@ExceptionHandler(value = ProductAlreadyExistsException.class)

	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleStudentAlreadyExistsException(ProductAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}

	@ExceptionHandler(value = NoSuchProductExistsException.class)

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleNoSuchCouponExistsException(ProductAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	// http:localhost:8282/products - GET Method by productId
	// @RequestMapping(value="/products/{productId}", method = RequestMethod.GET)
	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDto> getStudent(@PathVariable("productId") int productId) {
		return new ResponseEntity(productService.getStudent(productId), HttpStatus.OK);
	}

	// http:localhost:8282/products - GET Method for All products
	// @RequestMapping("/products")
	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDto>> getAllProducts(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "3") int pageSize) {

		return new ResponseEntity<List<ProductDto>>(productService.getAllProducts(pageNumber, pageSize), HttpStatus.OK);
	}

	// http:localhost:8282/products - POST method for adding products
	// @RequestMapping(value = "/products", method = RequestMethod.POST)
	@PostMapping(value = "/products")
	public ResponseEntity<ProductDto> insertProduct(@RequestBody ProductDto productDto) {
		return new ResponseEntity<>(productService.insertProduct(productDto), HttpStatus.CREATED);
	}

	// http:localhost:8282/products - PUT Method for updatingProducts
	// @RequestMapping(value="/products/{productId}", method = RequestMethod.PUT)
	@PutMapping("/products/{productId}")
	public ResponseEntity<String> updateProduct(@PathVariable("productId") int productId,
			@RequestBody ProductDto productDto) {
		productService.updateProduct(productId, productDto);
		return new ResponseEntity<String>("Product Updated Successfully..!!", HttpStatus.OK);
	}

	// http:localhost:8282/products - DELETE Method for DeletingProducts
	// @RequestMapping(value = "/products/{productId}", method =
	// RequestMethod.DELETE)
	@DeleteMapping(value = "/products/{productId}")
	public ResponseEntity<String> deletProduct(@PathVariable("productId") int productId) {
		productService.deletProduct(productId);
		return new ResponseEntity<String>("Product Deleted Successfully..!!", HttpStatus.OK);
	}

	// http:localhost:8282/product-api/products/productName/Keypad
	// --getByProductName
	@RequestMapping("/products/name/{productName}")
	public List<Product> getByProductName(@PathVariable("productName") String productName) {
		return productService.getByProductName(productName);

	}

	@RequestMapping("/products/cost/{productCost}")
	public List<Product> getByProductCost(@PathVariable("productCost") int productCost) {
		return productService.getByProductCost(productCost);
	}

	@RequestMapping("/products/{productName}/{productCost}")
	public List<Product> findByProductNameAndProductCost(@PathVariable String productName,
			@PathVariable("productCost") int productCost) {
		return productService.findByProductNameAndProductCost(productName, productCost);
	}
}
