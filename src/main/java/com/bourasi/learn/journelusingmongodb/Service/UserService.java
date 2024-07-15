package com.bourasi.learn.journelusingmongodb.Service;

import com.bourasi.learn.journelusingmongodb.Entity.Journel;
import com.bourasi.learn.journelusingmongodb.Entity.User;
import com.bourasi.learn.journelusingmongodb.Repository.JournelRepository;
import com.bourasi.learn.journelusingmongodb.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User journel){

        try {

            userRepository.save(journel);

        }catch (Exception e){
            System.out.println("Exception:" + e);
        }

    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public Optional<User> getUserById(ObjectId id) {
            return userRepository.findById(id);
    }

    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    public Journel updateJournel(Journel journel){
//        return journelRepository.save(journel);
//    }
}
