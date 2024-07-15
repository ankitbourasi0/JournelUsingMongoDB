package com.bourasi.learn.journelusingmongodb.Controller;


import com.bourasi.learn.journelusingmongodb.Entity.Journel;
import com.bourasi.learn.journelusingmongodb.Entity.User;
import com.bourasi.learn.journelusingmongodb.Service.JournelService;
import com.bourasi.learn.journelusingmongodb.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journel")
public class JournelController {

    @Autowired
    private JournelService journelService;
    @Autowired
    private UserService userService;
    @PostMapping("/{username}")
    public ResponseEntity<Journel> createJournelEntry(@RequestBody Journel myjournel,@PathVariable String username){
       try {
           /*
           * Now we are getting the username and journel, We have 2 collections User and Journel
           * User have: _id, username, password, journel[].
           * User's journel[] holds Journel collection's journel .
           * so we have to save journel first and then save it to the user.
           * */
            journelService.createJournelEntries(myjournel,username);
           return new ResponseEntity<>(myjournel, HttpStatus.CREATED);

       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

       }

        }

    @GetMapping("/{username}" )
    public ResponseEntity<?> getAllJournelEntryOfUser(@PathVariable String username){
           User user =  userService.findByUsername(username);
        List<Journel> list =  user.getJournelEntries();
        if(list != null &&  !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Journel> getById(@PathVariable  ObjectId id){
        Optional<Journel> journel = journelService.getJournelById(id);
        if (journel.isPresent()) {
            return new ResponseEntity<>(journel.get(), HttpStatus.OK);}
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<?> deletJournel(@PathVariable  ObjectId id ,@PathVariable String username) {
        journelService.deleteJournel(id,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{username}/{id}")
    public ResponseEntity<?> updateJournel(
            @RequestBody Journel newJournel,
            @PathVariable ObjectId id,
            @PathVariable String username
    ){
        Journel oldEntry =  journelService.getJournelById(id).orElse(null);
        if (oldEntry != null){
            oldEntry.setTitle(newJournel.getTitle() != null && !newJournel.getTitle().equals("") ? newJournel.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newJournel.getContent()!=null && !newJournel.getContent().equals("") ? newJournel.getContent() : oldEntry.getContent());
            journelService.createJournelEntries(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
