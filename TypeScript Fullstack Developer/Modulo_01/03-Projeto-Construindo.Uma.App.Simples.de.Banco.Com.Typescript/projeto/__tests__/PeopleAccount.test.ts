import { PeopleAccount } from '../class/PeopleAccount';

function logEtapa(mensagem: string) {
  process.stdout.write(mensagem + '\n');
}

describe('PeopleAccount com saída limpa', () => {
  let account: PeopleAccount;

  beforeEach(() => {
    account = new PeopleAccount(1, 'Nath', 10);
  });

  test('Criação de conta', () => {
    logEtapa(`Conta criada → nome: ${account.getName()}, doc_id: ${account.doc_id}, saldo inicial: ${(account as any).balance}`);
    expect(account).toBeInstanceOf(PeopleAccount);
  });

  test('Depósitos', () => {
    account.deposit(100);
    logEtapa(`Após depósito de 100 → saldo esperado: 100, saldo real: ${(account as any).balance}`);

    account.deposit(50.75);
    logEtapa(`Após depósito de 50.75 → saldo esperado: 150.75, saldo real: ${(account as any).balance}`);

    expect((account as any).balance).toBeCloseTo(150.75);
  });

  test('Saques', () => {
    account.deposit(100);
    logEtapa(`Saldo inicial após depósito → 100`);

    account.withdraw(40);
    logEtapa(`Após saque de 40 → saldo esperado: 60, saldo real: ${(account as any).balance}`);

    account.withdraw(200);
    logEtapa(`Tentativa de saque de 200 → saldo esperado: 60 (sem alteração), saldo real: ${(account as any).balance}`);

    expect((account as any).balance).toBe(60);
  });

  test('Alteração de nome', () => {
    account.setName('Maria');
    logEtapa(`Nome alterado → esperado: Maria, real: ${account.getName()}`);

    account.setName('Pedro');
    logEtapa(`Nome alterado novamente → esperado: Pedro, real: ${account.getName()}`);

    expect(account.getName()).toBe('Pedro');
  });

  test('Operações combinadas', () => {
    account.deposit(200);
    logEtapa(`Após depósito de 200 → saldo esperado: 200, saldo real: ${(account as any).balance}`);

    account.withdraw(50);
    logEtapa(`Após saque de 50 → saldo esperado: 150, saldo real: ${(account as any).balance}`);

    account.deposit(25);
    logEtapa(`Após depósito de 25 → saldo esperado: 175, saldo real: ${(account as any).balance}`);

    expect((account as any).balance).toBe(175);
  });
});
