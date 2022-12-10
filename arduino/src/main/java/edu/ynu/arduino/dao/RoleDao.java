package edu.ynu.arduino.dao;
    
    


import edu.ynu.arduino.dao.AbstractDao;
import java.lang.String;
import edu.ynu.arduino.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * 持久层
 *
 * @author shiwenbo
 * @since 2022-11-29 11:55:23
 */
@Repository
public interface RoleDao extends AbstractDao<Role, String> {

}

