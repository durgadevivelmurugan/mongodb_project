package com.mongo.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.entity.Product;
import com.mongo.service.ProductService;

import lombok.Delegate;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping("/save")
	public Object save(@RequestBody Product product) {
		return productService.addproduct(product);
	}
	
	@GetMapping("/getstart")
	public List<Product> getProductStartsWith(@RequestParam("name") String name){
	return productService.getproductWith(name);
	}
	
	@GetMapping("/get/{productname}")
	public List<Product> getProductname(@PathVariable String productname){
	return productService.getproductWithName(productname);
	}
	
	@DeleteMapping("/delete/{productid}")
	public Product delete(@PathVariable int productid) {
		return productService.deleteid(productid);
	}
	
	@GetMapping("/price")
	public List<Product> getByProductPrice(@RequestParam Integer minprice,@RequestParam Integer maxprice){
		return productService.getByProductPrice(minprice,maxprice);
	}

	
	//search api(pagination with search by using mongo template )
	//<Product>-->type product
	@GetMapping("/search")
	public Page<Product>search(@RequestParam(required = false) String name,
	                              @RequestParam(required = false) Integer minprice,
	                              @RequestParam(required = false)Integer maxprice,
	                              @RequestParam(required = false)String productCompany,
	                              @RequestParam(defaultValue = "0")Integer page,
	                              @RequestParam(defaultValue = "5")Integer size){
		Pageable pageable=PageRequest.of(page, size);
		
		
		return productService.search(name,minprice,maxprice,productCompany,pageable);
	}
	
     //lowest price of each product
	@GetMapping("/lowprice")
	public List<Document> getLowestPrice(){
		return productService.getLowestPriceByProductPrice();
	}
	
	//count of each product
	@GetMapping("/count")
	public List<Document>getProductByProductname(){
		return productService.getProductByProductname();
	}
	
	

	
}
