package org.learning.hazelcast.jet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HazelcastJetDemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(HazelcastJetDemoApplication.class, args);
	}

}
