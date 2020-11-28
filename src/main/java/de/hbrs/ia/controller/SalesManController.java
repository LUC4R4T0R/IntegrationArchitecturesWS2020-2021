package de.hbrs.ia.controller;

import de.hbrs.ia.controller.exception.NotFoundException;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.repository.ManagePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jbrill2s, lrinh2s
 * <p>
 * Controller to create, read, update and delete salesman-objects.
 */
@RestController
@RequestMapping("/salesman")
public class SalesManController {

    @Autowired
    ManagePersonal managePersonal;

    @ResponseStatus(code = HttpStatus.OK, reason = "Your request was a complete success")
    @PostMapping()
    public void createSalesman(@RequestBody() SalesMan s) { //C
        managePersonal.createSalesMan(s);
    }

    @GetMapping("/{id}")
    public SalesMan readSalesMan(@PathVariable() int id) { //R
        try {
            return managePersonal.readSalesMan(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @GetMapping()
    public List<SalesMan> getMoreSalesman(@RequestParam(name = "key", defaultValue = "", required = false) String key, @RequestParam(name = "attribute", defaultValue = "", required = false) String attribute) { //R
        try {
            if (key.equals("") && attribute.equals("")) {
                return managePersonal.getAllSalesMen();
            } else {
                return managePersonal.querySalesMan(attribute, key);
            }
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @ResponseStatus(code = HttpStatus.OK, reason = "Your request was a complete success")
    @PutMapping()
    public void updtaeSalesman(@RequestBody() SalesMan s) { //U
        try {
            managePersonal.updateSalesMen(s);
        } catch (Exception e) {
            throw new NotFoundException();
        }

    }

    @ResponseStatus(code = HttpStatus.OK, reason = "Your request was a complete success")
    @DeleteMapping("/{id}")
    public void deleteSalesman(@PathVariable() int id) { //D
        try {
            managePersonal.deleteSalesMen(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }

    }

}
