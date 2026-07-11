## Extra (fora do curso): uma classe `Main` para explorar manualmente

> **Nota:** esta seção não corresponde a nenhum vídeo do curso — é um material complementar, criado por iniciativa própria durante os estudos, aproveitando o código já construído nos Vídeos 01 a 03 para praticar e visualizar, no terminal, o comportamento de `Task` e `InMemoryTaskRepository`. Ela **não substitui** os testes automatizados (`TaskRepositoryTest` / `InMemoryTaskRepositoryTest`) — serve apenas como um laboratório manual, didático, para "ver com os próprios olhos" o que cada método faz antes (ou além) de confiar nos asserts do JUnit.

### Por que criar uma segunda classe com `main()`?

Um projeto Java pode ter quantas classes com método `main` você quiser — cada uma é só um *ponto de entrada* possível para rodar aquele arquivo específico. `TaskmanagerApplication` é o ponto de entrada **da aplicação**, que sobe o Spring Boot inteiro (o `ApplicationContext`, a injeção de dependências, etc.). Já a classe `Main` apresentada aqui é **Java puro**, sem nenhuma anotação do Spring — ela não sobe nenhum framework, só cria objetos `Task` e `InMemoryTaskRepository` na mão e imprime o resultado no console com `System.out.println`.

Isso só é possível porque `Task`, `TaskId`, `TaskStatus` e `InMemoryTaskRepository` foram desenhadas como **POJOs** (*Plain Old Java Objects*) na camada `domain`/`infrastructure` — objetos comuns, sem dependência do Spring para existir. É exatamente essa independência que torna a camada de domínio fácil de testar e de explorar isoladamente, seja via JUnit, seja "na unha" como fazemos aqui.

### Onde colocar o arquivo

```
src/main/java/dio/taskmanager/playground/Main.java
```

Foi criado um subpacote `playground` só para deixar visualmente claro, na árvore do projeto, que esta classe é um espaço de experimentação — e não faz parte da API/aplicação propriamente dita. Para executar, basta clicar com o botão direito no arquivo, dentro do IntelliJ, e escolher **Run 'Main.main()'**.

### O que cada bloco do código testa (e por quê)

O código está dividido em blocos numerados, cada um isolado por um separador impresso no console (`====...====`), para deixar a leitura da saída organizada. Aqui vai o detalhamento de cada um:

**Bloco 1 — Criando um `Task` sem descrição**
Cria uma tarefa passando `Optional.empty()` como descrição. É o primeiro contato com a ideia de que "descrição" é **opcional por design**: em vez de aceitar `null` e obrigar quem lê o código a adivinhar se pode vir vazio, o próprio tipo do parâmetro (`Optional<String>`) já avisa isso explicitamente.

**Bloco 2 — Criando um `Task` com descrição**
O mesmo construtor, agora com `Optional.of("...")`. Serve de contraste direto com o Bloco 1, mostrando as duas formas válidas de chamar o construtor.

**Bloco 3 — Testando o `Optional<String>` da descrição**
Aqui exploramos dois métodos centrais de `Optional`:
- `isPresent()` — pergunta "existe valor aqui?" sem arriscar um `NullPointerException`;
- `orElse(valorPadrao)` — devolve o valor real se existir, ou um valor padrão se estiver vazio.

Rodamos os dois em `tarefaSemDescricao` (onde `isPresent()` deve dar `false`) e em `tarefaComDescricao` (onde deve dar `true`), para comparar o comportamento lado a lado.

**Bloco 4 — Cada `Task` nasce com um `TaskId` próprio e status `PENDING`**
Cria uma terceira tarefa (`outraTarefa`) com o **mesmo título** de `tarefaSemDescricao`, só para provar que o `id` gerado é diferente em cada uma — porque o construtor de `Task` chama `new TaskId()` internamente, que por sua vez gera um `UUID` aleatório a cada chamada. Também confirmamos que toda `Task` nova nasce com `status = TaskStatus.PENDING`.

Este bloco inclui uma observação importante sobre o estado atual do código: como a classe `Task` só tem `@Getter` (via Lombok) e **nenhum setter**, hoje não existe uma forma pública de mudar o status de uma tarefa depois que ela foi criada — não há `setStatus()`, nem métodos como `iniciar()` ou `concluir()`. Isso não é um bug: é simplesmente um comportamento que ainda não foi implementado nesta etapa do curso (Vídeos 01 a 03) e ficará provavelmente para uma aula futura, quando o domínio for "orquestrado" com regras de transição de status.

**Bloco 5 — Criando o repositório em memória**
```java
TaskRepository repository = new InMemoryTaskRepository();
```
Repare que o **tipo declarado da variável é a interface** `TaskRepository`, não a classe concreta `InMemoryTaskRepository`. Só o lado direito do `=` sabe qual implementação está sendo usada por trás. Essa é a prática de "programar voltado à interface": todo o resto do código (inclusive este `Main`, a partir daqui) só enxerga os métodos definidos no contrato `TaskRepository` — o que permite, no futuro, trocar `InMemoryTaskRepository` por, digamos, uma implementação com banco de dados, sem mudar uma linha sequer de quem consome o repositório.

