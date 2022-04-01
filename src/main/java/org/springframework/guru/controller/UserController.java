package org.springframework.guru.controller;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.guru.request.AddUserRequest;
import org.springframework.guru.response.AddUserResponse;
import org.springframework.guru.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/addUsers")
    public ResponseEntity<Object> addUser(@Valid @RequestBody AddUserRequest addUserRequest,
            HttpServletRequest httpServletRequest) throws Exception {
        AddUserResponse addUserResponse = new AddUserResponse("true", "success");
        addUserResponse = userService.addUsers(addUserRequest);
        return ResponseEntity.ok("done");
    }
}