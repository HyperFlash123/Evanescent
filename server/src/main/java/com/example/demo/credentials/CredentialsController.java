package com.example.demo.credentials;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.google.gson.Gson; 

@RestController
public class CredentialsController {
    private CredentialsRepository repository;
    private UserRepository userRepository;

    public CredentialsController(CredentialsRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/getCredentials/{unm}/{nm}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Credentials> goodCredentials(@PathVariable(value="unm") String unam, @PathVariable(value="nm") String nam ) {
        if(nam.equals(null)) {
            return repository.findAll().stream().collect(Collectors.toList());
        }
        Map<String, List<Credentials>> mapByUsername = repository
                                                .findAll()
                                                .stream()
                                                .collect(Collectors.groupingBy(Credentials::getUserName));
        Map<String, List<Credentials>> mapByWebsite = repository
                                                .findAll()
                                                .stream()
                                                .collect(Collectors.groupingBy(Credentials::getWebSite));
        Map<String, List<Credentials>> mapByActivestatus = repository
                                                .findAll()
                                                .stream()
                                                .collect(Collectors.groupingBy(Credentials::getActiveStatus));
        if (nam.equalsIgnoreCase("userName")) {
            return mapByUsername.values().stream().flatMap(List::stream).collect(Collectors.toList());
        }else if (nam.equalsIgnoreCase("webSite")) {
            return mapByWebsite.values().stream().flatMap(List::stream).collect(Collectors.toList());
        } else if (nam.equalsIgnoreCase("activeStatus")) {
            return mapByActivestatus.values().stream().flatMap(List::stream).collect(Collectors.toList());
        } else {
            return repository.findAll().stream().collect(Collectors.toList());
        }
    }

    @PostMapping("/addCredentials")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Credentials> addCredentials(@RequestBody String nm) {
        Gson g = new Gson();
        Credentials Credentials = g.fromJson(nm, Credentials.class);
        System.out.println(Credentials);
        repository.save(Credentials);
        return repository.findAll().stream()
                .collect(Collectors.toList());
    }

    @RequestMapping("/users")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<User> users() {
        return userRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/addUsers")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<User> addUsers(@RequestBody String nm) {
        Gson g = new Gson();
        User user = g.fromJson(nm, User.class);
        System.out.println(user);
        userRepository.save(user);
        return userRepository.findAll().stream()
                .collect(Collectors.toList());
    }

}