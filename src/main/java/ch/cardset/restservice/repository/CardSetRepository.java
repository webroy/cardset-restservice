package ch.cardset.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import ch.cardset.restservice.entity.CardSet;

@RestResource(exported = false)
public interface CardSetRepository extends JpaRepository<CardSet, String> {

}
