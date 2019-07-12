package com.eolivenza.modules.baseProject.application.users.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.ConfigurationRepository;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.application.repositories.UsersRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.user.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Named
public class GetAllUsersQueryHandler implements QueryHandler<Class<Void>, List<User>> {

    private final UsersRepository usersRepository;
    private final ProductsRepository pRepository;

    @Inject
    public GetAllUsersQueryHandler(UsersRepository usersRepository, ProductsRepository pRepository) {
        this.usersRepository = usersRepository;
        this.pRepository = pRepository;
    }

    /**
     * Retrieve the {@link Configuration}
     *
     * @param getConfigurationRequest void class
     * @return a {@link Configuration}
     **/
    @DomainStrictTransactional
    @Override
    public List<User> apply(Class<Void> getConfigurationRequest) {
        test();

        return (usersRepository.retrieveAll());
    }

    @Override
    public String getName() { return "GetAllUsers"; }


    public void test(){
        Product p = new Product("My Identifier", new HashSet<AvailableProduct>());
        HashSet<AvailableProduct> list = new HashSet<>();
        AvailableProduct size = new AvailableProduct();
        //<TODO> this is not correct. it could be same identifier as parent
        size.identifier = p.getUuid().toString();
        size.size = "135x200";
        size.price = 400;
        list.add(size);
        //p.setAvailableProducts(list);
        this.pRepository.create(p);
    }

}
