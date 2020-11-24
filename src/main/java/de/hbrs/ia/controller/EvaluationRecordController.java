package de.hbrs.ia.controller;

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

    @GetMapping("/{id}/evaluationrecord")
    public List<EvaluationRecord> getAll(@PathVariable(required = true) int id){ //R
        return managePersonal.readEvaluationRecords(id);
    }

    @PostMapping("/{id}/evaluationrecord/{year}")
    public void createRecord(@PathVariable(required = true) int id, @PathVariable(required = true) int year){ //C
        managePersonal.addPerformanceRecord(new EvaluationRecord(year), id);
    }

    @GetMapping("/{id}/evaluationrecord/{year}")
    public EvaluationRecord getRecord(@PathVariable(required = true) int id, @PathVariable(required = true) int year){ //R
        return managePersonal.getOneRecord(id, year);
    }

    @DeleteMapping("/{id}/evaluationrecord/{year}")
    public void removeRecord(@PathVariable(required = true) int id, @PathVariable(required = true) int year){ //D
        managePersonal.deleteEvaluationRecord(id, year);
    }
}
