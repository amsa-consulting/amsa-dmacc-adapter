package com.amsadmacc.amsadmaccadapter.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@NamedStoredProcedureQuery(
        name = "pathwaysjourney_sp",
        procedureName = "AMSA_Marketing_Cloud.pathways_journey"        /*parameters = {
                @StoredProcedureParameter(name = "pidm", mode = ParameterMode.OUT, type = Integer.class),
                @StoredProcedureParameter(name = "bannerID", mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(name = "firstName", mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(name = "lastName", mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(name = "termCode", mode = ParameterMode.OUT, type = Integer.class),
                @StoredProcedureParameter(name = "termDescription", mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(name = "applicationNumber", mode = ParameterMode.OUT, type = Integer.class),
                @StoredProcedureParameter(name = "applicationStatusCode", mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(name = "applicationStatusDescription", mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(name = "applicationProgram", mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(name = "majorCode", mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(name = "majorDescription", mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(name = "applicationDate", mode = ParameterMode.OUT, type = Date.class),
                @StoredProcedureParameter(name = "daysFromApplication", mode = ParameterMode.OUT, type = Integer.class),
                @StoredProcedureParameter(name = "email", mode = ParameterMode.OUT, type = String.class),
                @StoredProcedureParameter(name = "mobileNumber", mode = ParameterMode.OUT, type = String.class)

        }
        */
)
public class PathwaysJourney implements Serializable  {
        @Id
        private long id;
        private Integer pidm;
        private String bannerID;
        private String firstName;
        private String lastName;
        private Integer termCode;
        private String termDescription;
        private Integer applicationNumber;
        private String applicationStatusCode;
        private String applicationStatusDescription;
        private String applicationProgram;
        private String majorCode;
        private String majorDescription;
        private Date applicationDate;
        private Integer daysFromApplication;
        private String email;
        private String mobileNumber;
}

