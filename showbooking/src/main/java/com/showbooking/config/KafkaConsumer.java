package com.showbooking.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.showbooking.dto.BookingNotificationRequest;
import com.showbooking.dto.SendMailNotification;
import com.showbooking.service.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
public class KafkaConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SendMailService sendMailService;

    @KafkaListener(topics = ShowConstants.TICKET_BOOKING_TOPIC, groupId = "booking_notification")
    public void sendNotification(ConsumerRecord receivedMessage) throws JsonProcessingException {
        SendMailNotification d = objectMapper.readValue(receivedMessage.value().toString(), SendMailNotification.class);
        sendMailService.sendMail(d);
    }
}
