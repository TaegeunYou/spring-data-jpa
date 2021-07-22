package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> { //구현 클래스를 알아서 만들어서 autowired에 주입해준다.
}
