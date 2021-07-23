package study.datajpa.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass   //엔티티가 아닌 속성만 상속해 주는 클래스
public class BaseEntity extends BaseTimeEntity {

    //등록자
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    //수정자
    @LastModifiedBy
    private String lastModifiedBy;


}
