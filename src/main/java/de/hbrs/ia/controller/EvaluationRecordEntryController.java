package de.hbrs.ia.controller;

import de.hbrs.ia.model.EvaluationRecord;
import de.hbrs.ia.model.EvaluationRecordEntry;
import de.hbrs.ia.model.SalesManRecord;
import de.hbrs.ia.repository.ManagePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesman")
public class EvaluationRecordEntryController {

    @Autowired
    ManagePersonal managePersonal;

    @GetMapping("/{id}/evaluationrecord/{year}/entry")
    public List<EvaluationRecordEntry> getAll(@PathVariable(required = true) int id, @PathVariable(required = true) int year){ //R
        EvaluationRecord record = managePersonal.getOneRecord(id, year);
        if(record != null) return record.getPerformance();
        return null;
    }

    @PostMapping("/{id}/evaluationrecord/{year}/entry/{name}")
    public void createRecordEntry(@PathVariable(required = true) int id, @PathVariable(required = true) int year, @PathVariable(required = true) String name, @RequestParam(required = true) int target, @RequestParam(required = true) int actual){ //C
        EvaluationRecord record = managePersonal.getOneRecord(id, year);
        if(record != null) {
            record.getPerformance().add(new EvaluationRecordEntry(target, actual, name));
            managePersonal.updateEvaluationRecord(new SalesManRecord(id, record));
        }
    }

    @PutMapping("/{id}/evaluationrecord/{year}/entry/{name}")
    public void updateRecordEntry(@PathVariable(required = true) int id, @PathVariable(required = true) int year, @PathVariable(required = true) String name, @RequestParam(required = true) int target, @RequestParam(required = true) int actual){ //U
        EvaluationRecord record = managePersonal.getOneRecord(id, year);
        if(record != null) {
            List<EvaluationRecordEntry> entries = record.getPerformance();
            int index = entries.indexOf(new EvaluationRecordEntry(0 ,0 ,name));
            if (index >= 0) {
                EvaluationRecordEntry entry = entries.get(index);
                entry.setActual(actual);
                entry.setExpected(target);
                managePersonal.updateEvaluationRecord(new SalesManRecord(id, record));
            }
        }
    }

    @GetMapping("/{id}/evaluationrecord/{year}/entry/{name}")
    public EvaluationRecordEntry getRecordEntry(@PathVariable(required = true) int id, @PathVariable(required = true) int year, @PathVariable(required = true) String name){ //R
        EvaluationRecord record = managePersonal.getOneRecord(id, year);
        if(record != null) {
            List<EvaluationRecordEntry> entries = managePersonal.getOneRecord(id, year).getPerformance();
            int index = entries.indexOf(new EvaluationRecordEntry(0 ,0 ,name));
            if (index >= 0) return entries.get(index);
        }
        return null;
    }

    @DeleteMapping("/{id}/evaluationrecord/{year}/entry/{name}")
    public void removeRecordEntry(@PathVariable(required = true) int id, @PathVariable(required = true) int year, @PathVariable(required = true) String name){ //D
        EvaluationRecord record = managePersonal.getOneRecord(id, year);
        List<EvaluationRecordEntry> entries = record.getPerformance();
        int index = entries.indexOf(new EvaluationRecordEntry(0 ,0 ,name));
        if(index >= 0){
            entries.remove(index);
            record.setPerformance(entries);
            managePersonal.updateEvaluationRecord(new SalesManRecord(id, record));
        }
    }
}
