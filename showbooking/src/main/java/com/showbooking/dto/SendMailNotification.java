package com.showbooking.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
@ToString
public class SendMailNotification {
    private String receiverMailId;
    private String message;
    private String subject;
}

