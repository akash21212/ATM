public class MaintainAtm 
{

    public static void updateDeno(int price, int deno, MaintainDeno maintainDeno)
{
        if(price==2000){
                maintainDeno.setTwoThousand(maintainDeno.getTwoThousand()+deno);
        }

        else if(price==500){
                maintainDeno.setFiveHundred(maintainDeno.getFiveHundred()+deno);
        }

        else if(price==100){
                maintainDeno.setOneHundred(maintainDeno.getOneHundred()+deno);
        }
    }

    public static int reduceDeno(int price, int deno, MaintainDeno maintainDeno)
    {
        int f1=0, f2=0;
        if(price==2000)
	   {
            if(maintainDeno.getTwoThousand()>0){
                maintainDeno.setTwoThousand(maintainDeno.getTwoThousand()-deno);
                return 1;
            }

            else if(maintainDeno.getFiveHundred()>0){
                f1=1;
                maintainDeno.setFiveHundred(maintainDeno.getFiveHundred()-deno);
            }

            else if(maintainDeno.getOneHundred()>0){
                f2=1;
                maintainDeno.setOneHundred(maintainDeno.getOneHundred()-deno);
            }

        }

        else if(price==500){
            if(maintainDeno.getFiveHundred()>0){
                if(f1==0){
                    maintainDeno.setFiveHundred(maintainDeno.getFiveHundred()-deno);
                    return 1;
                }
            }

            else if(maintainDeno.getOneHundred()>0)
                if(f2==0)
                maintainDeno.setOneHundred(maintainDeno.getOneHundred()-deno);
        }

        else if(price==100){
            if(maintainDeno.getOneHundred()>0){
                if(f2==0){
                maintainDeno.setOneHundred(maintainDeno.getOneHundred()-deno);
                return 1;}
            }
        }
        return -1;
    }

    public static void updateDepositingAmount(AtmDatabase atmDatabase, MaintainDeno maintainDeno)
    {
        int depositingAmount=maintainDeno.getTwoThousand()*2000+maintainDeno.getFiveHundred()*500+maintainDeno.getOneHundred()*100;
        atmDatabase.setDeposingAmount(depositingAmount);
        atmDatabase.setBalaceAmount(atmDatabase.getDeposingAmount());
    }

    public static void updateWithdraw(AtmDatabase atmDatabase, int withdrawAmount)
    {
        atmDatabase.setWithdrawAmount(withdrawAmount);
        atmDatabase.setBalaceAmount(atmDatabase.getBalaceAmount()-withdrawAmount);
    }

    public static int[] dispensingDenomination(int[] notes, int withdrawAmount)
    {
        int[] noteCounter=new int[notes.length];
        for(int i=0;i<notes.length;i++){
            if(withdrawAmount>=notes[i]){
                noteCounter[i]=withdrawAmount/notes[i];
                withdrawAmount=withdrawAmount-noteCounter[i]*notes[i];
            }
        }
        return noteCounter;
    }

}
