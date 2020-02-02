package ch.cardset.restservice.controller;

import ch.cardset.restservice.dto.CardAnswerDto;
import ch.cardset.restservice.dto.CardDto;
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
    public Iterable<Card> findCardsFromCategory(@PathVariable("id") Integer id) {
        return repository.findByCardSetId(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Card create(@RequestBody CardDto card) {
        if (repository.addCard(card.getImg(), card.getOriginalSrc(), card.getQuestion(), card.getCardSetId()) > 0){
            return repository.findTopByOrderByIdDesc();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CardSet not inserted!");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Card update(@RequestBody CardDto card) {
        // check for existing
        if (repository.findById(card.getId()).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card not found!");
        }
        
        // Update cardset and check if it updated
        if(repository.updateCard(card.getId(), card.getImg(), card.getOriginalSrc(), card.getQuestion(), card.getCardSetId()) > 0){
            return repository.findTopByOrderByIdDesc(); // TODO get old data back - why?
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card not updated!");
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
