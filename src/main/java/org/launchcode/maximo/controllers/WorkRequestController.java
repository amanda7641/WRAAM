package org.launchcode.maximo.controllers;

import org.launchcode.maximo.models.WorkRequest;
import org.launchcode.maximo.models.data.WorkRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("workrequest")
public class WorkRequestController {

    @Autowired
    private WorkRequestDao workRequestDao;

    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("title", "Work Request");

        return "workRequest/index";
    }

    @RequestMapping(value="add")
    public String displayAddForm(Model model){

        model.addAttribute("title", "New Work Request");
        model.addAttribute(new WorkRequest());

        return "workRequest/add";
    }

}
