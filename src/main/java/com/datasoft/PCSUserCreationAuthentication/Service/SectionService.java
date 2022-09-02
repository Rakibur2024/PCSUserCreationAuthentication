package com.datasoft.PCSUserCreationAuthentication.Service;

import com.datasoft.PCSUserCreationAuthentication.Model.Section;
import com.datasoft.PCSUserCreationAuthentication.Repository.OrganizationProfileRepository;
import com.datasoft.PCSUserCreationAuthentication.Repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private  OrganizationProfileRepository organizationProfileRepository;

    public List<Section> sectionListByRole(Integer role_id){
        String userRoleType = organizationProfileRepository.getRoleTypeById(role_id);
        List<Section> sectionList = new ArrayList<Section>();
        String exception = null;
        try
        {
            sectionList = sectionRepository.getSectionListByRole(userRoleType);
        }catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.getMessage();
        }
        return sectionList;
    }
}
