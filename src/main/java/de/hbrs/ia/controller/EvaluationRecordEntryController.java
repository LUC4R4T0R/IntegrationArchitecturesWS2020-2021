package de.hbrs.ia.controller;

import de.hbrs.ia.controller.exception.NotFoundException;
import de.hbrs.ia.model.EvaluationRecordEntry;
import de.hbrs.ia.repository.ManagePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jbrill2s. lringh2s
 * <p>
 * Controller to create, read, update and delete evaluationrecordentry-objects.
 */
@RestController
@RequestMapping("/salesman")
public class EvaluationRecordEntryController {

    @Autowired
    ManagePersonal managePersonal;

    @ResponseStatus(code = HttpStatus.OK, reason = "Your request was a complete success")
    @PostMapping("/{id}/evaluationrecord/{year}/entry")
    public void createRecordEntry(@PathVariable int id, @PathVariable() int year, @RequestBody() EvaluationRecordEntry ere) { //C
        managePersonal.addRecordEntry(id, year, ere);
    }

    @GetMapping("/{id}/evaluationrecord/{year}/entry")
    public List<EvaluationRecordEntry> getAll(@PathVariable int id, @PathVariable int year) { //R
        try {
            return managePersonal.getAllEntrys(id, year);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GetMapping("/{id}/evaluationrecord/{year}/entry/{name}")
    public EvaluationRecordEntry getRecordEntry(@PathVariable int id, @PathVariable int year, @PathVariable String name) { //R
        try {
            return managePersonal.getOneEntry(id, year, name);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @ResponseStatus(code = HttpStatus.OK, reason = "Your request was a complete success")
    @PutMapping("/{id}/evaluationrecord/{year}/entry")
    public void updateRecordEntry(@PathVariable() int id, @PathVariable int year, @RequestBody() EvaluationRecordEntry ere) { //U
        try {
            managePersonal.updateEntry(id, year, ere);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @ResponseStatus(code = HttpStatus.OK, reason = "Your request was a complete success")
    @DeleteMapping("/{id}/evaluationrecord/{year}/entry/{name}")
    public void removeRecordEntry(@PathVariable() int id, @PathVariable int year, @PathVariable String name) { //D
        try {
            managePersonal.deleteEntry(id, year, name);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}
