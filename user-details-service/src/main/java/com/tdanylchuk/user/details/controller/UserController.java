package com.tdanylchuk.user.details.controller;

import com.tdanylchuk.user.details.model.User;
import com.tdanylchuk.user.details.model.UserDetails;
import com.tdanylchuk.user.details.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/{userId}/details")
    public void saveUserDetails(@PathVariable("userId") long userId, @RequestBody UserDetails userDetails) {
        userService.saveDetails(userId, userDetails);
    }

    @GetMapping("/{userId}/details")
    public UserDetails getUserDetails(@PathVariable("userId") long userId) {
        return userService.getDetails(userId);
    }
}
