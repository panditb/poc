package org.learning.cache.hazelcast.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.MapConfig;


/**
 * Main class to create Hazelcast instance using java configuration
 */
@Configuration
public class HazelcastConfig {

    @Bean
    public Config hazelCastConfig() {
        Config config = new Config().setInstanceName("hazelcast-instance");

        return config.addMapConfig(new MapConfig().setName("userCache")
                        .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                        .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(500));
                        .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(200));
    }
}
