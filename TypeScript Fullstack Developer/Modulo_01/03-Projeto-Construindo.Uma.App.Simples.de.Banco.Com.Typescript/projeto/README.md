Este é um excelente projeto para demonstrar os fundamentos de **Programação Orientada a Objetos (POO)** com TypeScript e a importância de **Testes Unitários** com Jest.

Abaixo, preparei um `README.md` estruturado para o seu portfólio, detalhando a arquitetura do "DIO Bank" e a estratégia de validação técnica.

---

# 🏦 DIO Bank - TypeScript & Jest

O **DIO Bank** é um sistema bancário simplificado desenvolvido em TypeScript para praticar conceitos avançados de Programação Orientada a Objetos (POO), como Herança e Abstração, além de garantir a integridade do código através de testes unitários rigorosos com **Jest**.

---

## 🚀 Tecnologias Utilizadas

* **TypeScript**: Linguagem base para tipagem estática e POO.
* **Jest**: Framework de testes para garantir que as regras de negócio funcionem.
* **ts-node-dev**: Para execução em ambiente de desenvolvimento.
* **jest-html-reporter**: Geração de relatórios visuais de testes.

---

## 🏗️ Arquitetura do Projeto

O projeto utiliza uma classe abstrata como fundação, permitindo que diferentes tipos de contas compartilhem lógica comum enquanto implementam comportamentos específicos.

### 1. A Classe Base: `DioAccount`

É uma classe **abstrata**, ou seja, serve apenas como modelo e não pode ser instanciada diretamente.

* **Atributos Privados**: `name`, `accountNumber` e `status` são protegidos contra acessos externos diretos.
* **Encapsulamento**: Métodos como `getName()` e `setName()` controlam o acesso aos dados.
* **Lógica de Proteção**: O método `validateStatus()` garante que operações só ocorram em contas ativas.

### 2. Especializações (Herança)

* **PeopleAccount**: Extende a conta base adicionando o atributo `doc_id` (CPF/RG).
* **CompanyAccount**: Introduz o método `getLoan`, permitindo que empresas aumentem seu saldo através de empréstimos.
* **SpecialAccount**: Uma conta VIP onde cada depósito recebe um bônus adicional de  unidades monetárias.

---

## 🧪 Estratégia de Testes (Jest)

A robustez do DIO Bank é validada por um conjunto de testes que cobrem sucessos e falhas críticas.

### Lógica dos Testes Unitários

Os testes foram desenhados para serem **silenciosos e informativos**, utilizando `process.stdout.write` para logar o progresso sem poluir o terminal com logs internos das classes.

* **Validação de Saldo**: Verifica se o saldo inicial é  e se as operações matemáticas (soma/subtração) refletem o estado real da conta.
* **Segurança no Saque**: Testa se a conta impede saques que excedam o saldo disponível (regra de saldo insuficiente).
* **Polimorfismo e Herança**: Garante que o `CompanyAccount` processe o empréstimo corretamente e que o `PeopleAccount` mantenha a integridade do `doc_id`.
* **Spying & Mocking**: No arquivo `CompanyAccount.test.ts`, utilizamos `jest.spyOn` para silenciar o `console.log` original, permitindo uma saída de teste muito mais limpa e profissional.

#### Exemplo de Verificação:

```typescript
test('Saques', () => {
  account.deposit(100);
  account.withdraw(200); // Tentativa inválida
  expect((account as any).balance).toBe(100); // O saldo deve permanecer intacto
});

```

---

## 🛠️ Como Executar

1. **Instale as dependências**:
```bash
npm install

```


2. **Execute os testes**:
```bash
npm test

```

3. **Veja o relatório de cobertura**:
```bash
npm run test:coverage

```

<p align="center">
  <img src="./capture-jest-terminal/terminal_capture-dark.jpg" alt="" width="840">
</p>

---

## 📊 Resultado dos Testes

Abaixo, a captura de tela demonstrando a execução bem-sucedida de todos os suítes de teste, garantindo que o **DIO Bank** está operando conforme as regras de negócio estabelecidas.

*(Insira aqui a sua imagem: `terminal_capture-dark.jpg`)*

---

### Próximos Passos

Gostaria que eu criasse um arquivo de teste específico para a `SpecialAccount` para validar se o bônus de depósito de  está funcionando corretamente?