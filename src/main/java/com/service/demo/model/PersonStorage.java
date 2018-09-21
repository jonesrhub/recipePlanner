package com.service.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonStorage<T extends Identifiable> {

    private List<T> people = Collections.synchronizedList(new ArrayList<>());

    @Autowired
    private IdGenerator idGen;

    // add person
    public Long savePerson(T p) {
        people.add(p);
        p.setId(idGen.getNextId());
        return p.getId();
    }



    public Long saveNewPerson(String firstName, String surname, String dob) {
        Person p = new Person();
        p.setId(idGen.getNextId());
        people.add((T) p);
        return p.getId();
    }

    public Long saveNewPerson(String firstName) {
        Person p = new Person();
        p.setId(idGen.getNextId());
        people.add((T) p);
        return p.getId();
    }

    // delete person
    public boolean removePerson(Long id){
        return people.remove(people.stream().filter(pers -> pers.getId() == id).findAny().get());
    }

    // find by id
    public Optional<T> findById(String id) {
        return Optional.of(people.stream().filter(p -> p.getId() == Long.parseLong(id)).findAny().get());
    }

    // Returns all people inside the list
    public Optional<List<T>> returnPeople(){
        return Optional.of(people);
    }

    public Long generateRandomPerson(){
        Person p = new Person();
        return p.getId();
    }


}
