package com.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongo.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Integer>{
List<Product> findByProductnameStartsWith(String name);
List<Product> findByProductname(String productname);
//List<Product> findByProductPriceBetween(Integer minprice,Integer maxprice);


//greater than(1st parameter)minprice and lesser than(2nd)maxprice and if productcompany field don't want to show
//(productCompany: 0)--0 no need the particular field,1 need the field
@Query(value= "{'productPrice':{$gt:?0,$lt:?1}}",
fields= "{productCompany: 0}" )

List<Product> findproductByProductPriceBetween(Integer minprice,Integer maxprice);
}
