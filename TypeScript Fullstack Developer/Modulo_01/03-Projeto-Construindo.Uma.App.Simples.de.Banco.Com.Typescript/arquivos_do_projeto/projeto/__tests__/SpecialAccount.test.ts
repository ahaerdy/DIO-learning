import { SpecialAccount } from '../class/SpecialAccount';

function logEtapa(mensagem: string) {
  process.stdout.write(mensagem + '\n');
}

describe('SpecialAccount com saída limpa', () => {
  let account: SpecialAccount;

  beforeEach(() => {
    account = new SpecialAccount('Arthur', 30);
    jest.spyOn(console, 'log').mockImplementation(() => {}); // silencia logs internos
  });

  afterEach(() => {
    (console.log as jest.Mock).mockRestore();
  });

  test('Empréstimos com bônus', () => {
    account.getLoan(100);
    logEtapa(`Após empréstimo de 100 (+10 bônus) → saldo esperado: 110, saldo real: ${(account as any).balance}`);

    account.getLoan(50);
    logEtapa(`Após empréstimo de 50 (+10 bônus) → saldo esperado: 170, saldo real: ${(account as any).balance}`);

    expect((account as any).balance).toBe(170);
  });

  test('Depósitos e saques', () => {
    account.deposit(100);
    logEtapa(`Após depósito de 100 → saldo esperado: 100, saldo real: ${(account as any).balance}`);

    account.withdraw(40);
    logEtapa(`Após saque de 40 → saldo esperado: 60, saldo real: ${(account as any).balance}`);

    account.withdraw(200);
    logEtapa(`Tentativa de saque de 200 → saldo esperado: 60 (sem alteração), saldo real: ${(account as any).balance}`);

    expect((account as any).balance).toBe(60);
  });

  test('Operações combinadas', () => {
    account.deposit(100);
    logEtapa(`Após depósito de 100 → saldo esperado: 100, saldo real: ${(account as any).balance}`);

    account.getLoan(50);
    logEtapa(`Após empréstimo de 50 (+10 bônus) → saldo esperado: 160, saldo real: ${(account as any).balance}`);

    account.withdraw(75);
    logEtapa(`Após saque de 75 → saldo esperado: 85, saldo real: ${(account as any).balance}`);

    expect((account as any).balance).toBe(85);
  });
});
