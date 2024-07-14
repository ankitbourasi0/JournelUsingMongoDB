package com.bourasi.learn.journelusingmongodb.Controller;


import com.bourasi.learn.journelusingmongodb.Entity.Journel;
import com.bourasi.learn.journelusingmongodb.Service.JournelService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class JournelController {

    @Autowired
    private JournelService journelService;
    @PostMapping
    public ResponseEntity<Journel> createJournelEntry(@RequestBody Journel myjournel){
       try {
           myjournel.setDate(LocalDateTime.now());
           journelService.createJournelEntries(myjournel);
           return new ResponseEntity<>(myjournel, HttpStatus.CREATED);

       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

       }

        }

    @GetMapping()
    public ResponseEntity<?> getAllJournel(){
        List<Journel> list =  journelService.getAllJournel();
        if(list != null &&  !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Journel> getById(@PathVariable  ObjectId id){
        Optional<Journel> journel = journelService.getJournelById(id);
        if (journel.isPresent()) {
            return new ResponseEntity<>(journel.get(), HttpStatus.OK);}
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletJournel(@PathVariable  ObjectId id) {
        journelService.deleteJournel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateJournel(@RequestBody Journel newJournel, @PathVariable ObjectId id){
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
