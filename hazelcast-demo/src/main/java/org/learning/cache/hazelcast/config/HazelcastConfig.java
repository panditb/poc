//package org.learning.cache.hazelcast.config;
//
//import com.hazelcast.config.Config;
//import com.hazelcast.config.MapConfig;
//import com.hazelcast.config.MapStoreConfig;
//import org.learning.cache.hazelcast.store.LocationStore;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * Main class to create Hazelcast instance using java configuration
// */
//@Configuration
//public class HazelcastConfig {
//
//    @Bean
//    public Config hazelCastConfig() {
//        Config config = new Config().setInstanceName("hazelcast-instance");
//        MapConfig mapConfig = config.getMapConfig("location");
//        MapStoreConfig mapStoreConfig = new MapStoreConfig();
//        mapStoreConfig.setEnabled(true);
//        mapStoreConfig.setClassName(LocationStore.class.getName());
//        mapConfig.setMapStoreConfig(mapStoreConfig);
//        return  config;
//
//        //config.getManagementCenterConfig().setEnabled(true);
//        //config.getManagementCenterConfig().setUrl("http://localhost:8081/mancenter");
//
//        /*return config.addMapConfig(new MapConfig().setName("location")
//                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
//                .setEvictionPolicy(EvictionPolicy.LRU)
//                .setTimeToLiveSeconds(500));*/
//    }
//
//}
