package edu.ynu.arduino.controller;
    
    



import edu.ynu.arduino.controller.AbstractTypedController;
import java.lang.String;
import edu.ynu.arduino.entity.Role;
import edu.ynu.arduino.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
/**
 * 控制层
 *
 * @author shiwenbo
 * @since 2022-11-29 11:55:24
 */
@Slf4j
@RestController
@Tag(name = "RoleController")
@RequestMapping("/role")
public class RoleController extends AbstractTypedController<Role, String>{
    
    @Resource
	private RoleService roleService;
    
    RoleController(RoleService service) {
        this.svcContext = service;
        this.roleService = service;
    }
}

