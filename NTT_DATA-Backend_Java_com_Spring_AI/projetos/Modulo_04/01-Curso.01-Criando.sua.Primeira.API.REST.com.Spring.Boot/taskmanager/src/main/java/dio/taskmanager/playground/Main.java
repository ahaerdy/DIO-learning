package dio.taskmanager.playground;

import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskRepository;
import dio.taskmanager.domain.TaskStatus;
import dio.taskmanager.infrastructure.repository.InMemoryTaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * ATENÇÃO — CLASSE APENAS DIDÁTICA.
 *
 * Este ponto de entrada NÃO faz parte da aplicação Spring Boot e NÃO substitui
 * os testes automatizados (TaskRepositoryTest / InMemoryTaskRepositoryTest).
 * Ele existe só para você rodar manualmente, ver mensagens no terminal e
 * "testar" com as classes Task e InMemoryTaskRepository sem precisar escrever
 * asserts do JUnit — bom para fixar, na prática, o que cada método faz.
 *
 * Repare que esta classe é um Java comum: não tem @SpringBootApplication,
 * não sobe nenhum ApplicationContext, não depende do Spring para rodar.
 * Isso é possível porque Task e InMemoryTaskRepository são POJOs (Plain Old
 * Java Objects) — objetos Java "puros", sem nenhuma amarração ao framework.
 * É exatamente essa independência que a camada "domain" foi desenhada para ter.
 *
 * Como executar: clique com o botão direito neste arquivo no IntelliJ e
 * escolha "Run 'Main.main()'" (ou o ícone de play ao lado do método main).
 * Um projeto Java pode ter vários métodos main — cada um é só um ponto de
 * entrada possível; você escolhe qual quer rodar a cada vez.
 */
public class Main {

