package ch.cardset.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import ch.cardset.restservice.entity.CardType;

@RestResource(exported = false)
public interface CardTypeRepository extends JpaRepository<CardType, Integer> {
    public CardType findByType(String type);
}
