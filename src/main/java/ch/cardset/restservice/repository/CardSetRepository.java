package ch.cardset.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import ch.cardset.restservice.entity.CardSet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@RestResource(exported = false)
public interface CardSetRepository extends JpaRepository<CardSet, Integer> {
    public CardSet findByCategoryId(int id);
    public CardSet findByName(String name);  
    public Iterable<CardSet> findAllByCategoryId(int id);
    public CardSet findTopByOrderByIdDesc();
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO card_set (name, category_id, card_type_id) VALUES (:name, :category, :type)", nativeQuery = true)
    public int addCardSet(@Param("name") String name, @Param("category") int category, @Param("type") int type); // return number of affected rows
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE card_set SET name = :name, category_id = :category, card_type_id = :type WHERE id = :id", nativeQuery = true)
    public int updateCardSet(@Param("id") int id, @Param("name") String name, @Param("category") int category, @Param("type") int type); // return number of affected rows
}
