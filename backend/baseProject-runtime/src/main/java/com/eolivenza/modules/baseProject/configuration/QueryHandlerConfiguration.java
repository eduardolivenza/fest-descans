package com.eolivenza.modules.baseProject.configuration;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.QueryLogger;
import com.eolivenza.modules.baseProject.application.images.queries.ImageCommand;
import com.eolivenza.modules.baseProject.application.images.queries.ImagesQueryHandler;
import com.eolivenza.modules.baseProject.application.products.queries.GetAllProductsQueryHandler;
import com.eolivenza.modules.baseProject.application.products.queries.GetProductQueryHandler;
import com.eolivenza.modules.baseProject.application.suppliers.queries.GetAllSuppliersQueryHandler;
import com.eolivenza.modules.baseProject.application.users.queries.GetAllUsersQueryHandler;
import com.eolivenza.modules.baseProject.application.users.queries.GetUserQueryHandler;
import com.eolivenza.modules.baseProject.application.users.queries.ValidateUserCommand;
import com.eolivenza.modules.baseProject.application.users.queries.ValidateUserQueryHandler;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import com.eolivenza.modules.baseProject.domain.model.user.Session;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Configuration
public class QueryHandlerConfiguration {

    @Bean
    @Primary
    public QueryHandler<Class<String>, Optional<com.eolivenza.modules.baseProject.domain.model.user.User>> getUser(GetUserQueryHandler handler) {
        return new QueryLogger(handler);
    }

    @Bean
    @Primary
    public QueryHandler<ValidateUserCommand, Session> validateUser(ValidateUserQueryHandler handler) {
        return new QueryLogger(handler);
    }

    @Bean
    @Primary
    public QueryHandler<Class<Void>, List<User>> User(GetAllUsersQueryHandler handler) {
        return new QueryLogger(handler);
    }

    @Bean
    @Primary
    public QueryHandler<Class<Void>, List<Product>> getAllProducts(GetAllProductsQueryHandler handler) {
        return new QueryLogger(handler);
    }

    @Bean
    @Primary
    public QueryHandler<Class<Void>, Product> getProduct(GetProductQueryHandler handler) {
        return new QueryLogger(handler);
    }

    @Bean
    @Primary
    public QueryHandler<Class<Void>, List<Supplier>> getSuppliers(GetAllSuppliersQueryHandler handler) {
        return new QueryLogger(handler);
    }

    @Bean
    @Primary
    public QueryHandler<ImageCommand, List<InputStream>> getImages(ImagesQueryHandler handler) {
        return new QueryLogger(handler);
    }
}
