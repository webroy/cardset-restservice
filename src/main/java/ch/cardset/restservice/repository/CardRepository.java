package ch.cardset.restservice.repository;

import ch.cardset.restservice.dto.CardAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import ch.cardset.restservice.entity.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@RestResource(exported = false)
public interface CardRepository extends JpaRepository<Card, Integer> {
    //@Query("SELECT new ch.cardset.restservice.dto.CardAnswer(c.id, c.question) FROM Card c LEFT JOIN Answer a on c.id = a.card_id") // WHERE c.id = :id //LEFT JOIN answer a on c.id = a.card_id
    //@Query("SELECT c.id, c.question FROM Card c LEFT JOIN Answer a.Card b ") // WHERE c.id = :id //LEFT JOIN answer a on c.id = a.card_id
    @Query("SELECT c.id, c.question FROM Card c WHERE c.id = :id")
    public CardAnswer getCardWithAnswers(@Param("id") Integer id); // TODO
}
