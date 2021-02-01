package org.learning.cache.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main Entry class for Spring boot app
 */
@SpringBootApplication
@EnableCaching
public class HazelcastDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastDemoApplication.class, args);
	}

}
