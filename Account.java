import java.io.FileWriter;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

class Account{
    static private String ifscCode = "IBKL0012FD";
    private double balance;
    private long accountNumber;
    private String name;
    private String mobileNumber;
    private String acType;


    Account(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please make sure minimum amount is 2000.");
        System.out.println("Enter Amount: ");
        balance = sc.nextDouble();
        if(balance<2000){
            System.out.println("Please Make Sure Minimum Balance Is 2000");
            balance = 0;
        }
		
		sc.nextLine();
        System.out.println("Enter Customer Name: ");
        name = sc.nextLine();

        System.out.println("Enter Mobile Number: ");
        mobileNumber = sc.nextLine();
        if(mobileNumber.length()!=10){
            System.out.println("Invalid Mobile Number: ");
            mobileNumber = "Not Define";
        }
		
		System.out.println("Enter Account Type \nEnter c for current\nEnter s for Saving: ");
        char tempAcType = sc.next().charAt(0);
        if(tempAcType == 'c' || tempAcType == 'C'){
            acType = "Current";
        }
        else if (tempAcType == 's' || tempAcType == 'S') {
            acType = "Saving";
        }
        else {
            System.out.println("Invalid Account Type");
            acType = "Undefine";
        }

        if(balance == 0 || mobileNumber == "Not Define" || acType == "Undefine"){
            accountNumber = 0;
			balance = 0;
			mobileNumber = "Not Define";
			acType = "Undefine";
        }
		else{
			forActNoGet();
		}
		writerAccountDetails();
    }
    private void writerAccountDetails(){
		String tempAct = accountNumber+"";
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd\tHH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        
        try{
            FileWriter writeDetails = new FileWriter("Accounts/"+tempAct+".txt");
            writeDetails.write(tempAct+"\n"+balance+"\n"+linePrint()+"\n"+
                    "Customer Name\t:\t"+name+"\n"+
                    "Account Number\t:\t"+accountNumber+"\n"+
                    "Account Type\t:\t"+acType+"\n"+
                    "IFSC Code\t\t:\t"+ifscCode+"\n"+
                    "Mobile Number\t:\t"+mobileNumber+"\n"+linePrint()+"\n"+
            
                    "Date\t\tTime \t\tDebit \tCredit \tBalance"+"\n"+linePrint()+"\n"+
                    dtf.format(now)+"\t\t\t"+balance + "\t" + balance
            );
            
            
			writeDetails.close();
            System.out.println("\"Account Creted Successfully\"");
        }
        catch (Exception e){
            System.out.println("File Not Handle");
        }
    }
    private void forActNoGet(){
        Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number(CareFully): ");
		accountNumber = sc.nextLong();
		String tempActStr = accountNumber+"";
		if(tempActStr.length()<8){
			accountNumber = 0;
			System.out.println("Please Make Sure Your Account Number \nCan't accepct him/her money \nReturn Immidiately \nAnd Delete Account Whose name is \"0\"");
		}
    }
    public double getBalance(){
        return balance;
    }
    public String linePrint(){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<=60; i++){
            sb.append("=");
        }
        return sb.toString();
    }
}