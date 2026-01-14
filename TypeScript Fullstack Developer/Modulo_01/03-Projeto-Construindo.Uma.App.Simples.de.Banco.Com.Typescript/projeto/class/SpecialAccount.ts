import { DioAccount } from "./DioAccount"

export class SpecialAccount extends DioAccount {

  constructor(name: string, accountNumber: number){
    super(name, accountNumber)
  }

  getLoan = (value: number): void => {
    this.deposit(value+10)    
    // console.log('Voce pegou um empréstimo')
  }
}