package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);    //영속성 컨텍스트에 저장
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        //초기화
        em.flush(); //영속성 컨텍스트에 저장된 것을 DB에 저장
        em.clear();

        //확인
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for (Member member : members) { //공부할 때는 assertThat 말고도 출력해봐도 좋음.
            System.out.println("member = " + member);
            System.out.println("-> member.team = " + member.getTeam());
        }
    }

    @Test
    public void JpaEventBaseEntity() throws Exception {
        //given
        Member member = new Member("member1");
        memberRepository.save(member); //@PrePersist
        
        Thread.sleep(100);
        member.setUsername("member2");
        
        em.flush();
        em.clear();
        
        //when
        Member findMember = memberRepository.findById(member.getId()).get();
        
        //then
        System.out.println("findMember.getCreatedDate = " + findMember.getCreatedDate());
        System.out.println("findMember.getUpdatedDate = " + findMember.getLastModifiedDate());
        System.out.println("findMember.getCreatedBy = " + findMember.getCreatedBy());
        System.out.println("findMember.getLastModifiedBy = " + findMember.getLastModifiedBy());
    }

}