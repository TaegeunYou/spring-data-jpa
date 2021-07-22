package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //메서드 이름으로 쿼리 생성
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    //find...By, findTop3...By
    List<Member> findTop3HelloBy();

    //NamedQuery
//    List<Member> findByUsername(@Param("username") String username);

    /**
     * 메소드에 쿼리 정의하기
     */

    //엔티티 조회
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    //단순한 값 조회
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //DTO로 직접 조회 -> new, 위치, 생성자 사용
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    //단순한 값 조회 + 컬렉션 파라미터 바인딩
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    /**
     * 반환 타입
     */

    List<Member> findListByUsername(String username);          //컬렉션 //없을 때는 빈 컬렉션 반환
    Member findMemberByUsername(String username);              //단건   //없을 때는 null  //단건인데 2개 반환되면 에러
    Optional<Member> findOptionalByUsername(String username);  //단건   //없을 때는 empty

    /**
     * 페이징
     */

    Page<Member> findByAge(int age, Pageable pageable); // content 쿼리 + count 쿼리
//    Slice<Member> findByAge(int age, Pageable pageable);  // ex) 모바일에서 아래로 화면 내려서 더보기 기능 (count 쿼리를 날리지 않음)
//    List<Member> findByAge(int age, Pageable pageable);

    // join해서 조회하는 경우 count 쿼리문을 따로 작성하여 count 쿼리 성능 최적화
    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m) from Member m")
    Page<Member> findTestByAge(int age, Pageable pageable);

}





















