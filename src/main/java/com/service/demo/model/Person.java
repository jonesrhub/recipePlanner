package com.service.demo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * POJO
 */
public class Person implements Identifiable {

    @NotNull
    private Long id;
    private String firstName;
    private String surname;
    private String dob;

    public Person(){

    }

    public Person(String firstname, String surname, String dob){
        this.firstName = firstname;
        this.surname = surname;
        this.dob = dob;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString(){
        return "Person: " +  firstName + " " + surname;
    }
}
