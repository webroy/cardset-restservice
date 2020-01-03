package ch.cardset.restservice.controller;

import ch.cardset.restservice.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.cardset.restservice.entity.User;
import ch.cardset.restservice.repository.UserRepository;

import javassist.tools.web.BadHttpRequest;
import org.springframework.data.domain.Example;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{username}")
    public User find(@PathVariable("username") String username) {
        return repository.getOne(username);
    }

    @PostMapping(consumes = "application/json")
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping(path = "/{username}")
    public void delete(@PathVariable("username") String username) {
        repository.deleteById(username);
    }

    @PutMapping(path = "/{username}")
    public User update(@PathVariable("username") String username, @RequestBody User user) throws BadHttpRequest {
        User u = new User();
        u.setName(username);

        if (repository.findOne(Example.of(u)) != null) {
            user.setUsername(username);
            return repository.save(user);
        } else {
            throw new BadHttpRequest();
        }
    }

}
