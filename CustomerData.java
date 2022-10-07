import java.util.TreeSet;
import java.util.*;
public class CustomerData {
    private  String accountNo;
    private String customerName;
    private String pinNo;
    private int accountBalance;


    public CustomerData(){

    }

    public CustomerData(String accountNo, String customerName, String pinNo, int accountBalance) {
        this.accountNo = accountNo;
        this.customerName = customerName;
        this.pinNo = pinNo;
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPinNumber() {
        return pinNo;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPinNumber(String pinNo) {
        this.pinNo = pinNo;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void withdraw(String accountNo, int amount, TreeMap<String, CustomerData> db){
         db.get(accountNo).setAccountBalance(getAccountBalance()-amount);
    }

    public void deposit(String accountNo, int amount, TreeMap<String, CustomerData> db){
        db.get(accountNo).setAccountBalance(getAccountBalance()+amount);
    }
}