package com.asusoft.chatserver.auditing;

import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class LastModifiedTimeEntity extends CreateTimeEntity {
    @NotNull
    @LastModifiedDate
    protected LocalDateTime lastModifiedTime;
}
