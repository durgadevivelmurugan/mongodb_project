package com.mongo.service;

import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mongo.entity.Product;

public interface ProductService {

	

	public Object addproduct(Product product);

	public List<Product> getproductWith(String name);

	public List<Product> getproductWithName(String productname);

	public Product deleteid(int productid);

	public List<Product> getByProductPrice(Integer minprice, Integer maxprice);

	public Page<Product> search(String name, Integer minprice, Integer maxprice, String productCompany, Pageable pageable);

	public List<Document> getLowestPriceByProductPrice();

	public List<Document> getProductByProductname();

}
