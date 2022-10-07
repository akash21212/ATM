import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main 
{
    public static void main(String[] args) 
	{
        try(Scanner scanner=new Scanner(System.in);) 
		{

            AtmData atm=new AtmData();
            MaintainAtm maintainAtm=new MaintainAtm();
            MaintainCustomer maintainCustomer=new MaintainCustomer();

            TreeMap<String, CustomerData> db=new TreeMap<>();
            int[] notes=new int[]{2000, 500, 100};
            MaintainDeno maintainDeno=new MaintainDeno();
            
			while (true)
			{

                int option=0;
                System.out.println("Choose any Option(Before ATM Withdrawl...You need to Load ATM)\n1. Load ATM\n2. Withdrawl From ATM\n3. Check ATM Balance\n4. Create Account\n5. Transfer\n6. Check Account Balance\n7. Display All Customer Details\n8. Deposit\n9. End");

                option=scanner.nextInt();
                System.out.println();
                scanner.nextLine();

                switch (option){
                    case 1:
                    {
                        System.out.println("--------    Load ATM    --------");
                        System.out.println("Enter the Denoto deposit(2000:10, 500:5) : ");
                        String[] denos=scanner.nextLine().split(",");
                        int f=1;
                        for(String seperate:denos)
					{

                            String[] values=seperate.split(":");

                            int price=Integer.valueOf(values[0].trim());
                            int deno=Integer.valueOf(values[1]);
                            if(price<0||deno<0)
					 {
                                System.out.println("Incorrect Deposit price");
                            }

                            else if (price==0||deno==0)
					 {
                                System.out.println("Deposit price cannot be Zero");
                            }

                            else
					 {
                                maintainAtm.updateDeno(price, deno, maintainDeno);
                           	 }

                        	}

                            maintainAtm.updateDepositingAmount(atm, maintainDeno);


                        System.out.println("Deno           No  Value       ");
                        System.out.println("2000                    "+maintainDeno.getTwoThousand()+"       "+2000*maintainDeno.getTwoThousand());
                        System.out.println("500                     "+maintainDeno.getFiveHundred()+"       "+500*maintainDeno.getFiveHundred());
                        System.out.println("100                     "+maintainDeno.getOneHundred()+"       "+100*maintainDeno.getOneHundred());
                        break;

                    }

                    case 2:
                    {

                        System.out.println("--------    Withdrawl    --------");

                        System.out.println("Enter the Account No : ");
                        String accountNo=scanner.next();
                        System.out.println("Enter the Pin No : ");
                        String pinNo=scanner.next();

                        if(maintainCustomer.validAccountNo(accountNo, db)&&maintainCustomer.validPinNo(accountNo, pinNo, db))
					{
                            
					 System.out.println("Enter the price to Withdrawl : ");
                            int withdrawllPrice=scanner.nextInt();
                            if(withdrawllPrice<=0||withdrawllPrice>atm.getBalanceAmount())
					 {
                                System.out.println("Incorrect or Insufficient Funds");break;
                            }

                            else if(db.get(accountNo).getAccountBalance()>10000||db.get(accountNo).getAccountBalance()<100)
					 {
                                System.out.println("Withdrawl Amount should maximum 10000 and minimum 100");break;
                            }

                            db.get(accountNo).withdrawl(accountNo, withdrawllPrice, db);
                            int f=1;
                            int[] dispensingDenominations=maintainAtm.dispensingDenomination(notes, withdrawllPrice);
                            for(int i=0;i< notes.length;i++){
                                if(dispensingDenominations[i]>0)
					     {
                                    f=maintainAtm.reduceDenomination(notes[i], dispensingDenominations[i], maintainDeno);
                                }
                            }

                            System.out.println();

                            if(f==1)
                                maintainAtm.updateWithdrawl(atm, withdrawllPrice);
                            else 
					{
                                System.out.println("No Available Denomination");
                                break;
                            }
                        }

                        else
				  {
                            System.out.println("Invalid Acoount No or Pin No");break;
                        }

                        break;
                    }

                    case 3:
                    {
                        int currentAtmBalance=atm.getBalanceAmount();
                        if(currentAtmBalance<=0)
			    {
                            System.out.println("No Fund");
                            continue;
                        }

                        else
			    {
                            System.out.println("-------- Current ATM Balance --------");
                            System.out.println("Deno           No  Value       ");
                            System.out.println("2000                    "+maintainDeno.getTwoThousand()+"       "+2000*maintainDeno.getTwoThousand());
                            System.out.println("500                     "+maintainDeno.getFiveHundred()+"       "+500*maintainDeno.getFiveHundred());
                            System.out.println("100                     "+maintainDeno.getOneHundred()+"       "+100*maintainDeno.getOneHundred());
                            System.out.println("Total Amount available in ATM is = Rs. "+atm.getBalanceAmount());
                        }
                        break;

                    }

                    case 4:
                    {
                        System.out.println("-------- Create Savings Account -------- ");
                        System.out.println("Enter the New Account No : ");
                        String accountNo=scanner.nextLine();
                        System.out.println("Enter the Customer Name : ");
                        String customerName=scanner.nextLine();
                        System.out.println("Enter the New Pin No : ");
                        String pinNo=scanner.nextLine();
                        System.out.println("Enter the Amount to Deposit : ");
                        int acoountBalance=scanner.nextInt();
                        if(acoountBalance>=500)
			    {
                            CustomerData customerDatabase=new CustomerData(accountNo,customerName, pinNo, acoountBalance);
                            db.put(accountNo, customerDatabase);
                        }

                        else
			    {
                            System.out.println("Minimum Balance Must be 500 or above");
                            break;
                        }
                        break;

                    }

                    case 5:
                    {
                        System.out.println("--------    Money Transfer    --------");

                        System.out.println("Enter the Account No : ");
                        String fromAccountNo=scanner.next();
                        System.out.println("Enter the Pin No : ");
                        String fromPinNo=scanner.next();

                        if(maintainCustomer.validAccountNo(fromAccountNo, db)&&maintainCustomer.validPinNo(fromAccountNo, fromPinNo, db))
			    {
                            System.out.println("Enter the Account No to make Transfer : ");
                            String toAccountNo=scanner.next();
                            System.out.println("Enter the Amount to Transfer : ");
                            int transferAmount=scanner.nextInt();
                            maintainCustomer.transferAmount(fromAccountNo, toAccountNo, transferAmount, db);
                        }

                        else
			    {
                            System.out.println("Invalid Account No or Pin No");
                            break;
                        }
                        break;

                    }

                    case 6:
                    {
                        System.out.println("--------    Check Account Balance    --------");

                        System.out.println("Enter the Account No : ");
                        String accountNo=scanner.next();
                        System.out.println("Enter the Pin No : ");
                        String pinNo=scanner.next();
                        if(maintainCustomer.validAccountNo(accountNo, db)&&maintainCustomer.validPinNo(accountNo, pinNo, db))
			    {
                            System.out.println("AccNo  AccountHolder    PinNo AccountBalance");
                            System.out.println(accountNo+"        "+db.get(accountNo).getCustomerName()+"        "+pinNo+"        "+db.get(accountNo).getAccountBalance());
                        }

                        else
			    {
                            System.out.println("Invalid Account No or Pin No");
                            break;
                        }
                        break;

                    }

                    case 7:
                    {
                        System.out.println("-------- Customer Details ---------");
                        System.out.println("AccNo    Account Holder    Pin No    Account Balance");
                        for(Map.Entry<String, CustomerData> entry: db.entrySet())
			    {
                            System.out.println(entry.getValue().getAccountNo()+"       "+entry.getValue().getCustomerName()+"        "+entry.getValue().getPinNo()+"        "+entry.getValue().getAccountBalance());
                        }
                    }

                    case 8:
                    {
                        System.out.println("-------- Deposit --------");
                        System.out.println("Enter the Account No : ");
                        String accountNo=scanner.next();

                        if(maintainCustomer.validAccountNo(accountNo, db))
  			    {
                            System.out.println("Enter the Amount to Deposit : ");
                            int depositAmount=scanner.nextInt();
                            db.get(accountNo).deposit(accountNo, depositAmount, db);
                            System.out.println("Your Current Account Balance is Rs. "+db.get(accountNo).getAccountBalance());
                        }

                        else
			    {
                            System.out.println("Please Enter valid Account No...");break;
                        }break;

                    }

                    case 9:
                    {
                        System.out.println("Thank You!");
                        System.exit(0);
                    }

                    default:
                    {
                        System.out.println("Please Enter valid option...");
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}