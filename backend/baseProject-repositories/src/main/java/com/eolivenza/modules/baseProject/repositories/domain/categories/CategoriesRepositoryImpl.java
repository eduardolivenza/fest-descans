package com.eolivenza.modules.baseProject.repositories.domain.categories;

import com.eolivenza.modules.baseProject.application.repositories.CategoriesRepository;
import com.eolivenza.modules.baseProject.domain.model.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class CategoriesRepositoryImpl implements CategoriesRepository {

    private final CategoriesMapper categoriesMapper;
    private final CategoriesJpaSpringData categoriesJpaSpringData;

    @Autowired
    public CategoriesRepositoryImpl(CategoriesMapper categoriesMapper, CategoriesJpaSpringData categoriesJpaSpringData) {
        this.categoriesMapper = categoriesMapper;
        this.categoriesJpaSpringData = categoriesJpaSpringData;
    }

    @Override
    public Category create(Category entity) {
        try {
            CategoryJpa categoryJpa = categoriesMapper.fromDomain(entity);
            categoryJpa = categoriesJpaSpringData.saveAndFlush(categoryJpa);
            return categoriesMapper.toDomain(categoryJpa);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(String email) {
        categoriesJpaSpringData.deleteById(email);
    }

    @Override
    public Category retrieve(String identity) {
        Category category = null;
        Optional<CategoryJpa> categoryJpa = categoriesJpaSpringData.findById(identity);
        if (categoryJpa.isPresent())
            category = categoriesMapper.toDomain(categoryJpa.get());
        return category;
    }

    @Override
    public boolean existsByIdentifier(String externalIdentifier) {
        return categoriesJpaSpringData.existsById(externalIdentifier);
    }

    @Override
    public Category update(Category entity) {
        return null;
    }
}
