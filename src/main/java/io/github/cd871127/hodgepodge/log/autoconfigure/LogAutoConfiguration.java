package io.github.cd871127.hodgepodge.log.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "amc.log", value = "enable", havingValue = "true", matchIfMissing = true)
@ComponentScan("io.github.cd871127.hodgepodge.log")
public class LogAutoConfiguration {
}