**Bloco 6 — Salvando tarefas no repositório**
As três tarefas criadas anteriormente são enviadas para `repository.save(...)`. Em seguida, `repository.findAll().size()` é usado para confirmar que as três realmente foram armazenadas.

**Bloco 7 — Listando todas as tarefas (`findAll`)**
Percorre a lista retornada por `findAll()` com um `for` simples, reaproveitando o método auxiliar `imprimirTask` (explicado mais abaixo) para mostrar `id`, `título`, `descrição` e `status` de cada uma.

**Bloco 8 — Buscando uma tarefa específica por id (`findById`)**
Guarda o `id` de `tarefaComDescricao` numa variável e usa `repository.findById(idProcurado)`. Como o retorno é `Optional<Task>`, usamos `isPresent()` para confirmar que foi encontrada, e `ifPresent(Main::imprimirTask)` para imprimir os dados só se o valor realmente existir — mais uma vez evitando checagens manuais de `null`.

**Bloco 9 — Buscando um id que NÃO existe no repositório**
Criamos um `TaskId` totalmente novo, com um `UUID` aleatório que nunca foi salvo, e chamamos `findById` com ele. O objetivo é observar o "caminho triste": `isPresent()` deve retornar `false`, e usamos `map(...).orElse(...)` para produzir uma mensagem amigável de fallback, em vez de deixar a ausência de valor "vazar" sem tratamento.

**Bloco 10 — `TaskId` é um `record`: igualdade por valor, não por referência**
Este é o bloco mais conceitual do arquivo. Como `TaskId` foi declarado como `record TaskId(UUID id)`, o próprio Java já gera automaticamente os métodos `equals()` e `hashCode()` comparando o `UUID` interno — e não o endereço de memória do objeto. Isso é demonstrado assim:

```java
TaskId original = tarefaSemDescricao.getId();
TaskId copiaComMesmoUuid = new TaskId(original.id()); // outro objeto, mesmo UUID

original == copiaComMesmoUuid          // false → são dois objetos DIFERENTES na memória
original.equals(copiaComMesmoUuid)     // true  → mas "valem" a mesma coisa
```

Isso não é só curiosidade acadêmica: é o motivo pelo qual `findById(copiaComMesmoUuid)` **consegue encontrar** a tarefa correta mesmo sem que esse objeto específico jamais tenha sido inserido no `HashMap` interno de `InMemoryTaskRepository`. Como o `HashMap` usa `equals()`/`hashCode()` para localizar chaves, e `TaskId` (por ser `record`) implementa essas comparações "por valor", qualquer `TaskId` que "embrulhe" o mesmo `UUID` funciona como chave válida de busca.

**Bloco 11 — Salvando novamente o MESMO objeto (mesmo `TaskId`)**
Chama `repository.save(tarefaSemDescricao)` uma segunda vez (ela já havia sido salva no Bloco 6) e compara `findAll().size()` antes e depois. O tamanho **não muda**, porque o `HashMap` está indexado por `TaskId` — salvar de novo uma tarefa cujo id já existe apenas **sobrescreve** a entrada anterior (`storage.put(...)` substitui o valor daquela chave), em vez de criar uma segunda entrada duplicada.

**Bloco 12 — Removendo uma tarefa (`delete`)**
Chama `repository.delete(tarefaComDescricao.getId())`, compara o tamanho de `findAll()` antes e depois (deve cair em 1), e finalmente tenta um `findById` para o mesmo id — que agora deve retornar `Optional.empty()`, confirmando que a remoção realmente aconteceu.

**Bloco final — resumo**
Imprime as tarefas restantes no repositório e fecha com um lembrete: tudo o que foi verificado "a olho nu" aqui é conceitualmente o mesmo tipo de verificação que `TaskRepositoryTest` e `InMemoryTaskRepositoryTest` fazem de forma automática, repetível e com assertivas (`assertEquals`, `assertTrue`...) — só que sem depender de alguém ler o console linha por linha.

### Dois métodos auxiliares

- **`imprimirTask(Task task)`** — extrai a repetição de 4 `println` (id, título, descrição, status) que apareceria toda vez que quiséssemos inspecionar uma tarefa. É um pequeno exemplo de reaproveitamento de código dentro da própria classe.
- **`separador(String titulo)`** — só imprime um cabeçalho com `"=".repeat(70)` antes de cada bloco, para deixar a saída no terminal mais fácil de acompanhar visualmente.

### Código completo

```java
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
 * "brincar" com as classes Task e InMemoryTaskRepository sem precisar escrever
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
```

### O que você deve ver ao rodar

Ao executar, o console mostra, bloco a bloco, os `println` descritos acima — incluindo confirmações como `(esperado: false)` e `(esperado: true)` ao lado de cada verificação, para que você compare mentalmente o que o Java devolveu com o que era esperado, mesmo sem um framework de testes fazendo essa comparação por você.

# ▶️ Saída

