package com.leverx.odata.repository;

import com.leverx.odata.entity.jpa.Cat;
import com.leverx.odata.entity.jpa.Dog;
import com.leverx.odata.entity.jpa.Pet;
import com.leverx.odata.entity.jpa.User;

import java.util.List;

public interface PetRepository {

    Pet save(Pet pet);

    Pet updateOwnerUserId(long petId, long userId);

    void delete(Pet pet);

    List<Pet> findAll();

    Pet findById(long id);

    List<Cat> findAllCats();

    List<Dog> findAllDogs();

    List<Pet> findAllByUser(User user);
}
