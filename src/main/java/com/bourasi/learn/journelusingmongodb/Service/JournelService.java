package com.bourasi.learn.journelusingmongodb.Service;

import com.bourasi.learn.journelusingmongodb.Entity.Journel;
import com.bourasi.learn.journelusingmongodb.Entity.User;
import com.bourasi.learn.journelusingmongodb.Repository.JournelRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournelService {

    @Autowired
    private JournelRepository journelRepository;
    @Autowired
    private UserService userService;

    @Transactional
    /*
     this will tell spring to treat all code in this method treat them as single operation,
     if any one operation is failed the whole operation will fail,
     and successfull operation will rollback,
     also for enable this transaction use annotation at main class @EnableTransactionManagement
    */
    public void createJournelEntries(Journel journel, String username){
        User user = userService.findByUsername(username);
        try {
            journel.setDate(LocalDateTime.now());
            Journel saved = journelRepository.save(journel);
            user.getJournelEntries().add(saved);
            userService.saveUser(user);
        }catch (Exception e){
            System.out.println("Exception:" + e);
            throw  new RuntimeException("An error occured while saving journel",e);
        }

    }
    /*we are using method overloading because above method have already username,
    toh hame us method se update karne ki jarurat nhi hai kyuki database mein pehle se hi user k pass journel ka refrence hai or real journel to journelentries db mein hai.
    so hume bas real journel ko update karna hai hai uski id reference mein user k pass hai pehle se hi
    */
    public void createJournelEntries(Journel journel){
        journelRepository.save(journel);
    }

    public List<Journel> getAllJournel(){
        return journelRepository.findAll();
    }


    public Optional<Journel> getJournelById(ObjectId id) {
            return journelRepository.findById(id);
    }

    public void deleteJournel(ObjectId id, String username){
        User user =  userService.findByUsername(username);
        /*remove the journels if user's journel id and journel's journel id is equal so it remove from both placed called cascade remove */

    user.getJournelEntries().removeIf(x-> x.getId().equals(id));
    userService.saveUser(user);
        journelRepository.deleteById(id);
    }

//    public Journel updateJournel(Journel journel){
//        return journelRepository.save(journel);
//    }
}
