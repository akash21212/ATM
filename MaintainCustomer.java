import java.util.TreeMap;

public class MaintainCustomer 
{

    public boolean validAccNo(String accountNumber, TreeMap<String, CustomerDatabase> db){
        if(accountNumber.equals(db.get(accountNumber).getAccountNumber())){
            return true;
        }
        return false;
    }

    public boolean validPinNo(String accountNumber, String pinNumber, TreeMap<String, CustomerDatabase> db){
        if(pinNumber.equals(db.get(accountNumber).getPinNumber())){
            return true;
        }
        return false;
    }


    public void transferPrice(String fromAccountNumber, String toAccountNumber, int amount, TreeMap<String, CustomerDatabase> db){
        db.get(fromAccountNumber).withdraw(fromAccountNumber, amount, db);
        db.get(toAccountNumber).deposit(toAccountNumber, amount, db);
    }
}