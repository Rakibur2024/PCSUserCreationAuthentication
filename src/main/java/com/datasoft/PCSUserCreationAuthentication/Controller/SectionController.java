package com.datasoft.PCSUserCreationAuthentication.Controller;

import com.datasoft.PCSUserCreationAuthentication.Model.OrganizationProfile;
import com.datasoft.PCSUserCreationAuthentication.Model.Section;
import com.datasoft.PCSUserCreationAuthentication.Service.OrganizationProfileService;
import com.datasoft.PCSUserCreationAuthentication.Service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;
    @RequestMapping(value ="/listByRole/{role_id}", method = RequestMethod.GET)
    public @ResponseBody List<Section> sectionListByRole(@PathVariable Integer role_id){
        return sectionService.sectionListByRole(role_id);
    }


}
