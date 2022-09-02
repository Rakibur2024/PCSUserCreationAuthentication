package com.datasoft.PCSUserCreationAuthentication.Repository;

import com.datasoft.PCSUserCreationAuthentication.Model.URL;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface URLRepository extends CrudRepository<URL, Long> {

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_module WHERE id=:user_module_id", nativeQuery = true)
    Integer isUserModuleExists(@Param("user_module_id") Integer user_module_id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_enlist_url WHERE user_module_id=:user_module_id AND label_name=:label_name", nativeQuery = true)
    Integer isExists(@Param("user_module_id") Integer user_module_id,@Param("label_name") String label_name);

    @Query(value = "SELECT id FROM cchaportdb.user_enlist_url WHERE user_module_id=:user_module_id AND label_name=:label_name", nativeQuery = true)
    Integer getUrlId(@Param("user_module_id") Integer user_module_id,@Param("label_name") String label_name);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_assign_role WHERE user_role_id=:role_id AND user_url_id=:url_id", nativeQuery = true)
    Integer cntURLAssignment(@Param("role_id") Integer role_id,
                             @Param("url_id") Integer url_id);

    @Query(value = "SELECT user_role_id FROM cchaportdb.user_assign_role WHERE user_url_id=:url_id", nativeQuery = true)
    public List[] gerRoleByURL(@Param("url_id") Integer url_id);

    @Modifying()
    @Query(value = "INSERT INTO cchaportdb.user_enlist_url(user_module_id,label_name,router_link,no_of_param,param_1,param_2,param_3,param_4,url_for,editable,created_at,created_ip) " +
            "VALUES(:user_module_id,:label_name,:router_link,:no_of_param,:param_1,:param_2,:param_3,:param_4,:url_for,:editable,NOW(),:created_ip)", nativeQuery = true)
    @Transactional
    Integer insertUserEnlistURL(@Param("user_module_id") Integer user_module_id,
                             @Param("label_name") String label_name,
                             @Param("router_link") String router_link,
                             @Param("no_of_param") Integer no_of_param,
                             @Param("param_1") String param_1,
                             @Param("param_2") String param_2,
                             @Param("param_3") String param_3,
                             @Param("param_4") String param_4,
                             @Param("url_for") String url_for,
                             @Param("editable") Integer editable,
                             @Param("created_ip") String created_ip);

    @Modifying()
    @Query(value = "INSERT INTO cchaportdb.user_assign_role(user_role_id,user_url_id,created_at,created_ip) " +
            "VALUES(:role_id,:url_id,NOW(),:created_ip)", nativeQuery = true)
    @Transactional
    Integer insertURLAssignment(@Param("role_id") Integer role_id,
                                @Param("url_id") Integer url_id,
                                @Param("created_ip") String created_ip);

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM cchaportdb.user_enlist_url\n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id = cchaportdb.user_module.id", nativeQuery = true)
    public List<URL> UserEnlistUrlList();

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM cchaportdb.user_enlist_url\n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id = cchaportdb.user_module.id", nativeQuery = true)
    public List[] urlList();

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM cchaportdb.user_enlist_url\n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id = cchaportdb.user_module.id\n" +
            "WHERE cchaportdb.user_module.id=:module_id", nativeQuery = true)
    public List[] urlListByModule(@Param("module_id") Integer module_id);

    @Query(value = "SELECT cchaportdb.user_enlist_url.id,cchaportdb.user_enlist_url.user_module_id,cchaportdb.user_enlist_url.label_name,\n" +
            "cchaportdb.user_enlist_url.router_link,IFNULL(cchaportdb.user_enlist_url.no_of_param,0) AS no_of_param,\n" +
            "IFNULL(cchaportdb.user_enlist_url.param_1,' ') AS param_1,IFNULL(cchaportdb.user_enlist_url.param_2,' ') AS param_2,\n" +
            "IFNULL(cchaportdb.user_enlist_url.param_3,' ') AS param_3,IFNULL(cchaportdb.user_enlist_url.param_4,' ') AS param_4,\n" +
            "IFNULL(cchaportdb.user_enlist_url.url_for,' ') AS url_for,IFNULL(cchaportdb.user_enlist_url.editable,' ') AS editable,\n" +
            "IFNULL(cchaportdb.user_enlist_url.created_at,' ') AS created_at,IFNULL(cchaportdb.user_enlist_url.created_by,' ') AS created_by,\n" +
            "IFNULL(cchaportdb.user_enlist_url.created_ip,' ') AS created_ip,IFNULL(cchaportdb.user_enlist_url.updated_at,' ') AS updated_at,\n" +
            "IFNULL(cchaportdb.user_enlist_url.updated_by,' ') AS updated_by,IFNULL(cchaportdb.user_enlist_url.update_ip,' ') AS update_ip,\n" +
            "cchaportdb.user_module.module_name\n" +
            "FROM cchaportdb.user_enlist_url\n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id = cchaportdb.user_module.id\n" +
            "WHERE cchaportdb.user_enlist_url.id=:id", nativeQuery = true)
    public List[] urlById(@Param("id") Long id);

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM cchaportdb.user_enlist_url\n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id = cchaportdb.user_module.id\n" +
            "WHERE cchaportdb.user_enlist_url.id=:id", nativeQuery = true)
    public URL getUserEnlistURLById(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_enlist_url " +
            "WHERE (user_module_id=:user_module_id AND label_name=:label_name) AND (id!=:id)", nativeQuery = true)
    Integer isUnique(@Param("id") Long id,@Param("user_module_id") Integer user_module_id,@Param("label_name") String label_name);

    @Modifying()
    @Query(value = "UPDATE cchaportdb.user_enlist_url SET user_module_id=:user_module_id,label_name=:label_name,router_link=:router_link," +
            "no_of_param=:no_of_param,param_1=:param_1,param_2=:param_2,param_3=:param_3,param_4=:param_4," +
            "url_for=:url_for,editable=:editable,updated_at=NOW(),update_ip=:update_ip WHERE id=:id", nativeQuery = true)
    @Transactional
    Integer editUserEnlistURL(@Param("id") Long id,
                           @Param("user_module_id") Integer user_module_id,
                           @Param("label_name") String label_name,
                           @Param("router_link") String router_link,
                           @Param("no_of_param") Integer no_of_param,
                           @Param("param_1") String param_1,
                           @Param("param_2") String param_2,
                           @Param("param_3") String param_3,
                           @Param("param_4") String param_4,
                           @Param("url_for") String url_for,
                           @Param("editable") Integer editable,
                           @Param("update_ip") String update_ip);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_assign_role WHERE user_url_id=:id", nativeQuery = true)
    Integer chkUserAssignRole(@Param("id") Long id);

    @Modifying()
    @Query(value = "DELETE FROM cchaportdb.user_enlist_url WHERE id=:id", nativeQuery = true)
    @Transactional
    Integer deleteUserEnlistUrlById(@Param("id") Long id);

    @Modifying()
    @Query(value = "DELETE FROM cchaportdb.user_assign_role WHERE user_url_id=:url_id", nativeQuery = true)
    @Transactional
    Integer deleteUrlAssignmentByURLId(@Param("url_id") Long url_id);

    @Modifying()
    @Query(value = "DELETE FROM cchaportdb.user_assign_role WHERE user_url_id=:url_id", nativeQuery = true)
    @Transactional
    Integer deleteUserEnlistUrlByUrlId(@Param("url_id") Integer url_id);

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM user_enlist_url \n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id=cchaportdb.user_module.id\n" +
            "WHERE user_module_id=:module_id", nativeQuery = true)
    public List<URL> urlByModule(@Param("module_id") Integer module_id);

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM cchaportdb.user_assign_role\n" +
            "INNER JOIN cchaportdb.user_enlist_url ON cchaportdb.user_assign_role.user_url_id=cchaportdb.user_enlist_url.id\n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id=cchaportdb.user_module.id\n" +
            "WHERE cchaportdb.user_assign_role.user_role_id=:user_role_id", nativeQuery = true)
    public List<URL> urlByRole(@Param("user_role_id") Integer user_role_id);

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\\n\" +\n" +
            "            \"FROM cchaportdb.user_assign_role\\n\" +\n" +
            "            \"INNER JOIN cchaportdb.user_enlist_url ON cchaportdb.user_assign_role.user_url_id=cchaportdb.user_enlist_url.id\\n\" +\n" +
            "            \"INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id=cchaportdb.user_module.id\\n\" +\n" +
            "            \"WHERE cchaportdb.user_assign_role.user_role_id=:user_role_id", nativeQuery = true)
    public List[] urlListByROle(@Param("user_role_id") Integer user_role_id);
}
