package com.example.demo.credentials;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CredentialsCommandLineRunner implements CommandLineRunner {

    private final CredentialsRepository repository;
    private final UserRepository userRepository;

    public CredentialsCommandLineRunner(CredentialsRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        repository.save(new Credentials("u1", "p1", "w1", "a1", "a2"));
        userRepository.save(new User("u1","p1"));
        repository.findAll().forEach(System.out::println);
        userRepository.findAll().forEach(System.out::println);
    }
}