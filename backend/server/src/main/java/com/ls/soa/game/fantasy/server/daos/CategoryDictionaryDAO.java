package com.ls.soa.game.fantasy.server.daos;

import com.ls.soa.game.fantasy.api.server.exceptions.CategoryDictionaryNotFoundException;
import com.ls.soa.game.fantasy.server.models.CategoryDictionary;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class CategoryDictionaryDAO extends DAO {
    public CategoryDictionaryDAO(Session session) {
        super(session);
    }

    public CategoryDictionary createOrUpdate(CategoryDictionary categoryDictionary) {
        session.getTransaction().begin();
        session.saveOrUpdate(categoryDictionary);
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
