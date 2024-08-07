package com.base.msgapplication.Entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
public class SendMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long contact_number;
    private String msg;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    private int accountId;
    private int vendorId ;
    private String vendorResponse;
    private Date vendorResponseDate;
}
