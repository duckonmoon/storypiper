package com.duckonmoon.storypiper.storypiper.controller;

import com.duckonmoon.storypiper.storypiper.security.CurrentUser;
import com.duckonmoon.storypiper.storypiper.security.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api/user")
public class TestController {
    @GetMapping("/hey")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public HashMap<String,String> wow(@CurrentUser UserPrincipal currentUser){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("here","wow");
        return map;
    }
}
