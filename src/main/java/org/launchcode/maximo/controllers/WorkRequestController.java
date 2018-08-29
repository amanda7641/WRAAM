package org.launchcode.maximo.controllers;

import org.launchcode.maximo.models.Building;
import org.launchcode.maximo.models.StatusType;
import org.launchcode.maximo.models.WorkRequest;
import org.launchcode.maximo.models.WorkRequestFieldType;
import org.launchcode.maximo.models.data.BuildingDao;
import org.launchcode.maximo.models.data.WorkRequestDao;
import org.launchcode.maximo.models.forms.EditForm;
import org.launchcode.maximo.models.forms.SearchForm;
import org.launchcode.maximo.models.forms.WorkRequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("workrequest")
public class WorkRequestController extends AbstractController {

    @Autowired
    private WorkRequestDao workRequestDao;

    @Autowired
    private BuildingDao buildingDao;

    //Display Work Request Index automatically
    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("title", "Work Requests");
        model.addAttribute(new SearchForm());
        model.addAttribute("workRequests", workRequestDao.findAll());
        return "workRequest/index";
    }

    //Search through Work Request Index
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String indexSearch(Model model, @ModelAttribute SearchForm searchForm){

        ArrayList<WorkRequest> allWorkRequests = workRequestDao.findAll();

        ArrayList<WorkRequest> filteredWorkRequests = new ArrayList<>();

        if(searchForm.getSearchField().equals(WorkRequestFieldType.DESCRIPTION)){
            for(WorkRequest workRequest : allWorkRequests) {
                if (workRequest.getDescription().toLowerCase().contains(searchForm.getKeyword().toLowerCase())) {
                    filteredWorkRequests.add(workRequest);
                }
            }
        }
        else {
            for(WorkRequest workRequest : allWorkRequests){
                if (workRequest.getBuilding().getName().toLowerCase().contains(searchForm.getKeyword().toLowerCase())){
                    filteredWorkRequests.add(workRequest);
                }
            }
        }

        model.addAttribute("title", "Work Requests");
        model.addAttribute(new SearchForm());
        model.addAttribute("workRequests", filteredWorkRequests);
        return "workRequest/index";
    }

    //Display individual work request with form for changing status
    @RequestMapping(value="view/{id}", method = RequestMethod.GET)
    public String viewWorkRequest(Model model, @PathVariable("id") int workRequestId){
        WorkRequest workRequest = workRequestDao.findOne(workRequestId);
        model.addAttribute("workRequest", workRequest);
        model.addAttribute(new EditForm(workRequest));
        model.addAttribute("title", "Work Request Detail");

        return "workRequest/view";
    }

    //Process change of status from individual view and send to index
    @RequestMapping(value="view", method = RequestMethod.POST)
    public String editWorkRequest(@ModelAttribute EditForm editForm, Errors errors, Model model){
        WorkRequest workRequest = editForm.getWorkRequest();

        if (errors.hasErrors()){
            model.addAttribute("workRequest", workRequest);
            model.addAttribute("title", "Work Request Detail");

            return "workRequest/view/" + workRequest.getId();
        }

        workRequest.setStatus(editForm.getStatusField());
        workRequestDao.save(workRequest);
        return "redirect:";
    }

    //Display new work request form
    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddForm(Model model){

        model.addAttribute("title", "New Work Request");
        model.addAttribute(new WorkRequestForm());
        model.addAttribute("buildings", buildingDao.findAll());

        return "workRequest/add";
    }

    //Process the new work request form and redirect to index if it has no errors
    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddForm(Model model, @ModelAttribute @Valid WorkRequestForm workRequestForm, Errors errors){

        if (errors.hasErrors()){
            model.addAttribute("title", "New Work Request");
            model.addAttribute("selectBuildingError", "Please select a building.");
            model.addAttribute("buildings", buildingDao.findAll());
            return "workRequest/add";
        }


        Building building = buildingDao.findOne(workRequestForm.getBuildingId());

        if(building == null){
            model.addAttribute("title", "New Work Request");
            model.addAttribute("buildings", buildingDao.findAll());
            return "workRequest/add";
        }

        WorkRequest workRequest = new WorkRequest(workRequestForm.getDescription(), workRequestForm.getReportedBy(), workRequestForm.getContactNumber());
        workRequest.setBuilding(building);
        workRequestDao.save(workRequest);
        return "redirect:";
    }

}
