package ch.cardset.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import ch.cardset.restservice.entity.Card;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@RestResource(exported = false)
public interface CardRepository extends JpaRepository<Card, Integer> {
    public Iterable<Card> findByCardSetId(@Param("id") Integer id);
    public Card findTopByOrderByIdDesc();
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO card (img, original_src, question, card_set_id) VALUES (:img, :originalSrc, :question, :cardSetId)", nativeQuery = true)
    public int addCard(@Param("img") String img, @Param("originalSrc") String originalSrc, @Param("question") String question, @Param("cardSetId") int cardSetId); // return number of affected rows
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE card SET img = :img, original_src = :originalSrc, question = :question WHERE id = :id", nativeQuery = true)
    public int updateCard(@Param("id") int id, @Param("img") String img, @Param("originalSrc") String originalSrc, @Param("question") String question); // return number of affected rows
}
