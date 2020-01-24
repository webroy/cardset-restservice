package ch.cardset.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.cardset.restservice.entity.Category;
import ch.cardset.restservice.repository.CategoryRepository;
import java.util.Optional;

import javassist.tools.web.BadHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Category> findAll() {
            return repository.findAll();
        }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Optional<Category> find(@PathVariable("id") Integer id) {
        // check for existing
        if (repository.findById(id).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found!");
        } else {
            return repository.findById(id);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Category create(@RequestBody Category category) throws BadHttpRequest {
        // check for existing the same category name
        if (repository.findByName(category.getName()) == null) {
            return repository.save(category);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate Category Name!");
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Category update(@RequestBody Category category) throws BadHttpRequest {
        // check for existing
        if (repository.findById(category.getId()).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found!");
        }

        // check for existing the same category name
        if (repository.findByName(category.getName()) == null) {
            return repository.save(category);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate Category Name!");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        // check for existing
        if (repository.findById(id).orElse(null) != null) {
            repository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found!");
        }
    }
}
