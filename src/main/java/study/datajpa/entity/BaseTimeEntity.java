package study.datajpa.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass   //엔티티가 아닌 속성만 상속해 주는 클래스
@Getter
public class BaseTimeEntity {

    //등록일
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    //수정일
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
