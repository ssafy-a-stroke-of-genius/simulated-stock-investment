package com.ssafy.mockstockinvestment.user;

import com.ssafy.mockstockinvestment.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
