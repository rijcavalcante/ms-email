package com.ms.email.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_EMAIL")
public class EmailModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String emailTo;

    private String emailFrom;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime sendEmailTime;

    @Enumerated(EnumType.STRING)
    private StatusEmailEnum statusEmail;

    public enum StatusEmailEnum {
        SENT, ERROR;
    }
}
