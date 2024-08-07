package com.base.msgapplication.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorController {

    @GetMapping("/vendor/sendmsg")
    public String sendMessage(
            @RequestParam String user_name,
            @RequestParam String account_id,
            @RequestParam String pwd,
            @RequestParam String mobile_no,
            @RequestParam String msg,
            @RequestParam String vendor_id) {

        // Validation: Check for missing or invalid parameters
        if (mobile_no.isEmpty() || mobile_no.length() < 10 || msg.isEmpty()) {
            return "REJECTED";
        }

        // Simulate a unique acknowledgment ID
        String uniqueAcknowledgementID = "ACK-" + System.currentTimeMillis();

        // Simulate a successful response
        return "ACCEPTED~~" + uniqueAcknowledgementID;
    }
}

