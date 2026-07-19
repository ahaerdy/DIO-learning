package dio.marketplace.registration.infrastructure.persistence.repository; // ajuste o package se necessário

import dio.marketplace.registration.infrastructure.persistence.entity.projection.CustomerExcerpt;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
// Importe a entidade de persistência correta:
import dio.marketplace.registration.infrastructure.persistence.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(excerptProjection = CustomerExcerpt.class)
public interface CustomerEntityRepository extends PagingAndSortingRepository<Customer, UUID>,
        JpaRepository<Customer, UUID> {

    List<Customer> findByFirstNameStartingWithIgnoreCase(@Param("firstName") String firstName);

    @Override
    @RestResource(exported = false)
    void deleteById(UUID id);
}