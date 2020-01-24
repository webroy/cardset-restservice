package ch.cardset.restservice.controller;

import ch.cardset.restservice.dto.CardSetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.cardset.restservice.entity.CardSet;
import ch.cardset.restservice.repository.CardSetRepository;
import java.util.Optional;

import javassist.tools.web.BadHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/cardSet")
public class CardSetController {

    @Autowired
    private CardSetRepository repository;

    @RequestMapping(method = RequestMethod.GET, path = "/category/{id}")
    public Iterable<CardSet> findAll(@PathVariable("id") Integer id) {
        return repository.findAllByCategoryId(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Optional<CardSet> find(@PathVariable("id") Integer id) {
        // check for existing
        if (repository.findById(id).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CardSet not found!");
        } else {
            return repository.findById(id);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public CardSetDto create(@RequestBody CardSetDto cardSet) throws BadHttpRequest {
        // check for existing the same CardSet name
        if (repository.findByCategoryId(cardSet.getId()) == null) { // TODO
            CardSet set = new CardSet();
            set.setName(cardSet.getName());
            set.setCategory(1);
            
            CardSet dbSet = repository.save(set);
            cardSet.setId(dbSet.getId());
            return cardSet;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate CardSet Name!");
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CardSet update(@RequestBody CardSet cardSet) throws BadHttpRequest {
        // check for existing
        if (repository.findById(cardSet.getId()).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CardSet not found!");
        }
        
        // check for existing the same CardSet name
        if (repository.findByCategoryId(cardSet.getId()) == null) {
            return repository.save(cardSet);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate CardSet Name!");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        // check for existing
        if (repository.findById(id).orElse(null) != null) {
            repository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CardSet not found!");
        }
    }
}
