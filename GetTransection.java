import java.io.File;
import java.util.Scanner;

public class GetTransection{
    public static void main(String [] args){

        Transection t1 = new Transection();
        Scanner sc1 = new Scanner(System.in);
        boolean isAndar = true;
        System.out.println("Enter Account Number: ");
        String findAccount = sc1.nextLine();

        try{
            File fileRead = new File("Accounts/"+findAccount+".txt");
            Scanner sc = new Scanner(fileRead);
            t1.setAccountNo(Long.parseLong(sc.nextLine().toString()));
            t1.setBalance(Double.parseDouble(sc.nextLine().toString()));
            sc.close();
        }
        catch(Exception e){
            System.out.println("Account Not Found! It's Scame");
            isAndar = false;
        }

        int direction;
        while(isAndar){
            System.out.println("Your Balance Is: "+t1.getBalance());
            System.out.println("\nEnter 0 for Quit. \nEnter 1 for Diposit. \nEnter 2 for Withdraw");
            String tempString = sc1.next();
            try{
                direction = Integer.parseInt(tempString);
            }
            catch(Exception e){
                direction = 0;
            }

            if(direction == 0){
                isAndar = false;
            }
            else if(direction == 1){
                System.out.println("Enter Amount: ");
                double rupees = sc1.nextDouble();
                t1.diposite(rupees, findAccount);
            }
            else if(direction == 2){
                System.out.println("Enter Amount: ");
                double rupees = sc1.nextDouble();
                t1.withDraw(rupees, findAccount);
            }
            else{
                isAndar = false;
            }
        }
        t1.readAndUpdate(findAccount);
    }
}