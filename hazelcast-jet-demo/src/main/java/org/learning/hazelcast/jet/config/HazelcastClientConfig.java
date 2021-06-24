package org.learning.hazelcast.jet.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientClasspathYamlConfig;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.nio.serialization.ClassDefinition;
import com.hazelcast.nio.serialization.ClassDefinitionBuilder;
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
       /*final ClassDefinition classDefinition = new ClassDefinitionBuilder(1, 7)
                .addIntField("location_num")
                .build();
        config.getSerializationConfig().addClassDefinition(classDefinition);*/
        return HazelcastClient.newHazelcastClient(config);
    }
}
