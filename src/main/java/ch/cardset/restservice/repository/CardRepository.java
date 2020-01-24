package ch.cardset.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import ch.cardset.restservice.entity.Card;
import org.springframework.data.repository.query.Param;

@RestResource(exported = false)
public interface CardRepository extends JpaRepository<Card, Integer> {
    /*
    @Query("SELECT new ch.cardset.restservice.dto.CardAnswerDto(c.id, c.question, c.img, c.originalSrc) FROM Card c JOIN Answer a WHERE c.id = :id")
    public CardAnswer getCardWithAnswers(@Param("id") Integer id); // TODO
    */
    
    public Iterable<Card> findByCardSetId(@Param("id") Integer id);
}
