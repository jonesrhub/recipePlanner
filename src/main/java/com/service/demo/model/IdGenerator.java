package com.service.demo.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class IdGenerator {
    private static AtomicLong nextId = new AtomicLong(1);
    public static long getNextId() {
        return nextId.getAndIncrement();
    }
}