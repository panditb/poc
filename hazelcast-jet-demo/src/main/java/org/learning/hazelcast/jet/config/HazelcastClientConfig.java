package org.learning.hazelcast.jet.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientClasspathYamlConfig;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Configuration
@Order
public class HazelcastClientConfig  {

    @Value("${hazelcast.client.file}")
    private  String hazlecastFile;
    @Bean
    public HazelcastInstance clientConfig() {
        ClientConfig config  = new ClientClasspathYamlConfig(hazlecastFile);
        return HazelcastClient.newHazelcastClient(config);
    }
}