```log
15:25:07: Executing ':dio.taskmanager.playground.Main.main()'…

> Task :generateEffectiveLombokConfig UP-TO-DATE
> Task :compileJava UP-TO-DATE
> Task :processResources NO-SOURCE
> Task :classes UP-TO-DATE

> Task :dio.taskmanager.playground.Main.main()

======================================================================
BLOCO 1 — Criando um Task SEM descrição
======================================================================
  id:          TaskId[id=be5d8b33-fe15-46d0-aed1-bfe8bd848bc0]
  título:      Estudar Java
  descrição:   (nenhuma)
  status:      PENDING
  ------

======================================================================
BLOCO 2 — Criando um Task COM descrição
======================================================================
  id:          TaskId[id=189a6af4-acf5-48d2-bc13-3fc9ab744df9]
  título:      Estudar Spring Boot
  descrição:   Revisar Inversão de Controle
  status:      PENDING
  ------

======================================================================
BLOCO 3 — Testando o Optional<String> da descrição
======================================================================
tarefaSemDescricao tem descrição? false
Valor usado se estiver vazio: "(sem descrição informada)"
tarefaComDescricao tem descrição? true
Descrição: "Revisar Inversão de Controle"

======================================================================
BLOCO 4 — Cada Task nasce com um TaskId próprio e status PENDING
======================================================================
id de tarefaSemDescricao: TaskId[id=be5d8b33-fe15-46d0-aed1-bfe8bd848bc0]
id de outraTarefa:        TaskId[id=8d986962-22fe-48ff-b9c1-92250af5d458]
Mesmo título, IDs iguais? false (esperado: false)
Toda Task nasce com status: PENDING

======================================================================
BLOCO 5 — Criando o repositório em memória
======================================================================
Repositório criado: InMemoryTaskRepository

======================================================================
BLOCO 6 — Salvando tarefas no repositório
======================================================================
3 tarefas foram enviadas para save(). Total armazenado agora: 3

======================================================================
BLOCO 7 — Listando todas as tarefas (findAll)
======================================================================
  id:          TaskId[id=8d986962-22fe-48ff-b9c1-92250af5d458]
  título:      Estudar Java
  descrição:   (nenhuma)
  status:      PENDING
  ------
  id:          TaskId[id=be5d8b33-fe15-46d0-aed1-bfe8bd848bc0]
  título:      Estudar Java
  descrição:   (nenhuma)
  status:      PENDING
  ------
  id:          TaskId[id=189a6af4-acf5-48d2-bc13-3fc9ab744df9]
  título:      Estudar Spring Boot
  descrição:   Revisar Inversão de Controle
  status:      PENDING
  ------

======================================================================
BLOCO 8 — Buscando uma tarefa específica por id (findById)
======================================================================
Buscando id TaskId[id=189a6af4-acf5-48d2-bc13-3fc9ab744df9] ...
Encontrou? true
  id:          TaskId[id=189a6af4-acf5-48d2-bc13-3fc9ab744df9]
  título:      Estudar Spring Boot
  descrição:   Revisar Inversão de Controle
  status:      PENDING
  ------

======================================================================
BLOCO 9 — Buscando um id que NÃO existe no repositório
======================================================================
Encontrou? false (esperado: false)
Mensagem amigável: Nenhuma tarefa encontrada com esse id.

======================================================================
BLOCO 10 — TaskId é um record: igualdade por VALOR, não por referência
======================================================================
original == copiaComMesmoUuid (mesma referência)? false (esperado: false)
original.equals(copiaComMesmoUuid) (mesmo valor)?  true (esperado: true)
findById(copiaComMesmoUuid) encontrou a tarefa? true (esperado: true)

======================================================================
BLOCO 11 — Salvando novamente o MESMO objeto (mesmo TaskId)
======================================================================
Total antes de salvar de novo: 3
Total depois de salvar de novo: 3 (esperado: igual ao de antes, pois o id já existia)

======================================================================
BLOCO 12 — Removendo uma tarefa (delete)
======================================================================
Antes do delete, total: 3
Depois do delete, total: 2
A tarefa removida ainda é encontrada? false (esperado: false)

======================================================================
FIM — resumo final do repositório
======================================================================
Tarefas restantes no repositório:
  id:          TaskId[id=8d986962-22fe-48ff-b9c1-92250af5d458]
  título:      Estudar Java
  descrição:   (nenhuma)
  status:      PENDING
  ------
  id:          TaskId[id=be5d8b33-fe15-46d0-aed1-bfe8bd848bc0]
  título:      Estudar Java
  descrição:   (nenhuma)
  status:      PENDING
  ------

Lembrete: tudo o que foi validado aqui "na mão" (com o olho lendo o
terminal) é o mesmo tipo de verificação que TaskRepositoryTest e
InMemoryTaskRepositoryTest fazem de forma automática, repetível e
com assertivas (assertEquals, assertTrue...) em vez de println.

BUILD SUCCESSFUL in 1s
3 actionable tasks: 1 executed, 2 up-to-date
15:25:09: Execution finished ':dio.taskmanager.playground.Main.main()'.
```

# Debugging

