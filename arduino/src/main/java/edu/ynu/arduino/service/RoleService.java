package edu.ynu.arduino.service;
    
    


import edu.ynu.arduino.service.AbstractTypedService;
import java.lang.String;
import edu.ynu.arduino.entity.Role;
import edu.ynu.arduino.service.RoleService;
import edu.ynu.arduino.dao.RoleDao;
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
 * @since 2022-11-29 11:55:24
 */
@Slf4j
@Service
public class RoleService extends AbstractTypedService<Role, String> {

	@Resource
    private RoleDao roleDao;

    public RoleService(RoleDao dao) {
        this.dataContext = dao;
        this.roleDao = dao;
    }

    public Page<Role> queryRoleByPage(Pageable page) {
        return roleDao.queryPage(page, null);
    }

}

