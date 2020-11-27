package de.hbrs.ia.controller;

import de.hbrs.ia.model.EvaluationRecordEntry;
import de.hbrs.ia.repository.ManagePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesman")
public class EvaluationRecordEntryController {

    @Autowired
    ManagePersonal managePersonal;

    @PostMapping("/{id}/evaluationrecord/{year}/entry")
    public void createRecordEntry(@PathVariable int id, @PathVariable() int year, @RequestBody() EvaluationRecordEntry ere){ //C
        managePersonal.addRecordEntry(id, year, ere);
    }

    @GetMapping("/{id}/evaluationrecord/{year}/entry")
    public List<EvaluationRecordEntry> getAll(@PathVariable int id, @PathVariable int year){ //R
        return managePersonal.getAllEntrys(id, year);
    }

    @GetMapping("/{id}/evaluationrecord/{year}/entry/{name}")
    public EvaluationRecordEntry getRecordEntry(@PathVariable int id, @PathVariable int year, @PathVariable String name){ //R
        return managePersonal.getOneEntry(id, year, name);
    }

    @PutMapping("/{id}/evaluationrecord/{year}/entry")
    public void updateRecordEntry(@PathVariable(required = true) int id, @PathVariable int year, @RequestBody() EvaluationRecordEntry ere){ //U
        managePersonal.updateEntry(id, year, ere);
    }

    @DeleteMapping("/{id}/evaluationrecord/{year}/entry/{name}")
    public void removeRecordEntry(@PathVariable(required = true) int id, @PathVariable int year, @PathVariable String name) { //D
        managePersonal.deleteEntry(id, year, name);
    }
}
