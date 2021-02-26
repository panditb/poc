package org.learning.hazelcast.jet.config;

import com.hazelcast.client.config.ClientClasspathYamlConfig;
import com.hazelcast.client.config.ClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Configuration
@Order
public class HazelcastClientConfig  {

    @Bean
    public ClientConfig clientConfig() {
        ClientConfig config  = new ClientClasspathYamlConfig("hazelcast-client.yaml");
        return config;
    }
}
