package com.edu.batteryservice.controller;

import com.edu.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batteryservice/user")
@CrossOrigin(origins = "http://localhost:9525", allowCredentials = "true")
public class EduLoginController {
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
