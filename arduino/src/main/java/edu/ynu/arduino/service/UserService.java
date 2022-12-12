package edu.ynu.arduino.service;
    
    


import edu.ynu.arduino.service.AbstractTypedService;
import java.lang.String;
import edu.ynu.arduino.entity.User;
import edu.ynu.arduino.service.UserService;
import edu.ynu.arduino.dao.UserDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
/**
 * 业务层
 *
 * @author shiwenbo
 * @since 2022-11-29 11:55:25
 */
@Slf4j
@Service
public class UserService extends AbstractTypedService<User, String> {

	@Resource
    private UserDao userDao;

    public UserService(UserDao dao) {
        this.dataContext = dao;
        this.userDao = dao;
    }

    public Page<User> queryUserByPage(Pageable page) {
        return userDao.queryPage(page, null);
    }

    public User login(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user == null) {
            log.info("密码错误");
            return null;
        }

        return user;
    }
    public User register(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null) {
            log.info("用户已存在");
            return null;
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setSex(1);
        newUser.setEnabled(true);
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        return userDao.save(newUser);
    }

}

