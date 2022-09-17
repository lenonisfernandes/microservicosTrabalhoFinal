package br.com.infnet.appnotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AppnotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppnotasApplication.class, args);
	}

}
