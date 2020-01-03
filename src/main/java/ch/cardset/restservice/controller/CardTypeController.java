package ch.cardset.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.cardset.restservice.entity.CardType;
import ch.cardset.restservice.entity.Category;
import ch.cardset.restservice.repository.CardTypeRepository;

import javassist.tools.web.BadHttpRequest;
import org.springframework.data.domain.Example;

@RestController
@RequestMapping(path = "/cardType")
public class CardTypeController {

    @Autowired
    private CardTypeRepository repository;

    @GetMapping
    public Iterable<CardType> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public CardType find(@PathVariable("id") Integer id) {
        return repository.getOne(id);
    }

    @PostMapping(consumes = "application/json")
    public CardType create(@RequestBody CardType cardType) {
        return repository.save(cardType);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
    }

    @PutMapping(path = "/{type}")
    public CardType update(@PathVariable("type") String type, @RequestBody CardType cardType) throws BadHttpRequest {
        CardType ct = new CardType();
        ct.setType(type);
        
        if (repository.findOne(Example.of(ct)) != null) {
            cardType.setType(type);
            return repository.save(cardType);
        } else {
            throw new BadHttpRequest();
        }
    }

}
