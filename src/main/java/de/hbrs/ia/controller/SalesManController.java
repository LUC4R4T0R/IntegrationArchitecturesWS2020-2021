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
    public void createSalesman(@RequestBody() SalesMan s){ //C
        managePersonal.createSalesMan(s);
    }

    @GetMapping("/{id}")
    public SalesMan readSalesMan(@PathVariable() int id){ //R
        return managePersonal.readSalesMan(id);
    }

    @GetMapping()
    public List<SalesMan> getMoreSalesman(@RequestParam(name = "key", defaultValue = "",required = false) String key, @RequestParam(name = "attribute", defaultValue = "",required = false) String attribute){ //R
        if (key.equals("") && attribute.equals("")){
            return managePersonal.getAllSalesMen();
        } else{
            return managePersonal.querySalesMan(attribute,key);
        }
    }

    @PutMapping()
    public void updtaeSalesman(@RequestBody() SalesMan s){ //U
        managePersonal.updateSalesMen(s);
    }

    @DeleteMapping("/{id}")
    public void deleteSalesman(@PathVariable() int id){ //D
        managePersonal.deleteSalesMen(id);
    }

}