O objetivo desta sessão guiada de depuração (*debugging*) é observar o comportamento do código em tempo real, mapeando como a Java Virtual Machine (JVM) aloca e manipula os nossos POJOs na memória. Ao longo das capturas a seguir, analisaremos:

* **O Nascimento dos Objetos:** A alocação das instâncias na memória Heap e a geração de identificadores únicos (UUID).
* **Transição de Estados:** O preenchimento dos atributos internos passo a passo durante a execução dos construtores.
* **Mecânica da Infraestrutura:** O comportamento interno do `InMemoryTaskRepository` e como o `HashMap` armazena as instâncias persistidas.
* **Escopo e Referências:** A diferença entre visualizar parâmetros recebidos, variáveis locais e o ponteiro `this` dentro do contexto de execução.

Abaixo, documentamos o estado da aplicação em pontos estratégicos de parada (*breakpoints*).

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-10-16-53-24.png" alt="" width="100%">
</p>

Estado inicial, debugger disparado. Estado Limpo: Se você olhar para o painel de Variables (Variáveis) na parte inferior ou lateral da sua IDE, verá que a única coisa que existe no escopo local agora é o parâmetro args (o array de Strings vazio do método main).

**🎯 Próxima Ação:** Para não perdermos a oportunidade de ver a alocação de memória acontecer por dentro, utilizaremos o comando **Step Into (F7 - Entrar)**. Isso forçará o depurador a sair do método `main` e mergulhar diretamente no construtor da classe `Task`.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-10-17-12-28.png" alt="" width="100%">
</p>

### 🔍 Análise do Breakpoint: Construtor da Classe `Task`

O depurador está pausado exatamente na assinatura do construtor da classe `Task` (linha 14). Esta é a fase de inicialização, ideal para observar a diferença entre os dados recebidos (parâmetros) e o estado real do objeto na memória Heap (`this`).

#### 1. O Estado Atual na Memória (Painel de Variáveis)
* **Os Parâmetros (Dados de Entrada):**
  * `title`: `"Estudar Java"` (recebido com sucesso da classe `Main`).
  * `description`: `Optional.empty()` (representando explicitamente a ausência de descrição).
* **O Objeto em Construção (`this`):**
  * O objeto já possui um endereço físico alocado na memória Heap (`{Task@927}`).
  * Como as linhas internas do construtor **ainda não foram executadas**, os atributos internos do objeto (`this.title`, `this.id`, etc.) ainda estão com seus valores padrão de inicialização: **`null`**.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-10-17-16-03.png" alt="" width="100%">
</p>

### 🔍 Análise do Breakpoint: Evolução do Objeto `Task`

Como esperado, após a execução dos comandos de passo, as atribuições começaram a se consolidar na memória Heap da JVM. O objeto `this` começou a ganhar corpo e identidade própria.

#### 1. O Estado Atual na Memória (Painel de Variáveis)
* **`this.id` Inicializado:** O atributo `id` deixou de ser `null`. A linha `this.id = new TaskId();` foi executada, gerando um identificador único encapsulado na classe de valor `TaskId` (com o seu respectivo UUID).
* **`this.title` Atribuído:** O campo `this.title` agora aponta corretamente para a string `"Estudar Java"`, transferindo com sucesso o valor que veio do parâmetro do método.
* **Campos Restantes:** O depurador agora avança para carregar o `description` (como um `Optional.empty()`) e o `status` inicial da tarefa, que será definido como `TaskStatus.PENDING`.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-10-17-17-52.png" alt="" width="100%">
</p>

### 🔍 Análise do Breakpoint: Retorno ao Método Estático (`main`)

Após a conclusão do construtor da classe `Task`, o depurador encerra aquele escopo e retorna o fluxo de execução para a classe chamadora, parando na linha seguinte do `Main.java` (linha 43: `imprimirTask(tarefaSemDescricao);`).

#### 1. O Estado Atual na Memória (Painel de Variáveis)
* **Ausência do `this`:** Como estamos dentro do método `public static void main`, o painel de variáveis não exibe o ponteiro `this`. Métodos estáticos pertencem à classe como um todo, e não a uma instância específica, portanto, não há um "objeto atual" no escopo.
* **A Variável Local (`tarefaSemDescricao`):** Agora, a variável que criamos aparece disponível no painel. Ela guarda a referência para o objeto `{Task}` recém-criado na memória Heap. Expandindo essa variável, podemos confirmar que os atributos internos (`id`, `title`, `description` e `status`) foram inicializados com sucesso e contêm os valores exatos que vimos sendo atribuídos no passo anterior.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-10-44-56.png" alt="" width="100%">
</p>

### O Efeito Colateral no Console e a Preparação do Bloco 2

**A Imagem Acima:** O fluxo de execução avançou. Executamos o método `imprimirTask(tarefaSemDescricao)` e o depurador agora está pausado na linha 46, aguardando para iniciar a construção do segundo objeto. Mudamos a nossa lente de observação das Variáveis de Memória para a aba **Console**.

