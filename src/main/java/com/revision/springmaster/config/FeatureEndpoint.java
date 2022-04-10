package com.revision.springmaster.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {
    private final Map<String, Feature> featureMap = new ConcurrentHashMap<>();
    private static final Feature enabledFeature = new Feature(true);
    private static final Feature disabledFeature = new Feature(true);

    public FeatureEndpoint() {
        featureMap.put("Department", enabledFeature);
        featureMap.put("User", disabledFeature);
        featureMap.put("Authentication", disabledFeature);
    }

    @ReadOperation
    public Map<String, Feature> features() {
        return featureMap;
    }

    @ReadOperation
    public Feature feature(@Selector String featureName) {
        return featureMap.get(featureName);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class Feature {
        private boolean isEnabled;
    }
}
