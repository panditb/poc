package org.learning.hazelcast.jet.config;


import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.config.JetConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastJetConfig {

    @Bean
    public JetInstance jetInstance() {
        JetConfig jetConfig = JetConfig.loadFromClasspath(this.getClass().getClassLoader(), "hazelcast-client.yaml");
        return Jet.newJetInstance(jetConfig);
    }
}
