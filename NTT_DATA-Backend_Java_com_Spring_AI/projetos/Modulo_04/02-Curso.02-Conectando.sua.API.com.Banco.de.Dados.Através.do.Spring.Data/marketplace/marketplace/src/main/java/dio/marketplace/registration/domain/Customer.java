package dio.marketplace.registration.domain;

import lombok.Getter;
import org.springframework.util.Assert;
import java.util.UUID; // Adicionado para gerar o UUID aleatório

@Getter
public class Customer {
    private CustomerId id;
    private String name;
    private String email;

    // Construtor Principal
    public Customer(CustomerId id, String name, String email) {
        Assert.notNull(id, "Id must not be null"); // Validação de segurança adicionada
        Assert.notNull(name, "Name must not be null");
        Assert.notNull(email, "Email must not be null");

        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Construtor Secundário (Conveniência para novos cadastros)
    public Customer(String name, String email) {
        this(new CustomerId(UUID.randomUUID()), name, email); // Corrigido aqui!
    }
}