* **O Resultado do Bloco 1:** O console exibe a prova visual de que nosso domínio está funcionando perfeitamente. O objeto que vimos nascer na memória agora teve seu estado impresso: o `id` exibe o UUID gerado, o `status` está como `PENDING` e, de forma elegante, a nossa lógica converteu o `Optional.empty()` da descrição para a string amigável `(nenhuma)`.
* **A Nova Linha Destacada (Azul):** Estamos na largada do "BLOCO 2". A instrução `new Task(...)` está prestes a ser executada para criar a `tarefaComDescricao`. O contraste didático aqui é o uso do `Optional`: em vez de declararmos ausência de valor, estamos empacotando um valor real usando `Optional.of("Revisar Inversão de Controle...")`.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-10-44-23.png" alt="" width="100%">
</p>

### O Nascimento da Segunda Tarefa e o `Optional` Preenchido

**A Imagem Acima:** A instrução de instanciação foi concluída e o depurador parou na linha 48, prestes a enviar nosso novo objeto para o método de impressão.

* **A Coexistência na Memória:** Olhando para o painel de variáveis, fica claro como a JVM gerencia o ciclo de vida. Agora temos duas instâncias distintas e independentes de `Task` vivendo simultaneamente na Heap: a `tarefaSemDescricao` (`{Task@1106}`) e a recém-criada `tarefaComDescricao` (`{Task@1207}`).
* **O "Envelope" do `Optional`:** O grande destaque desta captura está ao expandirmos a `tarefaComDescricao`. O atributo `description` agora não é um espaço vazio. A JVM alocou um objeto `Optional` (`{Optional@1209}`) que atua como um invólucro de proteção. Dentro dele, repousa o valor real: a string `"Revisar Inversão de Controle"`. Isso valida visualmente o design do domínio: a aplicação sabe lidar de forma segura tanto com a presença quanto com a ausência do dado, blindando o código contra os temidos `NullPointerException`.
* **A Linha Destacada (Azul):** O objeto está íntegro. O fluxo aguarda apenas o seu comando para passar o objeto montado como argumento para o `imprimirTask`.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-10-49-17.png" alt="" width="100%">
</p>

### 🔍 A Prova Real no Console — O Poder do `Optional`

**A Imagem Acima:** O fluxo de execução avançou e o depurador parou na linha 64, prestes a imprimir o cabeçalho do **BLOCO 4**. Nossa atenção total agora se volta para o **Console**, que exibe o resultado cristalino das operações do Bloco 3.

* **A Prevenção de Nulos em Ação:** O console ilustra perfeitamente o comportamento do encapsulamento `Optional` que vimos na memória no passo anterior:
  * Ao interrogar a `tarefaSemDescricao`, o método `.isPresent()` retornou `false`. A aplicação não quebrou com o clássico (e temido) erro de *nulo*; em vez disso, o método `.orElse()` assumiu a responsabilidade de forma elegante e injetou a string de segurança `"(sem descrição informada)"`.
  * Ao interrogar a `tarefaComDescricao`, o `.isPresent()` retornou `true` e o valor real (`"Revisar Inversão de Controle"`) foi extraído sem sobressaltos.
* **A Linha Destacada (Azul):** Estamos na porta de entrada do Bloco 4. A instrução `separador(...)` está engatilhada, aguardando para organizar visualmente o nosso próximo experimento sobre a identidade das tarefas.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-10-51-26.png" alt="" width="100%">
</p>

### 🔍 A Prova da Identidade Única (O Poder do UUID)

**A Imagem Acima:** O depurador acabou de executar a linha 67 e agora está pausado na linha 68. Nós instanciamos a `outraTarefa` e estamos prestes a acionar os comandos que imprimirão os IDs no console para fins de comparação.

* **O Clone que não é Clone:** Olhe atentamente para a árvore expandida no painel de variáveis. A nossa nova `outraTarefa` (`{Task@1222}`) foi criada enviando exatamente os mesmos parâmetros da nossa primeira tarefa (`tarefaSemDescricao`): o título é `"Estudar Java"` e a descrição é `Optional.empty()`.
* **A Identidade Garantida pelo Domínio:** Embora os "dados" sejam idênticos, a JVM alocou objetos em endereços de memória distintos (`@1222` vs `@1106`). Mais importante ainda: a nossa regra de domínio funcionou com perfeição. Veja o atributo `id` recém-gerado para a `outraTarefa`: ele encapsula um UUID completamente novo (`0be137b5...`). Isso comprova que a identidade de uma `Task` é definida exclusivamente pelo seu `TaskId`, impossibilitando conflitos mesmo que o usuário cadastre várias tarefas com títulos repetidos.
* **A Linha Destacada (Azul):** O fluxo aguarda a sua liberação para começar a imprimir essas evidências no console.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-10-54-18.png" alt="" width="100%">
</p>

### A Conclusão da Regra de Negócio e a Ponte para a Infraestrutura

**A Imagem Acima:** O depurador encerrou as execuções do Bloco 4 e está pausado na linha 83, aguardando para imprimir o cabeçalho do **BLOCO 5**. A aba Console exibe as evidências finais do nosso laboratório sobre as entidades.

