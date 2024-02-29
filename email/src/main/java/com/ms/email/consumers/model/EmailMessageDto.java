package com.ms.email.consumers.model;

import java.time.LocalDate;
import java.util.UUID;

public record EmailMessageDto(UUID userId,
                              String name,
                              String emailTo,
                              String subject,
                              String text,
                              LocalDate registerDate) {
}
