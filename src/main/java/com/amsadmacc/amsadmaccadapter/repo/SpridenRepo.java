package com.amsadmacc.amsadmaccadapter.repo;

import com.amsadmacc.amsadmaccadapter.model.Spriden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpridenRepo extends JpaRepository<Spriden,Long> {

    @Query(value = "select SPRIDEN_PIDM, SPRIDEN_ID,SPRIDEN_FIRST_NAME from spriden order by spriden_id desc fetch first 10 rows only",nativeQuery = true)
    public List<Spriden> fetchDataWithTop();

    @Query(value = "set serveroutput on", nativeQuery = true)
    public void serverOut();

   // @Query(value = "select a.saradap_pidm as pidm,spriden_id as bannerID from saradap a left outer join spriden on spriden_pidm = a.saradap_pidm and spriden_change_ind is null where a.saradap_appl_date > sysdate-30 AND a.saradap_apst_code = 'C' AND a.saradap_program_1 in ('PY-AG-NAT-RE','PY-ARTS-HUMN','PY-BUSINESS','PY-ENG-MANU','PY-ED-PUBLIC','PY-HEALTH-SC','PY-IT','PY-SCI-MATH','PY-SOC-BEHAV')" ,nativeQuery = true)


}

