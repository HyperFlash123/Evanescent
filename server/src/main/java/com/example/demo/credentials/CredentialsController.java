package com.example.demo.credentials;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.credentials.CredentialsRepository.Database;
import com.google.gson.Gson; 

@RestController
public class CredentialsController {
    private CredentialsRepository repository;
    private UserRepository userRepository;
    private Database db = new Database();

    public CredentialsController(CredentialsRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/getCredentials/{unm}/{nm}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Credentials> getCredentials(@PathVariable(value="unm") String unam, @PathVariable(value="nm") String nam ) {

        List<Credentials> creds = db.getAllCredentials(unam);
        if(nam.equals(null)) {
            return  creds;
        }
        Map<String, List<Credentials>> mapByUsername = creds
                                                .stream()
                                                .collect(Collectors.groupingBy(Credentials::getUserName));
        Map<String, List<Credentials>> mapByWebsite = creds
                                                .stream()
                                                .collect(Collectors.groupingBy(Credentials::getWebSite));
        Map<String, List<Credentials>> mapByActivestatus = creds
                                                .stream()
                                                .collect(Collectors.groupingBy(Credentials::getActiveStatus));
        if (nam.equalsIgnoreCase("userName")) {
            return mapByUsername.values().stream().flatMap(List::stream).collect(Collectors.toList());
        }else if (nam.equalsIgnoreCase("webSite")) {
            return mapByWebsite.values().stream().flatMap(List::stream).collect(Collectors.toList());
        } else if (nam.equalsIgnoreCase("activeStatus")) {
            return mapByActivestatus.values().stream().flatMap(List::stream).collect(Collectors.toList());
        } else {
            return creds.stream().collect(Collectors.toList());
        }
    }

    @PostMapping("/deleteCredentials")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Credentials> deleteCredentials(@RequestBody String un) {
        Gson g = new Gson();
        Credentials cred = g.fromJson(un, Credentials.class);
        String website = cred.getWebSite();
        String username = cred.getUserName();
        String password = cred.getPassWord();
        if (website.equalsIgnoreCase("w1")) {
            Google.deleteAccount(username, password);
        }
        else if (website.equalsIgnoreCase("Twitter")) {
            Twitter.deleteAccount(username, password);
        }
        else if (website.equalsIgnoreCase("Instagram")) {
            Instagram.deleteAccount(username, password);
        }
        return db.deleteCredentials(cred);
    }

    @PostMapping("/addCredentials")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Credentials> addCredentials(@RequestBody String nm) {
        Gson g = new Gson();
        Credentials cred = g.fromJson(nm, Credentials.class);
        System.out.println(cred);
        return db.addCredentials(cred);
    }

    @RequestMapping("/users")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<User> users() {
        List<User> usr =  db.getAllUsers();
        return usr;
    }

    @PostMapping("/addUsers")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<User> addUsers(@RequestBody String nm) {
        Gson g = new Gson();
        User user = g.fromJson(nm, User.class);
        userRepository.save(user);
        return userRepository.findAll().stream()
                .collect(Collectors.toList());
    }

}