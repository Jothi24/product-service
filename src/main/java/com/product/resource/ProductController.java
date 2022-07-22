package com.product.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.exception.ResourceNotFoundException;
import com.product.model.Product;
import com.product.repository.ProductRepository;





@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product-service")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	@GetMapping("/listproducts")
	  public ResponseEntity<List<Product>> listAllProducts() {
	    List<Product> products = new ArrayList<Product>();

	   
	    products = productRepository.findAll();

	    if (products.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(products, HttpStatus.OK);
	  }
	
	@GetMapping("/listproductsbyid/{productId}")
	  public ResponseEntity<Product> listAllProductsById(@PathVariable(value = "productId") String productId) {
		/* List<Product> products = new ArrayList<Product>();

	   
	    Optional<Product> findById = productRepository.findById(Integer.parseInt(productId));

	    if (products.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(findById, HttpStatus.OK);
	    
	}*/
		
		int prodId  = Integer.parseInt(productId);
	
		Product product = productRepository.findById(prodId).orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + productId));
		
		 

return new ResponseEntity<>(product, HttpStatus.OK);
	}
		
		
	
	
	 @PostMapping("/products")
	  public ResponseEntity<Product> createMobile(@RequestBody Product product) {
		 Product product2 = productRepository.save(new Product(product.getProductName(), product.getPrice()));
	    //Mobile _mobile = mobileRepository.save(new Mobile(mobile.getMobileCompany(), mobile.getMobileName()));
	    return new ResponseEntity<>(product2, HttpStatus.CREATED);
	  }

}
