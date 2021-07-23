package study.datajpa.repository;

import lombok.Value;

public interface UsernameOnly {

    //close projections
    String getUsername();

    //open projections
//    @Value("#{target.username + ' ' + target.age}")
//    String getUsername();
}
