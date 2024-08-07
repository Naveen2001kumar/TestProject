package com.base.msgapplication.Repository;

import com.base.msgapplication.Entity.AccountMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository< AccountMaster , Integer> {

    boolean existsByUserName(String user_name);
    boolean existsByPassword(String password);
    AccountMaster findAccountMasterByUserName(String username);
    AccountMaster findAccountMasterByAccountId(int id);
}
