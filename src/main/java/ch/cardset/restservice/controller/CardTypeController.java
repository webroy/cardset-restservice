package ch.cardset.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.cardset.restservice.entity.CardType;
import ch.cardset.restservice.repository.CardTypeRepository;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/cardType")
public class CardTypeController {

    @Autowired
    private CardTypeRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<CardType> findAll() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Optional<CardType> find(@PathVariable("id") Integer id) {
        // check for existing
        if (repository.findById(id).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Type not found!");
        } else {
            return repository.findById(id);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public CardType create(@RequestBody CardType cardType) {
        // check for existing the same card type
        if (repository.findByType(cardType.getType()) == null) {
            return repository.save(cardType);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate Card Type!");
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CardType update(@RequestBody CardType cardType) {
        // check for existing
        if (repository.findById(cardType.getId()).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Type not found!");
        }
            
        // check for existing the same card type
        if (repository.findByType(cardType.getType()) == null) {
            return repository.save(cardType);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate Card Type!");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        // check for existing
        if (repository.findById(id).orElse(null) != null) {
            repository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Type not found!");
        }
    }
}
