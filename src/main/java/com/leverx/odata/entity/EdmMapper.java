package com.leverx.odata.entity;


import com.leverx.odata.entity.edm.CatEdm;
import com.leverx.odata.entity.edm.DogEdm;
import com.leverx.odata.entity.edm.PetEdm;
import com.leverx.odata.entity.edm.UserEdm;
import com.leverx.odata.entity.jpa.Cat;
import com.leverx.odata.entity.jpa.Dog;
import com.leverx.odata.entity.jpa.Pet;
import com.leverx.odata.entity.jpa.User;
import com.leverx.odata.util.StringConstants;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.leverx.odata.util.StringConstants.ET_CAT_NAME;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class EdmMapper {

    public static UserEdm toUserEdm(User user) {
        if (user == null) {
            return null;
        }
        return UserEdm.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .petsEdm(toPetsEdmList((List<Pet>) user.getPets()))
                .build();
    }

    public static User fromUserEdm(UserEdm userEdm) {
        if (userEdm == null) {
            return null;
        }
        return User.builder()
                .id(userEdm.getId())
                .name(userEdm.getName())
                .email(userEdm.getEmail())
                .pets(toPetEntityList(userEdm.getPetsEdm()))
                .build();
    }

    public static PetEdm toPetEdm(Pet pet) {
        if (pet == null) {
            return null;
        }
        return PetEdm.builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .build();
    }

    public static Pet fromPetEdm(PetEdm petEdm) {
        if (petEdm == null) {
            return null;
        }
        return Pet.builder()
                .id(petEdm.getId())
                .name(petEdm.getName())
                .age(petEdm.getAge())
                .build();
    }

    public static CatEdm toCatEdm(Cat cat) {
        if (cat == null) {
            return null;
        }
        return CatEdm.builder()
                .id(cat.getId())
                .name(cat.getName())
                .age(cat.getAge())
                .userEdm(toUserEdm(cat.getUser()))
                .build();
    }

    public static Cat fromCatEdm(CatEdm catEdm) {
        if (catEdm == null) {
            return null;
        }
        return Cat.builder()
                .id(catEdm.getId())
                .name(catEdm.getName())
                .age(catEdm.getAge())
                .user(fromUserEdm(catEdm.getUserEdm()))
                .build();
    }

    public static DogEdm toDogEdm(Dog dog) {
        if (dog == null) {
            return null;
        }
        return DogEdm.builder()
                .id(dog.getId())
                .name(dog.getName())
                .age(dog.getAge())
                .userEdm(toUserEdm(dog.getUser()))
                .build();
    }

    public static Dog fromDogEdm(DogEdm dogEdm) {
        if (dogEdm == null) {
            return null;
        }
        return (Dog) Pet.builder()
                .id(dogEdm.getId())
                .name(dogEdm.getName())
                .age(dogEdm.getAge())
                .user(fromUserEdm(dogEdm.getUserEdm()))
                .build();
    }

    private static List<PetEdm> toPetsEdmList(List<Pet> pets) {
        if (pets == null) {
            return null;
        }
        return pets.stream()
                .map(EdmMapper::toPetEdm)
                .collect(toList());
    }

    private static List<Pet> toPetEntityList(List<PetEdm> petEdms) {
        if (petEdms == null) {
            return null;
        }
        return petEdms.stream()
                .map(EdmMapper::fromPetEdm)
                .collect(toList());
    }

    private String getPetType(Pet pet) {
        // if ()
        return null;
    }

}

