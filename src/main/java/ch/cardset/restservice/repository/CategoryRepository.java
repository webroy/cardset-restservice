package ch.cardset.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import ch.cardset.restservice.entity.Category;

@RestResource(exported = false)
public interface CategoryRepository extends JpaRepository<Category, String> {

}
