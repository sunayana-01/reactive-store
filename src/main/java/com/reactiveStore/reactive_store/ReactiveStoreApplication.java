package com.reactiveStore.reactive_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.tools.agent.ReactorDebugAgent;


@SpringBootApplication
public class ReactiveStoreApplication {

	public static void main(String[] args) {
		ReactorDebugAgent.init();
		SpringApplication.run(ReactiveStoreApplication.class, args);
	}

}
