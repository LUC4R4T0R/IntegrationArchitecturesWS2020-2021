package de.hbrs.ia.controller;

import de.hbrs.ia.controller.exception.NotFoundException;
import de.hbrs.ia.model.EvaluationRecord;
import de.hbrs.ia.repository.ManagePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesman")
public class EvaluationRecordController {

    @Autowired
    ManagePersonal managePersonal;

    @ResponseStatus(code = HttpStatus.OK, reason = "Your request was a complete success")
    @PostMapping("/{id}/evaluationrecord/{year}")
    public void createRecord(@PathVariable int id, @PathVariable int year){ //C
        managePersonal.addPerformanceRecord(new EvaluationRecord(year), id);
    }

    @GetMapping("/{id}/evaluationrecord/{year}")
    public EvaluationRecord getRecord(@PathVariable() int id, @PathVariable() int year){ //R
        EvaluationRecord er = managePersonal.getOneRecord(id, year);
        if (er == null){
            throw new NotFoundException();
        }
        return er;
    }

    @GetMapping("/{id}/evaluationrecord")
    public List<EvaluationRecord> getAll(@PathVariable() int id){ //R
        try{
            return managePersonal.readEvaluationRecords(id);
        }catch (Exception e){
            throw new NotFoundException();
        }

    }

    @ResponseStatus(code = HttpStatus.OK, reason = "Your request was a complete success")
    @DeleteMapping("/{id}/evaluationrecord/{year}")
    public void deleteRecord(@PathVariable() int id, @PathVariable() int year){ //D
        try{
            managePersonal.deleteEvaluationRecord(id, year);
        }catch (Exception e){
            throw new NotFoundException();
        }

    }
}
