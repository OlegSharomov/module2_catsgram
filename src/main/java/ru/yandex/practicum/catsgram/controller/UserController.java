package ru.yandex.practicum.catsgram.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
//<<<<<<< HEAD
//
//    private final UserService userService;
//
//    @Autowired
//=======
    private final UserService userService;

//>>>>>>> origin/error-handler_add-handling
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
//<<<<<<< HEAD
//    public User create(@RequestBody User user) {
//        return userService.create(user);
//    }
//
//    @PutMapping
//    public User put(@RequestBody User user) {
//        return userService.put(user);
//=======
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
//>>>>>>> origin/error-handler_add-handling
    }

    @GetMapping("/user/{userMail}")
    public User getUser(@PathVariable("userMail") String userMail){
        return userService.findUserByEmail(userMail);
    }
}
