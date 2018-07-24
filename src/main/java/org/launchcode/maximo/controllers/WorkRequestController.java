package org.launchcode.maximo.controllers;

import org.launchcode.maximo.models.Building;
import org.launchcode.maximo.models.WorkRequest;
import org.launchcode.maximo.models.WorkRequestFieldType;
import org.launchcode.maximo.models.data.BuildingDao;
import org.launchcode.maximo.models.data.WorkRequestDao;
import org.launchcode.maximo.models.forms.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("workrequest")
public class WorkRequestController {

    @Autowired
    private WorkRequestDao workRequestDao;

    @Autowired
    private BuildingDao buildingDao;

    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("title", "Work Requests");
        model.addAttribute(new SearchForm());
        // TODO - might be better looking to have search fields built into table for each column
        model.addAttribute("workRequests", workRequestDao.findAll());
        return "workRequest/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String indexSearch(Model model, @ModelAttribute SearchForm searchForm){

        ArrayList<WorkRequest> allWorkRequests = workRequestDao.findAll();

        ArrayList<WorkRequest> filteredWorkRequests = new ArrayList<>();

        if(searchForm.getSearchField().equals(WorkRequestFieldType.ALL)){
            for (WorkRequest workRequest : allWorkRequests){
                if(workRequest.getDescription().toLowerCase().contains(searchForm.getKeyword().toLowerCase())){
                    filteredWorkRequests.add(workRequest);
                }
                else if (workRequest.getDateRequested().toString().contains(searchForm.getKeyword().toLowerCase())){
                    filteredWorkRequests.add(workRequest);
                }
                else if(workRequest.getBuilding().getName().toLowerCase().contains(searchForm.getKeyword())){
                    filteredWorkRequests.add(workRequest);
                }
            }
            model.addAttribute("title", "Work Request");
            model.addAttribute(new SearchForm());
            model.addAttribute("workRequests", filteredWorkRequests);
            return "workRequest/index";
        }
        else {
            for(WorkRequest workRequest : allWorkRequests){
                switch(searchForm.getSearchField()) {
                    case DATEREQUESTED:
                        if (workRequest.getDateRequested().toString().contains(searchForm.getKeyword().toLowerCase())){
                            filteredWorkRequests.add(workRequest);
                        }
                        break;
                    case BUILDING:
                        if (workRequest.getBuilding().getName().toLowerCase().contains(searchForm.getKeyword())){
                            filteredWorkRequests.add(workRequest);
                        }
                        break;
                    case DESCRIPTION:
                        if(workRequest.getDescription().toLowerCase().contains(searchForm.getKeyword().toLowerCase())) {
                            filteredWorkRequests.add(workRequest);
                        }
                        break;

                }

            }
        }
        model.addAttribute("title", "Work Request");
        model.addAttribute(new SearchForm());
        model.addAttribute("workRequests", filteredWorkRequests);
        return "workRequest/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddForm(Model model){

        model.addAttribute("title", "New Work Request");
        model.addAttribute(new WorkRequest());
        model.addAttribute("buildings", buildingDao.findAll());

        return "workRequest/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddForm(Model model, @ModelAttribute @Valid WorkRequest workRequest, Errors errors, @RequestParam int buildingId){
        if (errors.hasErrors()){
            model.addAttribute("title", "New Work Request");
            model.addAttribute("buildings", buildingDao.findAll());
            return "workRequest/add";
        }

        Building build = buildingDao.findOne(buildingId);
        workRequest.setBuilding(build);
        workRequestDao.save(workRequest);
        return "redirect:";
    }

}
