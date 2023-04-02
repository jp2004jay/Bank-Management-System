import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

class Transection{
    private long accountNo;
    private double balance;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd\tHH:mm:ss");


    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setBalance(double balance) {
        if(balance<2000){
            System.out.println("Minimum Balance is 2000");
        }
        else{
            this.balance = balance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public double withDraw(double rupees, String findAccount){
        LocalDateTime now = LocalDateTime.now();
        if(rupees>(balance-2000)){
            System.out.println("Insuffician Balace! Please Contact Your Branch");
        }
        else{
            balance = balance - rupees;
            try{
                FileWriter printDebit = new FileWriter("Accounts/"+findAccount+".txt", true);
                printDebit.write("\n"+dtf.format(now)+"\t"+rupees+"\t\t\t" + balance);
                printDebit.close();
            }
            catch(Exception e){
                System.out.println("File Not Found");
            }
        }

        return balance;
    }

    public double diposite(double rupees, String findAccount){
        LocalDateTime now = LocalDateTime.now();
        balance = balance + rupees;

        try{
            FileWriter printCredit = new FileWriter("Accounts/"+findAccount+".txt", true);
            printCredit.write("\n"+dtf.format(now)+"\t\t\t"+rupees+"\t" + balance);
            printCredit.close();
        }
        catch(Exception e){
            System.out.println("File Not Found");
        }

        return balance;
    }
    public void readAndUpdate(String findAccount){
        try{
            File tempFile = new File("Accounts/"+findAccount+".txt");
            Scanner scanFile = new Scanner(tempFile);

            scanFile.nextLine();
            scanFile.nextLine();
            
            StringBuffer fullFile = new StringBuffer();
            while (scanFile.hasNext()){
                fullFile.append(scanFile.nextLine()+"\n");
            }
            scanFile.close();

            FileWriter finalFileWrite = new FileWriter("Accounts/"+findAccount+".txt");
            finalFileWrite.write(accountNo+"\n"+balance+"\n"+fullFile);
            finalFileWrite.close();
            
        }
        catch(Exception e){
            System.out.println("File Not Found!");
            System.out.println(e);
        }
    }
}