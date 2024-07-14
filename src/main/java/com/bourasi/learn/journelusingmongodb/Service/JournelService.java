package com.bourasi.learn.journelusingmongodb.Service;

import com.bourasi.learn.journelusingmongodb.Entity.Journel;
import com.bourasi.learn.journelusingmongodb.Repository.JournelRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournelService {

    @Autowired
    private JournelRepository journelRepository;

    public void createJournelEntries(Journel journel){
            journelRepository.save(journel);
    }

    public List<Journel> getAllJournel(){
        return journelRepository.findAll();
    }


    public Optional<Journel> getJournelById(ObjectId id) {
            return journelRepository.findById(id);
    }

    public void deleteJournel(ObjectId id){
          journelRepository.deleteById(id);
    }

//    public Journel updateJournel(Journel journel){
//        return journelRepository.save(journel);
//    }
}
