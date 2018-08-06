package org.launchcode.maximo.controllers;

import org.launchcode.maximo.models.Building;
import org.launchcode.maximo.models.data.BuildingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("buildings")
public class BuildingController extends AbstractController {

    //display index table of buildings
    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "Buildings");
        model.addAttribute("buildings", buildingDao.findAll());
        return "buildings/index";
    }

    //allows admins to add new buildings
    @RequestMapping(value="add")
    public String displayAddBuildingForm(Model model){
        model.addAttribute("title", "New Building");
        model.addAttribute(new Building());
        return "buildings/add";
    }


    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddBuildingForm(Model model, @ModelAttribute @Valid Building building, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Building");
            return "buildings/add";
        }

        buildingDao.save(building);
        return "redirect:";
    }

}
