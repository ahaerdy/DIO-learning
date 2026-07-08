package dio.taskmanager.infrastructure.repository;

import dio.taskmanager.domain.TaskRepository;
import dio.taskmanager.domain.TaskRepositoryTest;

// Essa classe NÃO tem mais nenhum teste escrito diretamente nela.
// Ela apenas diz QUAL implementação de TaskRepository deve ser testada,
// e "herda" automaticamente todos os testes definidos em TaskRepositoryTest.
class InMemoryTaskRepositoryTest extends TaskRepositoryTest {

    @Override
    protected TaskRepository createRepository() {
        return new InMemoryTaskRepository();
    }
}
