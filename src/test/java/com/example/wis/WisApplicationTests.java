package com.example.wis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WisApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void indexShouldRedirectToProductPage() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("<h1>Product");
	}

	@Test
	public void productPage() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/product",
				String.class)).contains("<h1>Product");
	}

	@Test
	public void inventoryPage() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/inventory",
				String.class)).contains("<h1>Inventory");
	}

	@Test
	public void uploadPage() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/upload",
				String.class)).contains("<h1>Upload");
	}
}