* **A Confirmação Visual (Console):** O console mostra de forma cristalina a diferença entre os UUIDs gerados. A verificação lógica de `Mesmo título, IDs iguais?` retornou `false`, ratificando que a identidade do objeto é soberana e independe de seus atributos textuais. Além disso, confirmamos que a regra de negócio inicializou as tarefas rigorosamente com o status `PENDING`.
* **O Segredo do Encapsulamento (Comentários no Código):** Imediatamente acima da linha destacada, há um bloco de comentários crucial. Ele destaca a ausência intencional de um `@Setter` para o `status`. Esta é uma excelente prática de modelagem rica (*Domain-Driven Design*). Uma entidade não deve ter seu status alterado de forma externa e anêmica por um `setStatus(COMPLETED)`; ela deve sofrer transições de estado através de comportamentos do próprio negócio, como métodos `iniciar()` ou `concluir()`.
* **A Linha Destacada (Azul):** Estamos mudando de marcha. Saímos da criação pura de instâncias e vamos preparar o terreno onde essas tarefas vão morar temporariamente na memória.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-10-57-39.png" alt="" width="100%">
</p>

### O Nascimento da Infraestrutura e o Design por Contratos

**A Imagem Acima:** Acabamos de executar a linha de instanciação (linha 89) e o depurador parou na linha 90. O nosso repositório ganhou vida na memória da aplicação e está pronto para uso.

* **O "Banco de Dados" Vazio na Memória:** Olhando para a aba *Threads & Variables*, vemos o surgimento da nossa variável `repository` (`{InMemoryTaskRepository@1249}`). O detalhe crucial revela-se ao expandi-la: o atributo interno `storage` (um `HashMap`) foi alocado com sucesso e seu estado inicial é explicitamente `size = 0`. A gaveta está montada e completamente limpa, aguardando as entidades.
* **O Poder da Interface (Polimorfismo):** O bloco de comentários (linhas 85-88) e a própria sintaxe da linha executada evidenciam um princípio fundamental de arquitetura de software: *Programar voltado à Interface*. O lado esquerdo da declaração garante o contrato (`TaskRepository`), enquanto apenas o lado direito sabe da implementação concreta. Para o restante da classe `Main`, não importa se é um mapa em memória, um banco de dados relacional ou um arquivo de texto — o contrato de uso permanece o mesmo.
* **A Linha Destacada (Azul):** O fluxo aguarda para imprimir no console o nome da classe concreta que está operando por trás da interface.

### Por que um `HashMap`?

No nosso `InMemoryTaskRepository`, o atributo `storage` foi declarado como um `Map<TaskId, Task>` e instanciado concretamente como um `HashMap`. Mas o que isso significa na prática?

* **O Conceito:** Um `HashMap` é uma estrutura de dados baseada no conceito de dicionário (ou chave-valor). Ele não guarda os itens em uma "fila" sequencial, mas sim associando uma **Chave** exclusiva a um **Valor**.
* **Neste Contexto:** * A **Chave** é o nosso `TaskId` (o objeto que guarda o UUID).
  * O **Valor** é a nossa `Task` inteira (o objeto com título, descrição e status).
* **A Grande Vantagem (Performance):** Se tivéssemos guardado as tarefas em uma simples lista (`ArrayList`), toda vez que precisássemos buscar uma tarefa específica pelo ID (no método `findById`), o Java teria que olhar tarefa por tarefa na lista, do início ao fim, até achar a correta. Com o `HashMap`, o Java aplica uma fórmula matemática (função *hash*) sobre o ID e vai **direto** ao endereço de memória onde aquela tarefa está guardada, de forma instantânea. 

É como um guarda-volumes: você não precisa abrir todos os armários para achar sua mochila; basta olhar o número na sua chave e ir direto à porta certa.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-11-04-11.png" alt="" width="840">
</p>

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-11-33-10.png" alt="" width="100%">
</p>

### 🔍 Anatomia da Criação — O Nascimento no Construtor

**A Imagem Acima:** O depurador está pausado no momento cirúrgico em que a JVM está alocando espaço para a nova instância de `InMemoryTaskRepository`. O frame no Debug indica `<init>:12`, o que significa que estamos exatamente dentro do método construtor da classe.

* **O Estado "null" Transitório:** Note que, no painel de variáveis, o `storage` aparece como `null`. Embora a linha de declaração `private final Map... = new HashMap<>();` esteja presente, a instrução ainda não foi totalmente processada pela JVM. Estamos literalmente no milissegundo anterior à variável receber a referência para o novo objeto `HashMap` na Heap.
* **A Segurança do `final field`:** O atributo é marcado como `final`, o que garante que, uma vez que o construtor termine, essa referência nunca mais será alterada. É uma excelente prática de imutabilidade que blinda o repositório contra reatribuições acidentais (você não quer que alguém substitua seu mapa de dados por outro acidentalmente).
* **O Ciclo de Vida do Objeto:** Este é o momento onde a abstração da "interface" começa a se transformar na "implementação" concreta. Quando você der o próximo passo, a memória será alocada e o `storage` passará a apontar para um endereço válido na memória.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-11-43-39.png" alt="" width="100%">
</p>

