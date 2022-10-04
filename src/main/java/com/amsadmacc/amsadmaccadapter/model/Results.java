package com.amsadmacc.amsadmaccadapter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class Results {

    @Id
   // @Column(name = "pidm")
    private Integer pidm;
  //  @Column(name = "bannerID")
    private String bannerID;
  //  @Column(name = "firstName")
    private String firstName;
  //  @Column(name = "lastName")
    private String lastName;
  //  @Column(name = "termCode")
    private Integer termCode;
  //  @Column(name = "termDescription")
    private String termDescription;
  //  @Column(name = "applicationNumber")
    private Integer applicationNumber;
  //  @Column(name = "applicationStatusCode")
    private String applicationStatusCode;
  //  @Column(name = "applicationStatusDescription")
    private String applicationStatusDescription;
  //  @Column(name = "applicationProgram")
    private String applicationProgram;
  //  @Column(name = "majorCode")
    private String majorCode;
  //  @Column(name = "majorDescription")
    private String majorDescription;
  //  @Column(name = "applicationDate")
    private Date applicationDate;
  //  @Column(name = "daysFromApplication")
    private Integer daysFromApplication;
   // @Column(name = "email")
    private String email;
  //  @Column(name = "mobileNumber")
    private String mobileNumber;
}
