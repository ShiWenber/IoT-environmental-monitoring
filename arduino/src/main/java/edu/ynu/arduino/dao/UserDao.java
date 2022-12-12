package edu.ynu.arduino.dao;
    
    


import edu.ynu.arduino.dao.AbstractDao;
import java.lang.String;
import edu.ynu.arduino.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 持久层
 *
 * @author shiwenbo
 * @since 2022-11-29 11:55:25
 */
@Repository
public interface UserDao extends AbstractDao<User, String> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}


