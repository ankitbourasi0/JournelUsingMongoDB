package com.bourasi.learn.journelusingmongodb.Controller;


import com.bourasi.learn.journelusingmongodb.Entity.User;
import com.bourasi.learn.journelusingmongodb.Repository.UserRepository;
import com.bourasi.learn.journelusingmongodb.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }
    @PostMapping()
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User userData){
        User oldUser =  userRepository.findByUsername(username);
        if (oldUser != null){
            oldUser.setUsername( userData.getUsername());
            oldUser.setPassword(userData.getPassword());
            userService.saveUser(oldUser);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
