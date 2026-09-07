package by.shakhau.ps.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.BatchMessageConverter;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    @Bean
    public RecordMessageConverter multiTypeConverter() {
        return new ByteArrayJsonMessageConverter();
    }

    @Bean
    public BatchMessageConverter batchConverter(RecordMessageConverter multiTypeConverter) {
        return new BatchMessagingMessageConverter(multiTypeConverter);
    }

    @Bean
    public DefaultErrorHandler errorHandler() {
        var errorHandler = new DefaultErrorHandler(new FixedBackOff(0L, 0L));
        errorHandler.setAckAfterHandle(false);
        return errorHandler;
    }
}
