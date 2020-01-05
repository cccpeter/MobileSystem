package com.mobile.mobile.controller;

import com.mobile.mobile.entity.ResponseBean;
import com.mobile.mobile.entity.User;
import com.mobile.mobile.service.UserService;
import com.mobile.mobile.util.JWTUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WebController {

    private static final Logger LOGGER = LogManager.getLogger(WebController.class);

    private UserService userService;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseBean login(@RequestParam("username") String username,
                                                  @RequestParam("password") String password) {
        System.out.println(username);
        User userBean = userService.getUserinfo(username);
        if (userBean.getUser_password().equals(password)) {
            Map<String, String> data = new HashMap<>();
            data.put("token", JWTUtil.sign(username, password));
            data.put("usertype", userBean.getUser_role());
            return new ResponseBean(200, "Login success", data);
        } else {
            throw new UnauthorizedException();
        }
    }
    @CrossOrigin
    @GetMapping("/test")
    public String test(@RequestParam("username") String username,
                       @RequestParam("password") String password){
        System.out.println("123");
        return "123";
    }
    @GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        } else {
            return new ResponseBean(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }
    @GetMapping("/require_teacher")
    @RequiresRoles("teacher")
    public ResponseBean requireTeacher() {
        return new ResponseBean(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresRoles("teacher")
    public ResponseBean requirePermission() {
        return new ResponseBean(200, "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }
}
