package com.mongo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.mongo.entity.Product;
import com.mongo.repository.ProductRepository;
import com.mongo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	@Override
	public Object addproduct(Product product) {
		productRepository.save(product);
		return "saved sucessfully "+ product.getProductid();
	}

	@Override
	public List<Product> getproductWith(String name) {
		return productRepository.findByProductnameStartsWith(name);
	}

	@Override
	public List<Product> getproductWithName(String productname) {
		return productRepository.findByProductname(productname);
	}

	@Override
	public Product deleteid(int productid) {
		Product pro=productRepository.findById(productid).get();
		productRepository.delete(pro);
		return pro;
		
	}

	@Override
	public List<Product> getByProductPrice(Integer minprice, Integer maxprice) {
		return productRepository.findproductByProductPriceBetween(minprice, maxprice);
	}

	//search api(pagination with search by using mongo template )
	@Override
	public Page<Product> search(String name, Integer minprice, Integer maxprice, String productCompany,
			Pageable pageable) {
		
	
		//this Query is used to fetch data from mongodb database
   Query query=	new Query().with(pageable);
   
   List<Criteria>criteria=new ArrayList<Criteria>();
	if(name!=null&&!name.isEmpty())	{
		//"i"==>case sensitive
		//if the name is notnull and not empty then it should match the 1st criteria(productname should match with name that we provided) 
		criteria.add(Criteria.where("productname").regex(name,"i"));
		
	}
	 
	if(minprice !=null && maxprice !=null ) {
		
		//if minprice and maxprice not null then the productprice(greater than minprice and lowerthan max)
		criteria.add(Criteria.where("productPrice").gte(minprice).lte(maxprice));
	}
	
	if(productCompany !=null &&productCompany.isEmpty()) {
		
	criteria.add(Criteria.where("productCompany").is(productCompany));
	}
	
	//attach the above criteria to query
	//taking all the above criteria and converting to array(criteria.toArray())
	if(!criteria.isEmpty()) {
		query.addCriteria(new Criteria()
				.andOperator(criteria.toArray(new Criteria[0])));//adding all the criteria by using andOperator
	}
	Page<Product>pro=PageableExecutionUtils.getPage(
			mongoTemplate.find(query,Product.class),
					pageable,
					()-> mongoTemplate.count(query.skip(0).limit(0),Product.class)
					
			);
		return pro;
	}

	//1st going to get Lowest price by productname
	//Using MongoTemplate and aggregation functions.
	@Override
	public List<Document> getLowestPriceByProductPrice() {
		
		//it is used to fetch ex(city inside address)

		//		UnwindOperation unwindOperation=Aggregation.unwind("productname");[unwrap operation]
		
		SortOperation operation=Aggregation
				.sort(Sort.Direction.ASC ,"productPrice");
		
		//
		GroupOperation groupOperation=Aggregation.group("productname")
				.first(Aggregation.ROOT)
				.as("lowestPrice");
		
		Aggregation aggregation=Aggregation.newAggregation(operation,groupOperation);
		
		
//we pass the operations in aggregation and that aggregation is passed to  mongotemplate to get document back.
		//we want as document,aggregate()->aggregation,product.class(input type,document.class(to get [as output])) 
		List<Document>product=mongoTemplate.aggregate(aggregation, Product.class,Document.class).getMappedResults();
		return product;
	}

	//total count of the product  
	@Override
	public List<Document> getProductByProductname() {
		
		//productname and its count
		GroupOperation groupOperation=Aggregation.group("productname")
				.count().as("count");
		
		SortOperation operation=Aggregation.sort(Sort.Direction.DESC,"count");
		
		//if we want particular fields we can perform projection
		ProjectionOperation projectionOperation= 
				Aggregation
				.project()
				.andExpression("_id").as("productname")  //must _id don't use productid 
				.andExpression("count").as("counts")
				.andExclude("_id");
		
		Aggregation aggregation=Aggregation.newAggregation(groupOperation,operation,projectionOperation);
		//pass aggregation to mongo template to get data
		//pass input class(product.class),outputclass(document.class)
		List<Document>doc=mongoTemplate
				.aggregate(aggregation,Product.class,Document.class).getMappedResults();
		
		
		return doc;
	}
	

}
