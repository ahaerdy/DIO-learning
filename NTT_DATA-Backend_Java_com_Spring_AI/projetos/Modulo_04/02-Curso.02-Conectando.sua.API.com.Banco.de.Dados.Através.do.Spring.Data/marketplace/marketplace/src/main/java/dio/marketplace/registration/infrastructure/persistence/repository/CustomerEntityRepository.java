package dio.marketplace.registration.infrastructure.persistence.repository; // ajuste o package se necessário

import org.springframework.data.jpa.repository.JpaRepository;
// Importe a entidade de persistência correta:
import dio.marketplace.registration.infrastructure.persistence.entity.Customer;

// O primeiro parâmetro genérico DEVE ser a classe de persistência (entity)
public interface CustomerEntityRepository extends JpaRepository<Customer, String> { // ou Long, dependendo do tipo do seu ID
}