package ru.volkov.integration.channel.priority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@SpringBootApplication
@EnableIntegration
public class PriorityApplication implements ApplicationRunner {

    @Autowired
    private PrinterGateway gateway;

    public static void main(String[] args) {
        SpringApplication.run(PriorityApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Future<Message<String>>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Message<String> message = MessageBuilder
                    .withPayload("Printing message payload for - " + i)
                    .setHeader("messageNumber", i)
                    .setPriority(i)
                    .build();

            System.out.println("Sending message - '{" + i + "}'");
            futures.add(this.gateway.print(message));
        }

        for (Future<Message<String>> future : futures) {
            System.out.println(future.get().getPayload());
        }
    }
}
