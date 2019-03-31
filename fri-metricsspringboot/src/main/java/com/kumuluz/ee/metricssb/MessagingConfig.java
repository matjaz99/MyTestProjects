package com.kumuluz.ee.metricssb;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * Konfiguracija sistema za izpostavitev metrik.
 *
 * @author UL FRI
 */
@Configuration
public class MessagingConfig {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        // vsem metrikam dodamo informacije, od kje prihajajo
        // ta koda se pozene samo enkrat ob zagonu aplikacije,
        // zato se instanceId nastavi na enolicen UUID za pognano instanco
        return registry -> registry.config().commonTags(
                "applicationName", "metricsSB",
                "applicationVersion", "1.0.0",
                "applicationEnv", "dev",
                "instanceId", UUID.randomUUID().toString());
    }
}
