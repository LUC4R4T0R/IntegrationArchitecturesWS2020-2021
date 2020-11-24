package de.hbrs.ia.controller;

import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.repository.ManagePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesman")
public class SalesManController {

    @Autowired
    ManagePersonal managePersonal;

    @PostMapping("/{id}")
    public void createSalesman(@PathVariable() int id, @RequestParam() String vorname, @RequestParam() String nachname){
            managePersonal.createSalesMan(new SalesMan(vorname,nachname,id));
    }

    @GetMapping("/{id}")
    public SalesMan readSalesMan(@PathVariable() int id){
        SalesMan a = managePersonal.readSalesMan(id);
        return a;
    }

    @GetMapping()
    public List<SalesMan> getAllSalesman(){
        return managePersonal.getAllSalesMen();
    }

    @PutMapping("/{id}")
    public void updtaeSalesman(@PathVariable() int id, @RequestParam() String vorname, @RequestParam() String nachname){
        managePersonal.updateSalesMen(new SalesMan(vorname,nachname,id));
    }

    @DeleteMapping("/{id}")
    public void deleteSalesman(@PathVariable() int id){
        managePersonal.deleteSalesMen(id);
    }

}
