package edu.ynu.arduino.service;
    
    


import edu.ynu.arduino.service.AbstractTypedService;
import java.lang.String;
import edu.ynu.arduino.entity.User_Role;
import edu.ynu.arduino.service.User_RoleService;
import edu.ynu.arduino.dao.User_RoleDao;
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
public class User_RoleService extends AbstractTypedService<User_Role, String> {

	@Resource
    private User_RoleDao user_RoleDao;

    public User_RoleService(User_RoleDao dao) {
        this.dataContext = dao;
        this.user_RoleDao = dao;
    }

    public Page<User_Role> queryUser_RoleByPage(Pageable page) {
        return user_RoleDao.queryPage(page, null);
    }

}

