package ru.volkov.integration.channel.priority;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@MessagingGateway
@Component
public interface PrinterGateway {

    @Gateway(requestChannel = "inputChannel")
    Future<Message<String>> print(Message<?> message);
}
