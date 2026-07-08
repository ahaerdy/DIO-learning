package dio.taskmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Classe de teste ABSTRATA para a interface TaskRepository.
// Ela concentra todos os testes de REGRA DE NEGÓCIO que QUALQUER implementação
// de TaskRepository precisa satisfazer (memória, banco de dados, arquivo, etc.).
// Não é executada diretamente pelo JUnit (é abstrata) — apenas suas subclasses são.
public abstract class TaskRepositoryTest {

    // Campo do tipo da INTERFACE (não da implementação concreta).
    // Isso garante que os testes aqui dentro só usem o que está definido no contrato.
    protected TaskRepository repository;

    // Roda antes de cada teste. Delega a criação do repositório para a subclasse,
    // via o método abstrato createRepository().
    @BeforeEach
    void setUp() {
        repository = createRepository();
    }

    // Método abstrato: cada subclasse (cada implementação de TaskRepository)
    // deve dizer QUAL repositório concreto será testado.
    protected abstract TaskRepository createRepository();

    @Test
    void shouldSaveTask() {
        Task task = new Task("Estudar Java", Optional.of("Revisar coleções"));
        Task saved = repository.save(task);

        assertNotNull(saved.getId());
        assertEquals("Estudar Java", saved.getTitle());
        assertEquals(TaskStatus.PENDING, saved.getStatus());
    }

    @Test
    void shouldFindTaskById() {
        Task task = new Task("Estudar Spring", Optional.empty());
        repository.save(task);

        Optional<Task> found = repository.findById(task.getId());

        assertTrue(found.isPresent());
        assertEquals("Estudar Spring", found.get().getTitle());
    }

    @Test
    void shouldReturnAllTasks() {
        repository.save(new Task("Tarefa 1", Optional.empty()));
        repository.save(new Task("Tarefa 2", Optional.of("Com descrição")));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldDeleteTask() {
        Task task = new Task("Tarefa para deletar", Optional.empty());
        repository.save(task);

        repository.delete(task.getId());

        Optional<Task> found = repository.findById(task.getId());
        assertFalse(found.isPresent());
    }
}
