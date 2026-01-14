import { CompanyAccount } from './class/CompanyAccount'
import { PeopleAccount } from './class/PeopleAccount'

// const peopleAccount: PeopleAccount = new PeopleAccount(1, 'Nath', 10)
// console.log(peopleAccount)
// peopleAccount.deposit(11)
// console.log(peopleAccount)
// peopleAccount.withdraw(5)
// console.log(peopleAccount)

const companyAccount: CompanyAccount = new CompanyAccount('DIO', 20)
companyAccount.deposit(10)
companyAccount.getBalance()
console.log(companyAccount)
companyAccount.getLoan(100)
companyAccount.getBalance()
console.log(companyAccount)