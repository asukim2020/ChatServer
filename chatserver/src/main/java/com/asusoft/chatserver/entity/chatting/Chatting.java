package com.asusoft.chatserver.entity.chatting;

import com.asusoft.chatserver.auditing.LastModifiedTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Chatting extends LastModifiedTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "CHATTING_ID")
    Long id;

    @NotNull(message = "Chatting message")
    String message;


}
