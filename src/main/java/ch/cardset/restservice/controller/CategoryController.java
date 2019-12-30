package ch.cardset.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.cardset.restservice.entity.Category;
import ch.cardset.restservice.repository.CategoryRepository;

import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public Iterable<Category> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Category find(@PathVariable("id") String id) {
        return repository.findOne(id);
    }

    @PostMapping(consumes = "application/json")
    public Category create(@RequestBody Category category) {
        return repository.save(category);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") String id) {
        repository.delete(id);
    }

    @PutMapping(path = "/{categoryName}")
    public Category update(@PathVariable("categoryName") String categoryName, @RequestBody Category category) throws BadHttpRequest {
        if (repository.exists(categoryName)) {
            category.setName(categoryName);
            return repository.save(category);
        } else {
            throw new BadHttpRequest();
        }
    }

}
