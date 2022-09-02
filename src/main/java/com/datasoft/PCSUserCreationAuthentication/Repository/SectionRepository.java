package com.datasoft.PCSUserCreationAuthentication.Repository;

import com.datasoft.PCSUserCreationAuthentication.Model.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends CrudRepository<Section,Long> {
    @Query(value = "SELECT * FROM cchaportdb.users_section_list WHERE user_role_type=:userRoleType", nativeQuery = true)
    List<Section> getSectionListByRole(@Param("userRoleType") String userRoleType);
}
