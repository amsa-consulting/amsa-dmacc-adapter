package com.amsadmacc.amsadmaccadapter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Spriden {

    @Id
    private Long SPRIDEN_PIDM;
    private String SPRIDEN_ID;
    private String SPRIDEN_FIRST_NAME;
}
