package com.base.msgapplication.Repository;

import com.base.msgapplication.Entity.SendMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SendMsgRepo extends JpaRepository< SendMsg , Integer> {

    List<SendMsg> findSendMsgsByDateTimeBetweenAndStatus(Date from , Date to , String status);
    List<SendMsg> findSendMsgsByStatus(String status);
}
