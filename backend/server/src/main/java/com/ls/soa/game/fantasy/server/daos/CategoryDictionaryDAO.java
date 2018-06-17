package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.server.models.CategoryDictionary;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class CategoryDictionaryDAO extends DAO {
    public CategoryDictionary create(CategoryDictionary categoryDictionary) {
        session.getTransaction().begin();
        session.save(categoryDictionary);
        session.getTransaction().commit();

        return categoryDictionary;
    }

    public Optional<CategoryDictionary> findById(long id) {
        return session.getNamedNativeQuery("findCategoryDictionaryById")
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public List<CategoryDictionary> getAll() {
        return (List<CategoryDictionary>) session.getNamedNativeQuery("getAllCategoryDictionaries")
                .getResultStream()
                .collect(Collectors.toList());
    }

    public CategoryDictionary update(CategoryDictionary newCategoryDictionary) throws CategoryDictionaryNotFoundException {
        Optional<CategoryDictionary> oldCategoryDictionary = findById(newCategoryDictionary.getId());

        if (!oldCategoryDictionary.isPresent()) {
            throw new CategoryDictionaryNotFoundException();
        }

        CategoryDictionary entity = oldCategoryDictionary.get();
        entity.merge(newCategoryDictionary);

        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();

        return entity;
    }

    public void delete(long categoryDictionaryId) throws CategoryDictionaryNotFoundException {
        Optional<CategoryDictionary> categoryDictionary = findById(categoryDictionaryId);

        if (!categoryDictionary.isPresent()) {
            throw new CategoryDictionaryNotFoundException();
        }

        session.getTransaction().begin();
        session.delete(categoryDictionary.get());
        session.getTransaction().commit();
    }
}
