package study.datajpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
public class Item extends BaseTimeEntity implements Persistable<String> {


    /**
     * 식별자(id)에 @GenerateValue 전략이 아닌 직접 설정해 주어야 할 경우
     * save()를 해주면 식별자가 이미 있기 때문에 persist가 아닌 merge가 호출되기 때문에
     * 새로운 엔티티 확인 여부를 직접 구현하는 것이 효율적이다. (Persistable 인터페이스 사용)
     */

    @Id
    private String id;

    public Item(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return (getCreatedDate() == null);
    }
}
