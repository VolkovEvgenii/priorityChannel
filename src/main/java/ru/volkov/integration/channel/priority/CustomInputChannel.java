package ru.volkov.integration.channel.priority;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.dsl.MessageChannels;

import java.util.Comparator;

@Configuration
public class CustomInputChannel {

    @Bean
    public PriorityChannel inputChannel() {
        return MessageChannels
                .priority()
                .capacity(10)
                .comparator(customMessageComparator())
                .get();
    }

    @Bean
    public Comparator customMessageComparator(){
        return new CustomMessageComparator();
    }
}
