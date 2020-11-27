package de.hbrs.ia.controller;

import de.hbrs.ia.controller.exception.NotFoundException;
import de.hbrs.ia.model.EvaluationRecord;
import de.hbrs.ia.repository.ManagePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesman")
public class EvaluationRecordController {

    @Autowired
    ManagePersonal managePersonal;


    @PostMapping("/{id}/evaluationrecord/{year}")
    public void createRecord(@PathVariable int id, @PathVariable int year){ //C
        managePersonal.addPerformanceRecord(new EvaluationRecord(year), id);
    }

    @GetMapping("/{id}/evaluationrecord/{year}")
    public EvaluationRecord getRecord(@PathVariable(required = true) int id, @PathVariable(required = true) int year){ //R
        EvaluationRecord er = managePersonal.getOneRecord(id, year);
        if (er == null){
            throw new NotFoundException();
        }
        return er;
    }

    @GetMapping("/{id}/evaluationrecord")
    public List<EvaluationRecord> getAll(@PathVariable(required = true) int id){ //R
        try{
            return managePersonal.readEvaluationRecords(id);
        }catch (Exception e){
            throw new NotFoundException();
        }

    }

    @DeleteMapping("/{id}/evaluationrecord/{year}")
    public void deleteRecord(@PathVariable(required = true) int id, @PathVariable(required = true) int year){ //D
        try{
            managePersonal.deleteEvaluationRecord(id, year);
        }catch (Exception e){
            throw new NotFoundException();
        }

    }
}
