package com.asusoft.chatserver.entity.chatting.dto;

import com.asusoft.chatserver.entity.chatting.Chatting;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChattingReadDto {
    Long id;
    String message;
    Long memberId;
    Long chatRoomId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime createdTime;
    public ChattingReadDto(Chatting chatting) {
        id = chatting.getId();
        message = chatting.getMessage();
        memberId = chatting.getMember().getId();
        chatRoomId = chatting.getChatRoom().getId();
        createdTime = chatting.getCreatedTime();
    }
}
