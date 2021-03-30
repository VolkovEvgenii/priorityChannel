package ru.volkov.integration.channel.priority;

import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class PrintService {

    @ServiceActivator(
            inputChannel = "inputChannel",
    poller = @Poller(fixedRate = "5000", maxMessagesPerPoll = "1"))
    public Message<String> print(Message<String> message) {

        System.out.println("Print service: '{" + message.getPayload() + "}'");
        int messageNumber = (int) message.getHeaders().get("messageNumber");
        return MessageBuilder.withPayload("Sending reply for message - '{" + messageNumber + "}'").build();
    }
}