    public static void main(String[] args) {

        separador("BLOCO 1 — Criando um Task SEM descrição");
        // Optional.empty() representa "nenhum valor" de forma explícita.
        // Em vez de passar null (que obrigaria todo mundo a lembrar de checar
        // "e se for null?"), o próprio tipo do parâmetro já avisa: "isto pode
        // não existir, trate esse caso".
        Task tarefaSemDescricao = new Task("Estudar Java", Optional.empty());
        imprimirTask(tarefaSemDescricao);

        separador("BLOCO 2 — Criando um Task COM descrição");
        Task tarefaComDescricao =
                new Task("Estudar Spring Boot", Optional.of("Revisar Inversão de Controle"));
        imprimirTask(tarefaComDescricao);

        separador("BLOCO 3 — Testando o Optional<String> da descrição");
        // Aqui mostramos duas formas comuns de "abrir" um Optional com segurança,
        // sem correr o risco de um NullPointerException.
        System.out.println("tarefaSemDescricao tem descrição? "
                + tarefaSemDescricao.getDescription().isPresent());
        System.out.println("Valor usado se estiver vazio: \""
                + tarefaSemDescricao.getDescription().orElse("(sem descrição informada)") + "\"");

        System.out.println("tarefaComDescricao tem descrição? "
                + tarefaComDescricao.getDescription().isPresent());
        System.out.println("Descrição: \""
                + tarefaComDescricao.getDescription().orElse("(sem descrição informada)") + "\"");

        separador("BLOCO 4 — Cada Task nasce com um TaskId próprio e status PENDING");
        // O construtor de Task chama "new TaskId()" internamente, que por sua vez
        // gera um UUID aleatório. Por isso cada Task tem um identificador único,
        // mesmo que o título seja repetido.
        Task outraTarefa = new Task("Estudar Java", Optional.empty());
        System.out.println("id de tarefaSemDescricao: " + tarefaSemDescricao.getId());
        System.out.println("id de outraTarefa:        " + outraTarefa.getId());
        System.out.println("Mesmo título, IDs iguais? "
                + tarefaSemDescricao.getId().equals(outraTarefa.getId()) + " (esperado: false)");
        System.out.println("Toda Task nasce com status: " + tarefaSemDescricao.getStatus());

        // IMPORTANTE (observação didática):
        // Repare que Task não tem nenhum método para MUDAR o status depois de criada
        // (não existe setStatus, porque a classe só tem @Getter, sem @Setter).
        // Ou seja: hoje, uma Task nasce PENDING e não tem, ainda, uma forma pública
        // de virar IN_PROGRESS ou COMPLETED. Isso é esperado nesta etapa do curso —
        // é um comportamento a ser adicionado em vídeos futuros (provavelmente um
        // método de domínio como "iniciar()" ou "concluir()", em vez de um setter
        // genérico, para manter as regras de negócio dentro da própria entidade).

        separador("BLOCO 5 — Criando o repositório em memória");
        // Note o tipo declarado da variável: TaskRepository (a INTERFACE), não
        // InMemoryTaskRepository (a implementação). Só o lado direito do "=" sabe
        // qual implementação concreta está sendo usada. Isso é programar voltado
        // à interface, e é o que permite trocar o repositório no futuro (por um
        // banco de dados, por exemplo) sem alterar quem o utiliza.
        TaskRepository repository = new InMemoryTaskRepository();
        System.out.println("Repositório criado: " + repository.getClass().getSimpleName());

        separador("BLOCO 6 — Salvando tarefas no repositório");
        repository.save(tarefaSemDescricao);
        repository.save(tarefaComDescricao);
        repository.save(outraTarefa);
        System.out.println("3 tarefas foram enviadas para save(). Total armazenado agora: "
                + repository.findAll().size());

        separador("BLOCO 7 — Listando todas as tarefas (findAll)");
        List<Task> todas = repository.findAll();
        for (Task t : todas) {
            imprimirTask(t);
        }

        separador("BLOCO 8 — Buscando uma tarefa específica por id (findById)");
        TaskId idProcurado = tarefaComDescricao.getId();
        Optional<Task> encontrada = repository.findById(idProcurado);
        System.out.println("Buscando id " + idProcurado + " ...");
        System.out.println("Encontrou? " + encontrada.isPresent());
        encontrada.ifPresent(Main::imprimirTask);

        separador("BLOCO 9 — Buscando um id que NÃO existe no repositório");
        // Criamos um TaskId com um UUID aleatório novo, que nunca foi salvo.
        TaskId idInexistente = new TaskId(UUID.randomUUID());
        Optional<Task> naoEncontrada = repository.findById(idInexistente);
        System.out.println("Encontrou? " + naoEncontrada.isPresent() + " (esperado: false)");
        // orElse() evita um "if" manual para tratar o caso de ausência de valor.
        System.out.println("Mensagem amigável: "
                + naoEncontrada.map(Task::getTitle).orElse("Nenhuma tarefa encontrada com esse id."));

        separador("BLOCO 10 — TaskId é um record: igualdade por VALOR, não por referência");
        // Como TaskId é declarado como "record TaskId(UUID id)", o Java já gera
        // automaticamente os métodos equals()/hashCode() comparando o UUID interno.
        // Isso é essencial aqui: o HashMap dentro de InMemoryTaskRepository usa
        // TaskId como chave, então duas instâncias DIFERENTES de TaskId que
        // "embrulham" o mesmo UUID precisam ser tratadas como a MESMA chave.
        TaskId original = tarefaSemDescricao.getId();
        TaskId copiaComMesmoUuid = new TaskId(original.id()); // outro objeto, mesmo UUID
        System.out.println("original == copiaComMesmoUuid (mesma referência)? "
                + (original == copiaComMesmoUuid) + " (esperado: false)");
        System.out.println("original.equals(copiaComMesmoUuid) (mesmo valor)?  "
                + original.equals(copiaComMesmoUuid) + " (esperado: true)");
        // Por causa desse equals() por valor, a busca abaixo funciona mesmo usando
        // um objeto TaskId que nunca foi literalmente guardado no HashMap:
        Optional<Task> encontradaPelaCopia = repository.findById(copiaComMesmoUuid);
        System.out.println("findById(copiaComMesmoUuid) encontrou a tarefa? "
                + encontradaPelaCopia.isPresent() + " (esperado: true)");

        separador("BLOCO 11 — Salvando novamente o MESMO objeto (mesmo TaskId)");
        // Como o HashMap é indexado por TaskId, salvar de novo uma Task cujo id já
        // existe simplesmente SOBRESCREVE a entrada anterior — não cria uma segunda.
        int totalAntes = repository.findAll().size();
        repository.save(tarefaSemDescricao); // já tinha sido salva no BLOCO 6
        int totalDepois = repository.findAll().size();
        System.out.println("Total antes de salvar de novo: " + totalAntes);
        System.out.println("Total depois de salvar de novo: " + totalDepois
                + " (esperado: igual ao de antes, pois o id já existia)");

        separador("BLOCO 12 — Removendo uma tarefa (delete)");
        System.out.println("Antes do delete, total: " + repository.findAll().size());
        repository.delete(tarefaComDescricao.getId());
        System.out.println("Depois do delete, total: " + repository.findAll().size());

        Optional<Task> buscaAposDelete = repository.findById(tarefaComDescricao.getId());
        System.out.println("A tarefa removida ainda é encontrada? "
                + buscaAposDelete.isPresent() + " (esperado: false)");

        separador("FIM — resumo final do repositório");
        System.out.println("Tarefas restantes no repositório:");
        repository.findAll().forEach(Main::imprimirTask);

        System.out.println();
        System.out.println("Lembrete: tudo o que foi validado aqui \"na mão\" (com o olho lendo o");
        System.out.println("terminal) é o mesmo tipo de verificação que TaskRepositoryTest e");
        System.out.println("InMemoryTaskRepositoryTest fazem de forma automática, repetível e");
        System.out.println("com assertivas (assertEquals, assertTrue...) em vez de println.");
    }

    /**
     * Imprime os principais campos de uma Task de forma organizada.
     * Extraído em um método à parte só para não repetir os mesmos 4 println
     * toda vez que quisermos inspecionar uma tarefa — um pequeno exemplo de
     * reaproveitamento de código dentro da própria classe.
     */
    private static void imprimirTask(Task task) {
        System.out.println("  id:          " + task.getId());
        System.out.println("  título:      " + task.getTitle());
        System.out.println("  descrição:   " + task.getDescription().orElse("(nenhuma)"));
        System.out.println("  status:      " + task.getStatus());
        System.out.println("  ------");
    }

    /** Só imprime um cabeçalho separador no terminal, para facilitar a leitura da saída. */
    private static void separador(String titulo) {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println(titulo);
        System.out.println("=".repeat(70));
    }
}