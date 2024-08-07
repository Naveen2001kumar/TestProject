package com.base.msgapplication.Service;


import com.base.msgapplication.Entity.AccountMaster;
import com.base.msgapplication.Entity.SendMsg;
import com.base.msgapplication.Repository.AccountRepo;
import com.base.msgapplication.Repository.SendMsgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SendMsgService {

    @Autowired
    private SendMsgRepo sendMsgRepository;
    @Autowired
    private AccountRepo ar;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 50)
    public void processNewMessages() throws ParseException {
        List<SendMsg> newMessages = sendMsgRepository.findSendMsgsByStatus("new");

        for (SendMsg msg : newMessages) {
            msg.setStatus("inprogress");
            sendMsgRepository.save(msg);
            AccountMaster am = ar.findAccountMasterByAccountId(msg.getAccountId());
            String name = am.getUserName();
            String pass = am.getPassword();
            // Call the vendor API
            String vendorApiUrl = String.format(
                    "http://localhost:8080/vendor/sendmsg?user_name=%s&account_id=%s&pwd=%s&mobile_no=%s&msg=%s&vendor_id=%s",
                    name, msg.getAccountId(), pass,
                    msg.getContact_number(), msg.getMsg(), msg.getVendorId());

            String response = restTemplate.getForObject(vendorApiUrl, String.class);

            // Update the vendor response
            msg.setVendorResponse(response);
            if(response.charAt(0)=='A') {
                msg.setStatus("sent");
                System.out.println("sent Succefully");
            }
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date today = new Date();
            Date date = formatter.parse(formatter.format(today));
            msg.setVendorResponseDate(date);
            sendMsgRepository.save(msg);
        }
    }

}

