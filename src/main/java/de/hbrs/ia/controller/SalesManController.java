package de.hbrs.ia.controller;

import de.hbrs.ia.repository.ManagePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesManController {

    @Autowired
    ManagePersonal managePersonal;

    @RequestMapping("/test")
    public String test(){
            return "20"+ managePersonal;
    }

}
