package com.kakaluote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(value = {"com.kakaluote.web.filter"})
public class MemoryChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoryChatApplication.class, args);
	}
}
