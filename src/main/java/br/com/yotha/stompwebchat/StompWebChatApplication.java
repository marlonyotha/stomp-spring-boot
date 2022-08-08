package br.com.yotha.stompwebchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StompWebChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(StompWebChatApplication.class, args);
	}

}