### 🔍 A Materialização do Estado (O Poder do Watch)

**A Imagem Acima:** O depurador pausou na linha 90, mas agora temos uma visão clara do estado interno do `repository`. O uso do *Watch* (ou a inspeção direta na aba *Variables*) nos revelou que a variável `storage` deixou de ser `null` e agora aponta para uma instância válida de `HashMap`.

* **O Objeto "Vivo":** Ao expandir a variável `repository` (`{InMemoryTaskRepository@1216}`), vemos que o `storage` está presente e operante como um `HashMap`. O estado `size = 0` é a confirmação visual de que o repositório foi instanciado corretamente, está limpo e pronto para receber sua primeira tarefa.
* **A Utilidade do "Watch":** Diferente do *breakpoint* comum, adicionar um *Watch* permite que você monitore o ciclo de vida dessa variável específica (`storage`) em tempo real, sem que ela desapareça do seu campo de visão ou seja ignorada pelo debugger. Isso nos deu a prova definitiva de que o campo foi inicializado e que a memória foi alocada com sucesso.
* **Prontidão:** Com o `storage` instanciado e ativo na memória, a infraestrutura está totalmente operacional. O contrato definido pela interface `TaskRepository` está pronto para ser exercido pelas chamadas de método que virão a seguir.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-11-49-22.png" alt="" width="100%">
</p>

### 🔍 A Materialização do Estado (O Poder do Watch)

**A Imagem Acima:** O depurador pausou na linha 90, logo após a instaciação do `repository`. Graças à adição do *Watch*, agora temos uma visão clara do estado interno do `repository` e a variável `storage` deixou de ser `null`, apontando para uma instância válida de `HashMap`.

* **O Objeto "Vivo":** Ao expandir a variável `repository` (`{InMemoryTaskRepository@1216}`), vemos que o `storage` está presente e operante como um `HashMap`. O estado `size = 0` é a confirmação visual de que o repositório foi instanciado corretamente, está limpo e pronto para receber sua primeira tarefa.
* **A Utilidade do "Watch":** Diferente da inspeção padrão que pode ocultar campos durante a inicialização, o *Watch* permite que você monitore o ciclo de vida dessa variável específica (`storage`) em tempo real. Isso nos deu a prova definitiva de que o campo foi inicializado e que a memória foi alocada com sucesso.
* **Prontidão:** Com o `storage` instanciado e ativo na memória, a infraestrutura está totalmente operacional. O contrato definido pela interface `TaskRepository` está pronto para ser exercido pelas chamadas de método que virão a seguir.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-11-52-46.png" alt="" width="100%">
</p>

### 🔍 A Persistência Confirmada (Estado Final)

**A Imagem Acima:** O depurador pausou na linha 99, preparando-se para executar `repository.findAll()`. A evidência visual no painel de *Threads & Variables* é clara: nosso `repository` não é mais um objeto vazio; ele agora mantém o estado de toda a aplicação em memória.

* **O "Ground Truth" da Memória (`size = 3`):** Ao expandir o `repository`, o atributo `storage` exibe `size = 3`. Isso confirma que todas as chamadas anteriores ao método `save()` foram bem-sucedidas. O `HashMap` está gerenciando corretamente a associação entre os três `TaskId` (chaves únicas) e suas respectivas instâncias de `Task` (valores).
* **A Estrutura de Busca:** O `HashMap` organiza esses dados de forma que, quando chamarmos `findAll()` ou `findById()`, ele não precisará percorrer uma lista linear (o que seria custoso). Ele já tem os endereços mapeados internamente, permitindo acesso imediato aos dados.
* **Preparação para a Consulta:** Estamos prestes a executar `repository.findAll()`. Internamente, este método vai converter os valores do `HashMap` (`storage.values()`) em uma `List`, permitindo que o `Main` itere sobre os resultados.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-11-54-40.png" alt="" width="100%">
</p>

### 🔍 A Consolidação da Persistência (O Relatório do Console)

**A Imagem Acima:** O fluxo de execução alcançou o "BLOCO 7" e a evidência visual no Console é irrefutável. Após as três chamadas do método `.save()`, o sistema confirma que a memória (`storage`) está populada com 3 entidades `Task`.

* **A Confirmação do Estado:** A saída `Total armazenado agora: 3` valida a lógica da classe `InMemoryTaskRepository`. Não houve perda de dados, e o `HashMap` gerenciou corretamente as chaves (`TaskId`) e os valores (`Task`), independentemente do fato de termos títulos repetidos (como "Estudar Java").
* **Transição de Entrada para Saída:** O código agora avança para a linha 100, onde será executado o método `repository.findAll()`. Este é o momento crucial onde a "persistência" deixa de ser apenas um armazenamento e passa a ser uma consulta de dados.
* **O Fluxo Natural:** Observe que o `main` agora possui dados suficientes para realizar uma operação de listagem completa. O ciclo de vida do objeto, que começou com a criação, passou pela inserção e agora culmina na recuperação, está completo.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-12-02-06.png" alt="" width="100%">
</p>

