package ch.cardset.restservice.controller;

import ch.cardset.restservice.dto.CardAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.cardset.restservice.entity.Card;
import ch.cardset.restservice.repository.CardRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/card")
public class CardController {

    @Autowired
    private CardRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Card> findAll() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Optional<Card> find(@PathVariable("id") Integer id) {
        // check for existing
        if (repository.findById(id).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card not found!");
        } else {
            return repository.findById(id);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/category/{id}")
    public CardAnswer findCardsFromCategory(@PathVariable("id") Integer id) {
        return null; //repository.getCardWithAnswers(id); // TODO get all card with answer from category
    }

    @RequestMapping(method = RequestMethod.POST)
    public Card create(@RequestBody Card category) {
        return repository.save(category);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Card update(@RequestBody Card category) {
        // check for existing
        if (repository.findById(category.getId()).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card not found!");
        }
        
        return repository.save(category);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        // check for existing
        if (repository.findById(id).orElse(null) != null) {
            repository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card not found!");
        }
    }
}
