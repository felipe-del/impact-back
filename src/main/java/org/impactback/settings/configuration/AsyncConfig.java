package org.impactback.settings.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Configuration class for enabling asynchronous processing in the Spring application.
 * <p>
 * This class is marked with the {@link @Configuration} annotation to indicate that it contains
 * Spring configuration. The {@link @EnableAsync} annotation enables Spring's asynchronous method
 * execution capability. The attribute {@code proxyTargetClass = true} ensures that CGLIB proxies
 * are used for classes, which allows for the creation of proxies for classes, not just interfaces.
 * </p>
 *
 * @author Isaac F. B. C.
 * @since 7/10/2024 - 9:48 PM
 */
@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {
}