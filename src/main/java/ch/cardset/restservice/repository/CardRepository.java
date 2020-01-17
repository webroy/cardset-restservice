package ch.cardset.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import ch.cardset.restservice.entity.Card;
import java.util.List;
import org.springframework.data.repository.query.Param;

@RestResource(exported = false)
public interface CardRepository extends JpaRepository<Card, Integer> {
//    @Query("SELECT c.id, c.card.question, c.card.img, c.card.originalSrc FROM Answer c INNER JOIN c.card WHERE c.card.id = :id")
//    public CardAnswer getCardAnwers(@Param("id") Integer id); // TODO
//    
    
    /*
    @Query("SELECT new ch.cardset.restservice.dto.CardAnswer(c.id, c.question, c.img, c.originalSrc) FROM Card c JOIN Answer a WHERE c.id = :id")
    public CardAnswer getCardWithAnswers(@Param("id") Integer id); // TODO
    */
    
    public Iterable<Card> findByCardSetId(@Param("id") Integer id); // TODO
}
