package com.example.controllers;

import com.example.models.ChatMessage;
import com.example.models.MessageEntity;
import com.example.repositories.MessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController {

    private final MessageRepository messageRepository;

    public ChatController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        message.setTimestamp(timestamp);

        // Save to database
        MessageEntity msg = new MessageEntity();
        msg.setSender(message.getSender());
        msg.setReceiver(message.getReceiver());
        msg.setContent(message.getContent());
        msg.setTimestamp(new Date());

        messageRepository.save(msg);

        return message;
    }
}
