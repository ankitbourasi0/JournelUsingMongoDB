package com.bourasi.learn.journelusingmongodb.Controller;


import com.bourasi.learn.journelusingmongodb.Entity.Journel;
import com.bourasi.learn.journelusingmongodb.Service.JournelService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Journel createJournelEntry(@RequestBody Journel myjournel){
        myjournel.setDate(LocalDateTime.now());
        journelService.createJournelEntries(myjournel);
        return  myjournel;
    }

    @GetMapping()
    public List<Journel> getAllJournel(){
        return journelService.getAllJournel();
    }

    @GetMapping("/{id}")
    public Optional<Journel> getById(@PathVariable  ObjectId id){
        return journelService.getJournelById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deletJournel(@PathVariable  ObjectId id) {
        journelService.deleteJournel(id);
        return true;
    }

    @PutMapping("/update/{id}")
    public Journel updateJournel(@RequestBody Journel newJournel, @PathVariable ObjectId id){
        Journel oldEntry =  journelService.getJournelById(id).orElse(null);
        if (oldEntry != null){
            oldEntry.setTitle(newJournel.getTitle() != null && !newJournel.getTitle().equals("") ? newJournel.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newJournel.getContent()!=null && !newJournel.getContent().equals("") ? newJournel.getContent() : oldEntry.getContent());
        }
        journelService.createJournelEntries(oldEntry);
        return oldEntry;
    }
}
