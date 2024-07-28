package com.manas.inventoryservice;

import com.manas.inventoryservice.model.Inventory;
import com.manas.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setQuantity(100);
			inventory.setSkuCode("iphone_13");

			Inventory inventory1 = new Inventory();
			inventory1.setQuantity(200);
			inventory1.setSkuCode("iphone_14");

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
