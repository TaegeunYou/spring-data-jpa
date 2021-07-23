package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter //entity에는 setter는 가급적 없도록 만들어야 함(꼭 필요한 부분만 or 별도의 의미있는 비즈니스 네이밍)
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA에서 기본 생성자 필수
@ToString(of = {"id", "username", "age"})   //연관관계인 team도 출력해버리면 무한루프 발생 가능
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)  //모든 xToOne 관계를 지연로딩으로 설정해주기 (지연로딩 : 일단은 Member만 조회하고 Team은 가짜 객체로 갖고 있다가 Team을 조회할 때 DB에서 쿼리를 해온다.)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    //연관관계 메서드 (객체이기 때문에 양쪽에서 바꿔줘야 됨)
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
