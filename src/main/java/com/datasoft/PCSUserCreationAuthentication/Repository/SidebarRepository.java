package com.datasoft.PCSUserCreationAuthentication.Repository;

import com.datasoft.PCSUserCreationAuthentication.Model.Sidebar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SidebarRepository extends CrudRepository<Sidebar, Long> {
    @Query(value = "SELECT role_id FROM cchaportdb.users_info WHERE login_id=:login_id", nativeQuery = true)
    Integer getRoleId(@Param("login_id") String login_id);

    @Query(value = "SELECT DISTINCT cchaportdb.user_enlist_url.user_module_id\n" +
            "FROM cchaportdb.user_assign_role\n" +
            "INNER JOIN cchaportdb.user_enlist_url ON cchaportdb.user_assign_role.user_url_id=cchaportdb.user_enlist_url.id\n" +
            "WHERE cchaportdb.user_assign_role.user_role_id=:role_id", nativeQuery = true)
    public List[] moduleListByRole(@Param("role_id") Integer role_id);

    @Query(value = "SELECT module_name FROM user_module WHERE id=:module_id", nativeQuery = true)
    String getModuleName(@Param("module_id") Integer module_id);

    @Query(value = "SELECT user_assign_role.user_role_id,user_enlist_url.user_module_id,user_assign_role.user_url_id,user_enlist_url.label_name\n" +
            "FROM user_assign_role\n" +
            "INNER JOIN user_enlist_url ON user_assign_role.user_url_id=user_enlist_url.id\n" +
            "WHERE user_assign_role.user_role_id=:user_role_id AND user_enlist_url.user_module_id=:user_module_id", nativeQuery = true)
    public List[] getLabelInfo(@Param("user_role_id") Integer user_role_id, @Param("user_module_id") Integer user_module_id);


}
