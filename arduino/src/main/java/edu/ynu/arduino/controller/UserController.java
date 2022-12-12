package edu.ynu.arduino.controller;


import edu.ynu.arduino.entity.User;
import edu.ynu.arduino.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 控制层
 *
 * @author shiwenbo
 * @since 2022-11-29 11:55:25
 */
@Slf4j
@RestController
@Tag(name = "UserController")
@RequestMapping("/user")
public class UserController extends AbstractTypedController<User, String> {

    @Resource
    private UserService userService;

    UserController(UserService service) {
        this.svcContext = service;
        this.userService = service;
    }

    @Operation(summary = "login")
    @PostMapping("/login")
    public User login(@RequestParam("username") String username,
                      @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    @Operation(summary = "register")
    @PostMapping("/register")
    public User register(@RequestParam("username") String username,
                         @RequestParam("password") String password) {
        return userService.register(username, password);
    }
}