### 🔍 A Recuperação de Dados (O Poder do findAll())

**A Imagem Acima:** O depurador pausou na linha 104, revelando o sucesso da operação `repository.findAll()`. A variável `todas`, que antes não existia, agora é uma instância de `ArrayList` com `size = 3`.

* **A Transformação de Dados:** O `InMemoryTaskRepository` realizou a ponte entre o armazenamento interno (`HashMap`) e a interface esperada. Como o `HashMap` é otimizado para busca por chave, a conversão `storage.values()` para `ArrayList` foi necessária para que a camada de apresentação (o seu `Main`) pudesse iterar sobre as tarefas como uma lista sequencial.
* **Integridade dos Objetos:** Observe a profundidade do debug: os objetos estão íntegros.
    * **UUID:** O ID único gerado no início do processo foi preservado e está vinculado corretamente a cada tarefa.
    * **Optional:** O campo `description` mostra claramente o uso de `Optional`, seja como `Optional.empty` ou contendo o valor, provando que a lógica de "valor opcional" está sendo respeitada na memória.
* **A Visão do "Banco de Dados" em Memória:** Ao expandir qualquer um dos itens do `ArrayList` (índices 0, 1 ou 2), você está visualizando exatamente como o Java construiu o grafo de objetos em sua Heap. Este é o estado final da sua estrutura de dados após o processamento.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-13-52-57.png" alt="" width="100%">
</p>

## Relatório Final: A Arquitetura do Repositório em Memória

Após uma série de investigações profundas no depurador (debugger), validamos com sucesso o comportamento do nosso `InMemoryTaskRepository`. Este laboratório não apenas confirmou a funcionalidade do código, mas também revelou o funcionamento interno da JVM durante a persistência.

### 📋 Síntese do Fluxo de Trabalho

1.  **O Contrato (Interface):** Estabelecemos uma interface `TaskRepository` que desacopla a regra de negócio da implementação. Isso garante que, futuramente, possamos trocar o `InMemoryTaskRepository` por uma implementação real de banco de dados sem alterar o `Main`.
2.  **A Inicialização (Estado Zero):** Observamos via *Field Watchpoint* que o atributo `storage` inicia como `null` e, após a execução do construtor, torna-se um `HashMap` pronto para uso.
3.  **Persistência e Estado (Ciclo de Vida):**
    * Acompanhamos a evolução do `storage` conforme as chamadas `repository.save()` eram processadas, vendo o `size` do mapa incrementar de `0` para `3`.
    * Confirmamos que o `HashMap` lida corretamente com a identidade das tarefas através dos `TaskId` (UUIDs), tratando tarefas com títulos iguais como entidades distintas.

### 💡 Conclusão Técnica
O sucesso desta implementação reside na correta associação chave-valor dentro do `HashMap`. A capacidade de visualizar esse estado "vivo" durante a execução (especialmente ao confirmar que `repository.findAll()` recupera corretamente os dados convertidos em `ArrayList`) demonstra que a estrutura de dados escolhida é eficiente e atende aos requisitos do projeto.

<p align="center">
  <img src="000-Midia_e_Anexos/2026-07-11-14-01-24.png" alt="" width="100%">
</p>

## 🔍 A Busca de Precisão (findById)

**A Imagem Acima:** O depurador pausou na linha 108, logo após a execução de `repository.findById(idProcurado)`. Este passo demonstra a eficácia do padrão de projeto adotado.

* **O Mecanismo de Busca (`findById`):** Diferente do `findAll()` que recupera todos os objetos, o `findById()` utiliza a chave (`TaskId`) para acessar diretamente o valor (`Task`) no `HashMap`. Isso é uma operação de tempo constante O(1), o que a torna extremamente rápida, independentemente de haver 3 ou 3 milhões de tarefas armazenadas.
* **A Segurança do `Optional`:** Note que a variável `encontrada` é um `Optional<Task>`. O código não retorna `null` caso o ID não exista; ele retorna um `Optional.empty` ou um `Optional` contendo a tarefa encontrada. Isso é uma prática robusta para evitar o temido `NullPointerException` (NPE) nas camadas superiores da aplicação.
* **Integridade dos Dados:** O debug mostra que o objeto `Task@1203` recuperado é idêntico ao objeto original que foi inserido no `storage` anteriormente, provando que a referência na memória foi preservada corretamente.


### 💡 Por que isso é importante?
Ao utilizar um `Map<TaskId, Task>`, transformamos o repositório em uma estrutura de busca eficiente. O `HashMap` usa o *hash code* do `TaskId` para localizar o "balde" (bucket) na memória onde o objeto `Task` está guardado. Isso é muito mais eficiente do que ter que iterar sobre uma lista de milhares de itens para encontrar um específico.

**Conclusão da Etapa:** Você validou com sucesso:
1.  **Criação** (UUIDs únicos).
2.  **Persistência** (HashMap).
3.  **Listagem** (findAll).
4.  **Busca Específica** (findById).


---

