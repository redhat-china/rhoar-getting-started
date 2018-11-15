package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fruits")
public class FruitController {
    
    static List<Fruit> repository = new ArrayList<>();
    
    static {
        repository.add(new Fruit(1L, "Cherry"));
        repository.add(new Fruit(2L, "Apple"));
        repository.add(new Fruit(3L, "Banana"));
    }

    @GetMapping
    public List<Fruit> getAll() {
        return repository;
    }

    @GetMapping("/{id}")
    public Fruit getFruit(@PathVariable("id") Long id) {
        for(int i = 0 ; i < repository.size() ; i ++) {
            if(repository.get(i).getId() == id) {
                return repository.get(i);
            }
        }
        return new Fruit("New");
    }
}
