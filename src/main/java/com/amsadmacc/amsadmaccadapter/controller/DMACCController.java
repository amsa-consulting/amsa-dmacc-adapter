package com.amsadmacc.amsadmaccadapter.controller;
import com.amsadmacc.amsadmaccadapter.model.Spriden;
import com.amsadmacc.amsadmaccadapter.repo.ResultsRepo;
import com.amsadmacc.amsadmaccadapter.repo.SpridenRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
public class DMACCController {
    private final EntityManager em;

    public DMACCController(EntityManager em) {
        /*  15 */     this.em = em;
        /*     */   }

   // @Autowired
   // private PathwaysJourneyRepo pathwaysJourneyRepo;
/*
    @GetMapping(path = "/pathwaysjourney", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<PathwaysJourney> getAllPathways() {
        log.info("Came inside getAllPathways");
        return pathwaysJourneyRepo.findAll();
    }
}
*/
    @Autowired
    SpridenRepo spridenRepo;
    @Autowired
    ResultsRepo resultsRepo;

   @Query(value = "set serveroutput on", nativeQuery = true)
    public void serverOut(){
     // return "Turned On";
    }
   @GetMapping("/test/{id}")
   public Optional<Spriden> getAllTest(@PathVariable Long id){
       return spridenRepo.findById(id);
   }
    @GetMapping("/test")
    public List<Spriden> getAll(){
        // return spridenRepo.findById(id);
        return spridenRepo.fetchDataWithTop();
    }
   @PostMapping("/pathwaysjourney1")
   @ResponseBody
   public List getAllPathways1() {
       spridenRepo.serverOut();
       StoredProcedureQuery proc = this.em.createNamedStoredProcedureQuery("pathwaysjourney_sp");
       System.out.println("===>>> start exec");
       //String output=serverOut();
       //log.info("Output {}",output);
       proc.execute();
       System.out.println("===>>> end exec");

       return proc.getResultList();
   }
    //@GetMapping(value = "/pathwaysjourney",produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/pathwaysjourney")
    @ResponseBody
    public List getAllPathways() {
        return resultsRepo.fetchStoredPoc();

    }
    //@GetMapping(value = "/pathwaysjourney",produces = MediaType.APPLICATION_JSON_VALUE)
   /*
    @GetMapping("/pathwaysjourneyJson")
    @ResponseBody
    public List<Results> getAllPathwaysJson() {

        ArrayList<Results> resultsArrayList = new ArrayList<Results>();
       List<Results> resultsList= resultsRepo.fetchStoredPoc();

       for(int i=0; i < resultsList.size();i++){
           resultsArrayList.add(resultsList.get(i));
       }
      return resultsArrayList;

      //  return resultsList;
    }*/
    @GetMapping("/pathwaysjourneyNativeAll")
    @ResponseBody
    public String getAllPathwaysNative() {
       //Results results=new Results();
        String result= resultsRepo.fetchStoredPocAll().toString();
        result.replace("\\","");
        return result;
        /*
        Results body=new Results();
        body.setPidm(results.getPidm());
        body.setBannerID(results.getBannerID());
        body.setFirstName(results.getFirstName());
        body.setLastName(results.getLastName());
        body.setTermCode(results.getTermCode());
        body.setTermDescription(results.getTermDescription());
        body.setApplicationNumber(results.getApplicationNumber());
        body.setApplicationStatusCode(results.getApplicationStatusCode());
        body.setApplicationStatusDescription(results.getApplicationStatusDescription());
        body.setApplicationProgram(results.getApplicationProgram());
        body.setMajorCode(results.getMajorCode());
        body.setMajorDescription(results.getMajorDescription());
        body.setApplicationDate(results.getApplicationDate());
        body.setDaysFromApplication(results.getDaysFromApplication());
        body.setEmail(results.getEmail());
        body.setMobileNumber(results.getMobileNumber());

     return body;
*/
    }
    @GetMapping(value = "/pathwaysjourneyNative1/{pidm}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllPathwaysNative1(@PathVariable Integer pidm) {
        String result= resultsRepo.fetchStoredPoc1(pidm).toString();
        result.replace("\\","");
        return result;
    }
    @GetMapping(value = "/pathwaysjourneyOnePidms",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllPidms() {
        String result= resultsRepo.fetchListOfPidms().toString();
        result.replace("\\","");
        return result;
    }
}
