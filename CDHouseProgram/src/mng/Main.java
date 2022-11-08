package mng;

import data.CDList;

public class Main {
    public static void main(String[]args){
        String[] options = {"Add CD to catalog", "Search CD", "Display catalog", 
                            "Update CD", "Save to file", "Print CD list from file"};
        Menu menu = new Menu(options);
        CDList cdl = new CDList();
        int choice = 0;
        cdl.loadDataFromFile();
        do{
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.println("Catalog size: " + cdl.size() + "/700");
            choice = menu.getChoice("-----------------------------------------CD MANAGER----------------------------------------");
            
            switch(choice){
                case 1:cdl.addCD(); break;
                case 2:cdl.search(); break;
                case 3:cdl.display(); break;
                case 4:cdl.update(); break;
                case 5:cdl.writeToFile(); break;
                case 6:cdl.printFromFile(); break;
            }
        }
        while (choice >0 && choice <= 6);
    }
}
