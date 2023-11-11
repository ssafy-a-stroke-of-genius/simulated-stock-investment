package com.ssafy.mockstockinvestment.account;

import com.ssafy.mockstockinvestment.user.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select a from accounts a where a.studentIdFK=?1 and a.createdAt=?2 and a.assetType=?3")
    Account findAccount(Student student, String date,String company);
}
