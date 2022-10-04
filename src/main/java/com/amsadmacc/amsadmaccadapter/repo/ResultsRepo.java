package com.amsadmacc.amsadmaccadapter.repo;

import com.amsadmacc.amsadmaccadapter.model.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ResultsRepo extends JpaRepository<Results,Integer> {
    @Query(value = "select a.saradap_pidm as pidm, "+
            "spriden_id as bannerID, "+
            "(case when SPBPERS_PREF_FIRST_NAME is not null then SPBPERS_PREF_FIRST_NAME else spriden_first_name end) as firstName, "+
            "spriden_last_name as lastName, "+
            "stvterm_code as termCode, "+
            "stvterm_desc as termDescription,"+
            "a.saradap_appl_no as applicationNumber, "+
            "a.saradap_apst_code as applicationStatusCode, "+
            "stvapst_desc as applicationStatusDescription, "+
            "a.saradap_program_1 as applicationProgram, "+
            "a.saradap_MAJR_CODE_1 as majorCode, "+
            "stvmajr_desc as majorDescription, "+
            "a.saradap_appl_date as applicationDate, "+
            "(trunc(sysdate)-trunc(a.saradap_appl_date)) as daysFromApplication, "+
            "(case when d.goremal_email_address is not null then d.goremal_email_address "+
            "when b.goremal_email_address is not null then b.goremal_email_address "+
            "when c.goremal_email_address is not null then c.goremal_email_address "+
            "else null end) as email, "+
            "sprtele_phone_area||sprtele_phone_number as mobileNumber "+
            "from saradap a "+
            "left outer join stvterm on stvterm_code = a.saradap_term_code_entry "+
            "left outer join spbpers on spbpers_pidm = a.saradap_pidm "+
            "left outer join spriden on spriden_pidm = a.saradap_pidm and spriden_change_ind is null "+
            "left outer join stvmajr on a.saradap_MAJR_CODE_1 = stvmajr_code "+
            "left outer join stvapst on a.saradap_apst_code = stvapst_code "+
            "left outer join goremal d on d.goremal_pidm = a.saradap_pidm AND d.goremal_emal_code = 'EMAL' and d.goremal_status_ind = 'A' and rownum = 1 "+
            "left outer join goremal b on b.goremal_pidm = a.saradap_pidm AND b.goremal_emal_code = 'EMA2' and b.goremal_status_ind = 'A' and rownum = 1 "+
            "left outer join goremal c on c.goremal_pidm = a.saradap_pidm AND c.goremal_emal_code = 'DMAC' and c.goremal_status_ind = 'A' and rownum = 1 "+
            "left outer join sprtele e on e.sprtele_pidm = a.saradap_pidm AND e.sprtele_tele_code = 'CE' and e.sprtele_status_ind is null and rownum = 1 "+
            "where a.saradap_appl_date > sysdate-30 "+
            "AND a.saradap_apst_code = 'C' "+
            "AND stvterm_code = a.saradap_term_code_entry "+
            "AND a.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV') "+
            "and a.saradap_appl_no = (select max(f.saradap_appl_no) "+
            "from saradap f, stvterm g "+
            "where g.stvterm_start_date > sysdate-30 "+
            "AND g.stvterm_code = f.saradap_term_code_entry "+
            "AND f.saradap_apst_code = 'C' "+
            "AND f.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV') "+
            "AND a.saradap_pidm = f.saradap_pidm)" ,nativeQuery = true)
    public List fetchStoredPoc();
    @Query(value = "select COALESCE(json_arrayagg(json_object('pidm' VALUE a.saradap_pidm, "+
            "'bannerID' VALUE spriden_id, "+
            "'firstName' VALUE (case when SPBPERS_PREF_FIRST_NAME is not null then SPBPERS_PREF_FIRST_NAME else spriden_first_name end), "+
            "'lastName' VALUE spriden_last_name, "+
            "'termCode' VALUE stvterm_code, "+
            "'termDescription' VALUE stvterm_desc, "+
            "'applicationNumber' VALUE a.saradap_appl_no, "+
            "'applicationStatusCode' VALUE a.saradap_apst_code, "+
            "'applicationStatusDescription' VALUE stvapst_desc, "+
            "'applicationProgram' VALUE a.saradap_program_1, "+
            "'majorCode' VALUE a.saradap_MAJR_CODE_1, "+
            "'majorDescription' VALUE stvmajr_desc, "+
            "'applicationDate' VALUE to_char(a.saradap_appl_date,'MM/DD/YYYY'), "+
            "'daysFromApplication' VALUE to_number((trunc(sysdate)-trunc(a.saradap_appl_date))), "+
            "'email' VALUE (case when d.goremal_email_address is not null then d.goremal_email_address "+
            "when b.goremal_email_address is not null then b.goremal_email_address "+
            "when c.goremal_email_address is not null then c.goremal_email_address "+
            "else null end), "+
            "'mobileNumber' VALUE sprtele_phone_area||sprtele_phone_number FORMAT JSON)), '[]')  "+
            "from saradap a  "+
            "left outer join stvterm on stvterm_code = a.saradap_term_code_entry  "+
            "left outer join spbpers on spbpers_pidm = a.saradap_pidm  "+
            "left outer join spriden on spriden_pidm = a.saradap_pidm and spriden_change_ind is null  "+
            "left outer join stvmajr on a.saradap_MAJR_CODE_1 = stvmajr_code  "+
            "left outer join stvapst on a.saradap_apst_code = stvapst_code  "+
            "left outer join goremal d on d.goremal_pidm = a.saradap_pidm AND d.goremal_emal_code = 'EMAL' and d.goremal_status_ind = 'A' and rownum = 1 "+
            "left outer join goremal b on b.goremal_pidm = a.saradap_pidm AND b.goremal_emal_code = 'EMA2' and b.goremal_status_ind = 'A' and rownum = 1 "+
            "left outer join goremal c on c.goremal_pidm = a.saradap_pidm AND c.goremal_emal_code = 'DMAC' and c.goremal_status_ind = 'A' and rownum = 1 "+
            "left outer join sprtele e on e.sprtele_pidm = a.saradap_pidm AND e.sprtele_tele_code = 'CE' and e.sprtele_status_ind is null and rownum = 1 "+
            "where a.saradap_appl_date > sysdate-30 "+
            "AND a.saradap_apst_code = 'C' "+
            "AND stvterm_code = a.saradap_term_code_entry "+
            "AND a.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV') "+
            "and a.saradap_appl_no = (select max(f.saradap_appl_no) "+
            "from saradap f, stvterm g "+
            "where g.stvterm_start_date > sysdate-30 "+
            "AND g.stvterm_code = f.saradap_term_code_entry "+
            "AND f.saradap_apst_code = 'C' "+
            "AND f.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV') "+
            "AND a.saradap_pidm = f.saradap_pidm)", nativeQuery = true)
    public String fetchStoredPocAll();

   /*
    @Query(value = "select COALESCE(json_arrayagg(json_object('pidm' VALUE a.saradap_pidm, "+
            "'bannerID' VALUE spriden_id, "+
            "'firstName' VALUE (case when SPBPERS_PREF_FIRST_NAME is not null then SPBPERS_PREF_FIRST_NAME else spriden_first_name end), "+
            "'lastName' VALUE spriden_last_name, "+
            "'termCode' VALUE stvterm_code, "+
            "'termDescription' VALUE stvterm_desc, "+
            "'applicationNumber' VALUE a.saradap_appl_no, "+
            "'applicationStatusCode' VALUE a.saradap_apst_code, "+
            "'applicationStatusDescription' VALUE stvapst_desc, "+
            "'applicationProgram' VALUE a.saradap_program_1, "+
            "'majorCode' VALUE a.saradap_MAJR_CODE_1, "+
            "'majorDescription' VALUE stvmajr_desc, "+
            "'applicationDate' VALUE to_char(a.saradap_appl_date,'MM/DD/YYYY'), "+
            "'daysFromApplication' VALUE to_number((trunc(sysdate)-trunc(a.saradap_appl_date))), "+
            "'email' VALUE (case when d.goremal_email_address is not null then d.goremal_email_address "+
            "when b.goremal_email_address is not null then b.goremal_email_address "+
            "when c.goremal_email_address is not null then c.goremal_email_address "+
            "else null end), "+
            "'mobileNumber' VALUE sprtele_phone_area||sprtele_phone_number FORMAT JSON)), '[]')  "+
            "from saradap a  "+
            "left outer join stvterm on stvterm_code = a.saradap_term_code_entry  "+
            "left outer join spbpers on spbpers_pidm = a.saradap_pidm  "+
            "left outer join spriden on spriden_pidm = a.saradap_pidm and spriden_change_ind is null  "+
            "left outer join stvmajr on a.saradap_MAJR_CODE_1 = stvmajr_code  "+
            "left outer join stvapst on a.saradap_apst_code = stvapst_code  "+
            "left outer join goremal d on d.goremal_pidm = a.saradap_pidm AND d.goremal_emal_code = 'EMAL' and d.goremal_status_ind = 'A' and rownum = 1 "+
            "left outer join goremal b on b.goremal_pidm = a.saradap_pidm AND b.goremal_emal_code = 'EMA2' and b.goremal_status_ind = 'A' and rownum = 1 "+
            "left outer join goremal c on c.goremal_pidm = a.saradap_pidm AND c.goremal_emal_code = 'DMAC' and c.goremal_status_ind = 'A' and rownum = 1 "+
            "left outer join sprtele e on e.sprtele_pidm = a.saradap_pidm AND e.sprtele_tele_code = 'CE' and e.sprtele_status_ind is null and rownum = 1 "+
            "where a.saradap_appl_date > sysdate-365 "+
            "AND a.saradap_apst_code = 'C' "+
            "AND stvterm_code = a.saradap_term_code_entry "+
            "AND a.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV') "+
            "and a.saradap_appl_no = (select max(f.saradap_appl_no) "+
            "from saradap f, stvterm g "+
            "where g.stvterm_start_date > sysdate-365 "+
            "AND g.stvterm_code = f.saradap_term_code_entry "+
            "AND f.saradap_apst_code = 'C' "+
            "AND f.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV') "+
            "AND a.saradap_pidm = f.saradap_pidm) "+
            "AND a.saradap_pidm = ?1 "+
            "AND rownum = 1", nativeQuery = true)
    */
   @Query(value = "select COALESCE(json_arrayagg(json_object('pidm' VALUE a.saradap_pidm, " +
           "'bannerID' VALUE spriden_id,"+
           "'firstName' VALUE (case when SPBPERS_PREF_FIRST_NAME is not null then SPBPERS_PREF_FIRST_NAME else spriden_first_name end),"+
           "'lastName' VALUE spriden_last_name,"+
           "'termCode' VALUE stvterm_code,"+
           "'termDescription' VALUE stvterm_desc,"+
           "'applicationNumber' VALUE a.saradap_appl_no,"+
           "'applicationStatusCode' VALUE a.saradap_apst_code,"+
           "'applicationStatusDescription' VALUE stvapst_desc,"+
           "'applicationProgram' VALUE a.saradap_program_1,"+
           "'majorCode' VALUE a.saradap_MAJR_CODE_1,"+
           "'majorDescription' VALUE stvmajr_desc,"+
           "'applicationDate' VALUE to_char(a.saradap_appl_date,'MM/DD/YYYY'), "+
           "'daysFromApplication' VALUE to_number((trunc(sysdate)-trunc(a.saradap_appl_date))), "+
           "'emailPersonal' VALUE (case when d.goremal_email_address is not null then d.goremal_email_address "+
           "when b.goremal_email_address is not null then b.goremal_email_address "+
           "else null end), "+
           "'emailDMACC' VALUE c.goremal_email_address, "+
           "'mobileNumber' VALUE sprtele_phone_area||sprtele_phone_number FORMAT JSON)), '[]')  "+
           "from saradap a  "+
           "left outer join stvterm on stvterm_code = a.saradap_term_code_entry  "+
           "left outer join spbpers on spbpers_pidm = a.saradap_pidm  "+
           "left outer join spriden on spriden_pidm = a.saradap_pidm and spriden_change_ind is null  "+
           "left outer join stvmajr on a.saradap_MAJR_CODE_1 = stvmajr_code  "+
           "left outer join stvapst on a.saradap_apst_code = stvapst_code  "+
           "left outer join goremal d on d.goremal_pidm = a.saradap_pidm AND d.goremal_emal_code = 'EMAL' and d.goremal_status_ind = 'A' and rownum = 1 "+
           "left outer join goremal b on b.goremal_pidm = a.saradap_pidm AND b.goremal_emal_code = 'EMA2' and b.goremal_status_ind = 'A' and rownum = 1 "+
           "left outer join goremal c on c.goremal_pidm = a.saradap_pidm AND c.goremal_emal_code = 'DMAC' and c.goremal_status_ind = 'A' and rownum = 1 "+
           "left outer join sprtele e on e.sprtele_pidm = a.saradap_pidm AND e.sprtele_tele_code = 'CE' and e.sprtele_status_ind is null and rownum = 1 "+
           "where a.saradap_appl_date > sysdate-365 "+
           "AND a.saradap_apst_code = 'C' "+
           "AND stvterm_code = a.saradap_term_code_entry "+
           "AND a.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV') "+
           "and a.saradap_appl_no = (select max(f.saradap_appl_no) "+
           "from saradap f, stvterm g "+
           "where g.stvterm_start_date > sysdate-365 "+
           "AND g.stvterm_code = f.saradap_term_code_entry "+
           "AND f.saradap_apst_code = 'C' "+
           "AND f.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV') "+
           "AND a.saradap_pidm = f.saradap_pidm) "+
           "AND a.saradap_pidm = ?1 "+
           "AND rownum = 1", nativeQuery = true)
    public String fetchStoredPoc1(Integer pidm);
    @Query(value = "select COALESCE(json_arrayagg(json_object('pidm' VALUE to_char(a.saradap_pidm) FORMAT JSON)), '[]') "+
            "from saradap a  "+
            "left outer join stvterm on stvterm_code = a.saradap_term_code_entry  "+
            "left outer join spriden on spriden_pidm = a.saradap_pidm and spriden_change_ind is null  "+
            "left outer join stvmajr on a.saradap_MAJR_CODE_1 = stvmajr_code  "+
            "left outer join stvapst on a.saradap_apst_code = stvapst_code  "+
            "where a.saradap_appl_date > sysdate-365 "+
            "AND a.saradap_apst_code = 'C' "+
            "AND stvterm_code = a.saradap_term_code_entry "+
            "AND a.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV') "+
            "and a.saradap_appl_no = (select max(f.saradap_appl_no) "+
            "from saradap f, stvterm g "+
            "where g.stvterm_start_date > sysdate-365 "+
            "AND g.stvterm_code = f.saradap_term_code_entry "+
            "AND f.saradap_apst_code = 'C' "+
            "AND f.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV') "+
            "AND a.saradap_pidm = f.saradap_pidm)", nativeQuery = true)
    public String fetchListOfPidms();
}
