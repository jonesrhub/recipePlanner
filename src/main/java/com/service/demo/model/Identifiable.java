package com.service.demo.model;


public interface Identifiable extends org.springframework.hateoas.Identifiable<Long> {
    public void setId(Long id);
}