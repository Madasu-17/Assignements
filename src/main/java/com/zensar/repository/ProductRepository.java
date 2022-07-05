package com.zensar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import com.zensar.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	// List<Product> findByProductName(String productName);
	@Query(value="From Product p where p.productName=:name",nativeQuery = true)
	List<Product> test(@Param(value = "name") String productName);

	// List<Product> getByProductCost(int productCost);
	@Query(value="from Product p where p.productCost=:cost",nativeQuery = true)
	List<Product> test1(@Param(value = "cost") int productCost);

	// List<Product> findByProductNameAndProductCost(String productName, int
	// productCost);
	@Query(value="from Product p where p.productName=:name and p.productCost=:cost",nativeQuery = true)
	List<Product> test2(@Param(value = "name") String productName,@Param(value ="cost") int productCost);

}
