package com.base.msgapplication.Controller;

import com.base.msgapplication.Dto.SummaryReport;
import com.base.msgapplication.Entity.AccountMaster;
import com.base.msgapplication.Entity.SendMsg;
import com.base.msgapplication.Repository.AccountRepo;
import com.base.msgapplication.Repository.SendMsgRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    private AccountMaster accountMaster;
    @Autowired
    AccountRepo ar;
    @Autowired
    SendMsgRepo sr;
    private String AccountName;
    @RequestMapping("/")
    public String getLogin()
    {
        return "index.html";
    }
    @PostMapping("/submit-message")
    public String SetMsg(@RequestParam String mobileNumber, @RequestParam String message , Model model) throws ParseException {
        Long num = Long.parseLong(mobileNumber);
        SendMsg sendMsg = new SendMsg();
        sendMsg.setMsg(message);
        sendMsg.setContact_number(num);
        //date Formatter
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        Date date = formatter.parse(formatter.format(today));

        sendMsg.setDateTime(date);
        sendMsg.setLastUpdatedDate(date);
        sendMsg.setVendorResponse(null);
        sendMsg.setVendorResponseDate(null);
        sendMsg.setVendorId(1);
        sendMsg.setStatus("new");
        AccountMaster am = (AccountMaster) ar.findAccountMasterByUserName(AccountName);
        sendMsg.setAccountId(am.getAccountId());
        sr.save(sendMsg);
        model.addAttribute("successMessage" , "Message Sent Successfully");
        return "sendMessage.html";
    }

    @PostMapping("/login")
    public String getHome(@RequestParam String userid , @RequestParam String password , Model model)
    {
        AccountName = userid;
        if (isValidUser(userid,password)) {
            return "home.html";
        } else {
            model.addAttribute("errorMessage", "Invalid User ID or Password");
            return "index";
        }
    }
    @PostMapping("/Summary-submit-data")
    public String SummarySumbition(@RequestParam String FromDate , @RequestParam String toDate , @RequestParam String status , Model model) throws ParseException {
        List<SendMsg> msg = sr.findSendMsgsByDateTimeBetweenAndStatus(new SimpleDateFormat("yyyy-MM-dd").parse(FromDate) , new SimpleDateFormat("yyyy-MM-dd").parse(toDate) , status);
        List<SummaryReport> li = new ArrayList<>();
        for(int i=0 ; i<msg.size() ; i++)
        {
            SummaryReport sr1 = new SummaryReport();
            sr1.setId(i);
            sr1.setDate(msg.get(i).getDateTime());
            sr1.setVendorName(String.valueOf(msg.get(i).getVendorId()));
            sr1.setStatus(msg.get(i).getStatus());
            sr1.setCount(1);
            li.add(sr1);
        }
          model.addAttribute("reports" , li);
        return "summary-report";
    }
    @PostMapping("/Detail-submit-data")
    public String DetailSumbition(@RequestParam String FromDate , @RequestParam String toDate , @RequestParam String status , Model model) throws ParseException {
        List<SendMsg> msg = sr.findSendMsgsByDateTimeBetweenAndStatus(new SimpleDateFormat("yyyy-MM-dd").parse(FromDate) , new SimpleDateFormat("yyyy-MM-dd").parse(toDate) , status);
        model.addAttribute("reports" , msg);
        return "DetailReportTable.html";
    }
    private boolean isValidUser(String username, String password) {
        return ar.existsByUserName(username) && ar.existsByPassword(password);
    }
    @RequestMapping("/send-msg")
    public String getSendMessage()
    {
        return "sendMessage.html";
    }
    @RequestMapping("/summary-report")
    public String getSummaryReport()
    {
        return "SummaryReport.html";
    }
    @RequestMapping("/detail-report")
    public String getDetailReport()
    {
        return "DetailReport.html";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response ) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "index.html";
    }
}
