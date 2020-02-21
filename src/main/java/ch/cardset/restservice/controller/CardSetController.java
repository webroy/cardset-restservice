package ch.cardset.restservice.controller;

import ch.cardset.restservice.dto.CardSetDto;
import ch.cardset.restservice.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.cardset.restservice.entity.CardSet;
import ch.cardset.restservice.repository.CardRepository;
import ch.cardset.restservice.repository.CardSetRepository;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/cardSet")
public class CardSetController {

    @Autowired
    private CardSetRepository repository;
    @Autowired
    private CardRepository cardRepository;

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
    public CardSet create(@RequestBody CardSetDto cardSet) {
        // check for existing the same CardSet name
        if (repository.findByName(cardSet.getName()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate CardSet Name!");
        }
        
        // Insert cardset and check if it works
        if(repository.addCardSet(cardSet.getName(), cardSet.getCategory(), cardSet.getCardType()) > 0){
            return repository.findTopByOrderByIdDesc();
        }
        
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CardSet not inserted!");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CardSet update(@RequestBody CardSetDto cardSet) {
        // check for existing
        if (repository.findById(cardSet.getId()).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CardSet not found!");
        }
        
        // Update cardset and check if it updated
        if(repository.updateCardSet(cardSet.getId(), cardSet.getName(), cardSet.getCategory(), cardSet.getCardType()) > 0){
            return repository.findById(cardSet.getId()).orElse(null); // TODO get old data back - why?
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CardSet not updated!");
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        // check for existing
        if (repository.findById(id).orElse(null) != null) {
            Iterable<Card> cardsFromSet = cardRepository.findByCardSetId(id);
            if (cardsFromSet != null) {
                cardRepository.deleteAll(cardsFromSet); // delete first all cards from set
            }
            
            repository.deleteById(id); // delete card set!
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CardSet not found!");
        }
    }
}
