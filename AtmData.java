public class AtmData{
    private int balancePrice;
    private  int depositingAmount;
    private int withdrawlPrice;

    public AtmData() {
    }

    public AtmData(int balancePrice, int depositingAmount, int withdrawlPrice) {
        this.balancePrice = balancePrice;
        this.depositingAmount = depositingAmount;
        this.withdrawlPrice = withdrawlPrice;
    }

    public int getBalaceAmount() {
        return balancePrice;
    }

    public void setBalaceAmount(int balancePrice) {
        this.balancePrice = balancePrice;
    }

    public int getDeposingAmount() {
        return depositingAmount;
    }

    public void setDeposingAmount(int depositingAmount) {
        this.depositingAmount = depositingAmount;
    }

    public int getWithdrawAmount() {
        return withdrawlPrice;
    }

    public void setWithdrawAmount(int withdrawlPrice) {
        this.withdrawlPrice = withdrawlPrice;
    }
}