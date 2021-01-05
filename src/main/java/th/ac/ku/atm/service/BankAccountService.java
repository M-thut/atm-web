package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    private List<BankAccount> bankAccountList ;

    @PostConstruct
    public void postConstruct() {
        this.bankAccountList = new ArrayList<>();
    }

    public List<BankAccount> getBankAccount() {
        return new ArrayList<>(this.bankAccountList) ;
    }

    public void createBankAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount) ;
    }

    public BankAccount findBankAccount(int id) {
        for (BankAccount bankAccount : bankAccountList) {
            if (bankAccount.getId() == id) {
                return bankAccount ;
            }
        } return null ;
    }

    public BankAccount checkId(BankAccount inputBankAccount) {
        BankAccount storedBankAccount = findBankAccount(inputBankAccount.getId()) ;

        if (storedBankAccount != null) {
            return storedBankAccount ;
        } return null ;
    }
}