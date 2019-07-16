package com.eolivenza.modules.baseProject.repositories.domain.categories;

import com.eolivenza.modules.baseProject.repositories.domain.products.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriesJpaSpringData extends JpaRepository<CategoryJpa, String> {

}

