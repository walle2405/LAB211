package mng;

import data.CD;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String>{
    
    Scanner sc = new Scanner(System.in);
    public Menu(){
        super();
    }
    
    public Menu(String[] options){
        for(String o: options){
            this.add(o);
        }
    }
    
    public int getChoice(String title){
        int choice = 0;
        sc = new Scanner(System.in);
        boolean valid = true;
        String inputString;
        do{
            System.out.println("---" + title + "---");
            for (int i = 0; i < this.size(); i++){
                System.out.println("   " + (i+1) + "-" + this.get(i));
            }
            System.out.println("   Others-Exit");
            System.out.print("Choose [1..6]: ");
            inputString = sc.nextLine();
            if(inputString.matches(CD.CHOICE_FORMAT)){
                choice = Integer.parseInt(inputString);
                valid = true;
            } else {
                System.out.println("Invalid choice, try again");
                valid = false;
            }
        }
        while (!valid);
        return choice;
    }
}
