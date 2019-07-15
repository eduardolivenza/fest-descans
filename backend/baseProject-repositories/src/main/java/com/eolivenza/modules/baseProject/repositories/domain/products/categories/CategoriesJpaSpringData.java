package com.eolivenza.modules.baseProject.repositories.domain.products.categories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoriesJpaSpringData extends CrudRepository<CategoryJpa, String> {

}

