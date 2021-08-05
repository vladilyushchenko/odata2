package com.leverx.odata.repository.impl;

import com.leverx.odata.entity.jpa.Cat;
import com.leverx.odata.entity.jpa.Dog;
import com.leverx.odata.entity.jpa.User;
import com.leverx.odata.repository.PetRepository;
import com.leverx.odata.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PetRepositoryImplTest {

    private final PetRepository petRepository = PetRepositoryImpl.getInstance();
    private final UserRepository userRepository = UserRepositoryImpl.getInstance();

    @Test
    void updateUserId_ShouldNotThrow() {
        User userWithPets = detachedUserWithTwoPets();
        User userWithoutPets = detachedUserWithoutPets();
        long petId = userWithPets.getPets().stream().toList().get(0).getId();
        petRepository.updateOwnerUserId(
                petId,
                userWithoutPets.getId());
        assertEquals(userWithoutPets.getId(), petRepository.findById(petId).getUser().getId());
    }

    @Test
    void PetsAreDeletedWhenDeletingUsers() {
        User user = detachedUserWithTwoPets();
        userRepository.delete(user);
        assertNull(userRepository.findById(user.getId()));
        assertEquals(petRepository.findAllByUser(user).size(), 0);
    }

    @Test
    void PetsAreCreatedWhenCreatingUsers() {
        User newUser = nonPersistentUserWithoutPets();
        Cat cat = nonPersistentCatWithoutUser();
        cat.setUser(newUser);
        Dog dog = nonPersistentDogWithoutUser();
        dog.setUser(newUser);
        newUser.setPets(List.of(cat, dog));
        userRepository.save(newUser);
        assertEquals(2, petRepository.findAllByUser(newUser).size());
    }

    private User detachedUserWithTwoPets() {
        User newUser = nonPersistentUserWithoutPets();
        Cat cat = nonPersistentCatWithoutUser();
        cat.setUser(newUser);
        Dog dog = nonPersistentDogWithoutUser();
        dog.setUser(newUser);
        newUser.setPets(List.of(cat, dog));
        userRepository.save(newUser);
        return newUser;
    }

    private User detachedUserWithoutPets() {
        return userRepository.save(nonPersistentUserWithoutPets());
    }

    private User nonPersistentUserWithoutPets() {
        return User.builder()
                .name("u-2")
                .email("e-2")
                .build();
    }

    private Cat nonPersistentCatWithoutUser() {
        return Cat.builder()
                .name("c-2")
                .age(2)
                .build();
    }

    private Dog nonPersistentDogWithoutUser() {
        return Dog.builder()
                .name("d-2")
                .age(2)
                .build();
    }
}