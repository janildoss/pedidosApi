package com.api.pedidosApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
public class PedidosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosApiApplication.class, args);
		System.out.println("jajao da bahia");
	}
	//@GetMapping("/")
	//public String index(){
	//	return "Olá Mundo!";
//	}

}
