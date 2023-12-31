package com.sigaCompany.dvdStore.services;

import com.sigaCompany.dvdStore.controllers.DvdDTO;
import com.sigaCompany.dvdStore.entities.DvdEntity;
import com.sigaCompany.dvdStore.repositories.DvdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DvdService {
    @Autowired
    DvdRepository dvdRepository;

    // Adds a new Dvd to the store
    public boolean add(DvdServiceModel dvdServiceModel) {
        DvdEntity dvdEntity = new DvdEntity(dvdServiceModel.getName(), dvdServiceModel.getGender(), dvdServiceModel.getQuantity(), dvdServiceModel.getPrice());
        DvdEntity tempObject = dvdRepository.save(dvdEntity);
        return tempObject != null;
    }

    //Updates an existing Dvd in the store
    public boolean updateDvd(long id, DvdDTO dvdDTO){
        Optional<DvdEntity> optionalDvdEntity = dvdRepository.findById(id);
        if (optionalDvdEntity.isPresent()){
            DvdEntity dvdEntity = optionalDvdEntity.get();
            dvdEntity.setName(dvdDTO.name());
            dvdEntity.setGender(dvdDTO.gender());
            dvdEntity.setQuantity(dvdDTO.quantity());
            dvdEntity.setPrice(dvdDTO.price());
            dvdRepository.save(dvdEntity);
            return true;
        } else {
            return false;
        }
    }

    //Retrieves a list of all dvds in the store
    public List<DvdServiceModel> findAll() {
        List<DvdEntity> dvdEntities = dvdRepository.findAll();
        List<DvdServiceModel> dvdServiceModels = new ArrayList<>();
        for (DvdEntity dvd : dvdEntities){
            dvdServiceModels.add(new DvdServiceModel(dvd.getName(), dvd.getGender(), dvd.getQuantity(), dvd.getPrice()));
        }
        return dvdServiceModels;
    }

    //Retrieves a Dvd by its ID
    public DvdServiceModel findById(long id) {
        Optional<DvdEntity> dvdEntityOptional = dvdRepository.findById(id);
        DvdEntity dvdEntity = dvdEntityOptional.get();
        return new DvdServiceModel(dvdEntity.getName(), dvdEntity.getGender(), dvdEntity.getQuantity(), dvdEntity.getPrice());
    }

    //Deletes a Dvd by its ID
    public void deleteById(long id) {
        dvdRepository.deleteById(id);
    }
}
