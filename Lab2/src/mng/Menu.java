package mng;
import java.util.ArrayList;
import java.util.Scanner;

import tools.MyTool;
public class Menu extends ArrayList<String> {
    public Menu() {
        super();
    }

    public Menu(String[] items) {
        super();
        for (String item: items)
            this.add(item);
    }

    public int getChoice(String title){
        Scanner sc = new Scanner(System.in);
        for (int i=0;i<this.size();i++){
            System.out.println(i+1 + "-" + this.get(i) );
        }
        System.out.print("Choose [1.. " + this.size() + "]: ");
        int choice = Integer.parseInt(sc.nextLine());
        return choice;
    }
}




