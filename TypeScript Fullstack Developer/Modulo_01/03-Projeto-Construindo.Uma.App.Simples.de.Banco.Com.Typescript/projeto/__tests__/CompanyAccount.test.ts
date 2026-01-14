import { CompanyAccount } from '../class/CompanyAccount';

function logEtapa(mensagem: string) {
  process.stdout.write(mensagem + '\n');
}

describe('CompanyAccount com saída limpa', () => {
  let account: CompanyAccount;

  beforeEach(() => {
    account = new CompanyAccount('DIO', 20);
    jest.spyOn(console, 'log').mockImplementation(() => {}); // silencia logs internos
  });

  afterEach(() => {
    (console.log as jest.Mock).mockRestore();
  });

  test('Depósitos e empréstimos', () => {
    account.deposit(100);
    logEtapa(`Após depósito de 100 → saldo esperado: 100, saldo real: ${(account as any).balance}`);

    account.getLoan(50);
    logEtapa(`Após empréstimo de 50 → saldo esperado: 150, saldo real: ${(account as any).balance}`);

    expect((account as any).balance).toBe(150);
  });

  test('Saques', () => {
    account.deposit(100);
    logEtapa(`Saldo inicial → 100`);

    account.withdraw(40);
    logEtapa(`Após saque de 40 → saldo esperado: 60, saldo real: ${(account as any).balance}`);

    account.withdraw(200);
    logEtapa(`Tentativa de saque de 200 → saldo esperado: 60 (sem alteração), saldo real: ${(account as any).balance}`);

    expect((account as any).balance).toBe(60);
  });
});
