package com.base.msgapplication.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Data
@Getter
@Setter
public class AccountMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    @Column(unique = true)
    private String userName;
    private String password;
    private String status;
    private Date createDate ;
    private Date  lastUpdateDate;
    private Long contact_number;
    private String email;
    private String userType;

}
