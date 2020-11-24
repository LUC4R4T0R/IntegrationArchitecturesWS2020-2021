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

    @PostMapping()
    public void createSalesman(@RequestParam(name = "salesman")SalesMan salesman){
            managePersonal.createSalesMan(salesman);
    }

    @GetMapping("/{id}")
    public SalesMan readSalesMan(@PathVariable() int id){
        SalesMan a = managePersonal.readSalesMan(id);
        return a;
    }

    @GetMapping()
    public List<SalesMan> querySalesman(@RequestParam(name = "key", defaultValue = "") String key, @RequestParam(name = "attribute", defaultValue = "") String attribute){
        if (key.equals("") && attribute.equals("")){
            return managePersonal.getAllSalesMen();
        }
        return managePersonal.querySalesMan(attribute,key);
    }

    @PutMapping()
    public void updtaeSalesman(@RequestParam(name = "salesman")SalesMan salesman){
        managePersonal.updateSalesMen(salesman);
    }

    @DeleteMapping("/{id}")
    public void deleteSalesman(@PathVariable() int id){
        managePersonal.deleteSalesMen(id);
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable() int id){
        managePersonal.readSalesMan(17);
        return "hallo" +id;
    }
}
