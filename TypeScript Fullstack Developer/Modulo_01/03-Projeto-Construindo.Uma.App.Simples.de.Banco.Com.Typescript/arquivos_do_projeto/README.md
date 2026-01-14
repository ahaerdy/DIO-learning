# 🏦 DIO Bank - TypeScript & Jest

O **DIO Bank** é um sistema bancário simplificado desenvolvido em TypeScript para praticar conceitos avançados de Programação Orientada a Objetos (POO), como Herança e Abstração, além de garantir a integridade do código através de testes unitários rigorosos com **Jest**.

---

## Tecnologias Utilizadas

* **TypeScript**: Linguagem base para tipagem estática e POO.
* **Jest**: Framework de testes para garantir que as regras de negócio funcionem.
* **ts-node-dev**: Para execução em ambiente de desenvolvimento.
* **jest-html-reporter**: Geração de relatórios visuais de testes.

---

## Arquitetura do Projeto

O projeto utiliza uma classe abstrata como fundação, garantindo que todos os atributos de qualquer conta sejam estritamente **privados**.

### 1. A Classe Base: `DioAccount`

É uma classe **abstrata**, ou seja, serve apenas como modelo e não pode ser instanciada diretamente.

* **Atributos Privados e Imutáveis**:
* `name` e `accountNumber` são protegidos e não podem ser alterados interna ou externamente (uso de `private` e `readonly`).
* `status` e `balance` (saldo) também são controlados internamente.
* **Regras de Depósito e Saque**:
* O depósito (`deposit`) altera o saldo apenas se a conta estiver ativa (`status: true`).
* O saque (`withdraw`) é validado pela regra de negócio: a conta deve estar ativa e possuir saldo superior ou igual ao valor solicitado.



### 2. Especializações (Herança)

* **PeopleAccount**: Estende a conta base adicionando o atributo específico `doc_id`.
* **CompanyAccount**: Implementa o método `getLoan` (empréstimo). O saldo é acrescido do valor solicitado, desde que o status da conta seja `true`.
* **SpecialAccount**: Um novo tipo de conta que não possui atributos extras, mas redefine a lógica de depósito: para cada valor informado, a conta soma **10 unidades adicionais** ao saldo final (Ex: depósito de 100 resulta em saldo de 110).

---

## Estratégia de Testes (Jest)

A robustez do DIO Bank é validada por um conjunto de testes que cobrem sucessos e falhas críticas, garantindo que as regras de negócio acima sejam cumpridas.

### Lógica dos Testes Unitários

Os testes foram desenhados para serem **silenciosos e informativos**, utilizando `process.stdout.write` para logar o progresso sem poluir o terminal com logs internos.

* **Validação de Saldo**: Verifica se o saldo inicial é 0 e se as operações refletem o estado real.
* **Segurança no Saque**: Valida explicitamente a regra de "Saldo Insuficiente", garantindo que o saldo não mude se o saque for maior que o disponível.
* **Polimorfismo e Herança**:
* Garante que o `CompanyAccount` processe o empréstimo somando ao saldo.
* O comportamento da `SpecialAccount` é validado para confirmar o bônus de 10 unidades no depósito.
* **Spying & Mocking**: No arquivo `CompanyAccount.test.ts`, utilizamos `jest.spyOn` para silenciar o `console.log` original, permitindo uma saída de teste limpa.

#### Exemplo de Verificação (Regra de Saque):

```typescript
test('Saques', () => {
  account.deposit(100);
  account.withdraw(200); // Tentativa que viola a regra de saldo
  expect((account as any).balance).toBe(100); // O saldo deve permanecer 100
});

```

---

## Como Executar

1. **Instale as dependências**:

```bash
npm install

```

2. **Execute os testes**:

```bash
npm test

```

3. **Execução Manual (app.ts)**:
O arquivo `app.ts` contém instâncias de todos os tipos de conta (`People`, `Company` e `Special`) para demonstração prática dos métodos.
4. **Relatório de cobertura**:

```bash
npx jest --verbose --runInBand --coverage

```

---

## Resultado dos Testes

Abaixo, a captura de tela demonstrando a execução bem-sucedida de todos os suítes de teste, garantindo que o **DIO Bank** está operando conforme as regras de negócio estabelecidas.

<p align="center">
<img src="./projeto/capture-jest-terminal/terminal_capture-dark.jpg" alt="Relatório Jest" width="840">
</p>