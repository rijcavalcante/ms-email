package com.ms.email.consumers;

import com.ms.email.consumers.model.EmailMessageDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailServices;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailServices services;

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void receiveMessage(@Payload EmailMessageDto messageDto) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(messageDto, emailModel);

        services.sendEmail(emailModel);
    }
}
