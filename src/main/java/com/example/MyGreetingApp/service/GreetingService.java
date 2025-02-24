package com.example.MyGreetingApp.service;

import com.example.MyGreetingApp.model.Greeting;
import com.example.MyGreetingApp.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {

    public String getGreetingMessage() {
        return "{\"message\": \"Hello World\"}";
    }

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting getGreetingMessage(String firstName, String lastName) {
        String message;

        if (firstName != null && lastName != null) {
            message = String.format("Hello, %s %s!", firstName, lastName);
        } else if (firstName != null) {
            message = String.format("Hello, %s!", firstName);
        } else if (lastName != null) {
            message = String.format("Hello, %s!", lastName);
        } else {
            message = "Hello World!";
        }

        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    // UC5 Finding Messages using ID
    public Greeting findGreetingById(Long id) {
        return greetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Greeting not found with id: " + id));
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }
}

