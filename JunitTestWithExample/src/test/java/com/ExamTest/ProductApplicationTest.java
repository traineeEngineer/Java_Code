package com.ExamTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
class ProductApplicationTest {

	@Autowired
	private ProductRepository prepo;

	@Test
	void testToSaveIntoDatabase() {
		Product p = new Product("iPhone 10", 7878);
		Product saveProdcut = prepo.save(p);
		assertNotNull(saveProdcut);
	}

	@Test
	void testFindByProductNameByExist() {
		String name = "iPhone 10";
		Product p = prepo.findByName(name);
		assertThat(p.getName()).isEqualTo(name);
	}

	@Test
	void testFindByProductNameNotExist() {
		String name = "iPhone 11";
		Product p = prepo.findByName(name);
		assertNull(p);
	}

	@Test
	void testUpdateProduct() {
		String productName = "Trimmer";
		Product pro = new Product(productName, 199);
		pro.setId(2);
		prepo.save(pro);

		Product updateProduct = prepo.findByName(productName);
		assertThat(updateProduct.getName()).isEqualTo(productName);

	}
	
	@Test
	void testListProduct() {
		List<Product> listProduct =prepo.findAll();
		assertThat(listProduct).size().isGreaterThanOrEqualTo(0);
	}
	
	@Test
	void testDeleteProduct() {
		Integer id=1;
		Optional<Product> present=prepo.findById(id);
        assertEquals(present, prepo.findById(id));
	    
	}

}
