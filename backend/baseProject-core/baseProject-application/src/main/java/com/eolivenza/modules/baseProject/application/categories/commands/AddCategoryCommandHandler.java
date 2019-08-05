package com.eolivenza.modules.baseProject.application.categories.commands;


import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.CategoriesRepository;
import com.eolivenza.modules.baseProject.domain.model.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AddCategoryCommandHandler implements CommandHandler<AddCategoryCommand> {

    private final CategoriesRepository categoriesRepository;

    private Logger logger = LoggerFactory.getLogger(AddCategoryCommandHandler.class);

    @Inject
    public AddCategoryCommandHandler(CategoriesRepository productsRepository) {
        this.categoriesRepository = productsRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(AddCategoryCommand addProductCommand) {
        Category category = new Category(
                addProductCommand.identifier,
                addProductCommand.description
        );
        categoriesRepository.create(category);
    }

    @Override
    public String getName() { return "Add available size to a product"; }

}


