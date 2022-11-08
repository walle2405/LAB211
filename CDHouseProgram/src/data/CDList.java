
package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import tools.*;


public class CDList extends ArrayList<CD> {

    public int counter = this.size();

    public void loadDataFromFile() {
        try {
            List<String> list = NTool.readData();
            for (String s : list) {
                CD cd = new CD(s);
                this.add(cd);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void addCD() {
        String collection, type, title, ID, priceRaw, yearRaw;
        double price = 0;
        int year;
        counter += 1;
        if (checkSize()) {
            boolean valid = true;
            do {
                valid = true;
                ID = NTool.readPattern("Enter ID: ", CD.ID_FORMAT).toUpperCase();
                for (int i = 0; i < this.size(); i++) {
                    if (ID.matches(this.get(i).getID())) {
                        System.out.println("Duplicated ID, try again.");
                        valid = false;
                        break;
                    }
                }

            } while (!valid);
            collection = NTool.readPattern("Enter collection: ", CD.COLLECTION_FORMAT);
            type = NTool.readPattern("Enter type: ", CD.TYPE_FORMAT);
            title = NTool.readNonPattern("Enter title: ");
            do {
                valid = true;
                priceRaw = NTool.readNonPattern("Enter price: ");
                if (priceRaw.matches("\\[A-Za-z]")) {
                    System.out.println("Invalid input, try again.");
                    valid = false;
                } else {
                    price = Double.parseDouble(priceRaw);
                    valid = true;
                }
            } while (!valid);
            yearRaw = NTool.readPattern("Enter year: ", CD.YEAR_FORMAT);
            year = Integer.parseInt(yearRaw);
            this.add(new CD(collection, type, title, price, ID, year));
        } else {
            System.out.println("The catalog has reached its max capacity.");
        }
    }

    public boolean checkSize() {
        if (counter == 700) {
            return false;
        } else {
            return true;
        }
    }

    public void writeToFile() {
        try {
            NTool.writeData(this);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void display() {
        List<String> tempList = new ArrayList<String>();
        for (int i = 0; i < this.size(); i++) {
            tempList.add(this.get(i).getID());
        }
        Collections.sort(tempList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return extractInt(o1) - extractInt(o2);
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                if (num.isEmpty()) {
                    return 0;
                } else {
                    return Integer.parseInt(num);
                }
            }
        });
        List<CD> returnList = new ArrayList<CD>();
        for (int i = 0; i < tempList.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (this.get(j).getID().equals(tempList.get(i))) {
                    returnList.add(this.get(j));
                }
            }
        }
        if (returnList.size() != 0) {
            System.out.println("+--------+----------------+----------+-----------------------------------+-----------+----------+");
            System.out.println("|   ID   |   COLLECTION   |   TYPE   |               TITLE               |   PRICE   |   YEAR   |");
            System.out.println("+--------+----------------+----------+-----------------------------------+-----------+----------+");
            for (int i = 0; i < returnList.size(); i++) {
                System.out.format("|%-8s|%-16s|%-10s|%-35s|$%10.2f|%10d|\n", returnList.get(i).getID(), returnList.get(i).getCollection(), returnList.get(i).getType(), returnList.get(i).getTitle(), returnList.get(i).getPrice(), returnList.get(i).getYear());
            }
            System.out.println("+--------+----------------+----------+-----------------------------------+-----------+----------+");
        } else {
            System.out.println("Empty list!");
        }
    }

    public void search() {
        String input = NTool.readNonPattern("Enter keyword: ");
        System.out.println("+--------+----------------+----------+-----------------------------------+-----------+----------+");
        System.out.println("|   ID   |   COLLECTION   |   TYPE   |               TITLE               |   PRICE   |   YEAR   |");
        System.out.println("+--------+----------------+----------+-----------------------------------+-----------+----------+");
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getTitle().contains(input) || this.get(i).getTitle().toUpperCase().contains(input.toUpperCase())) {
                System.out.format("|%-8s|%-16s|%-10s|%-35s|$%10.2f|%10d|\n", this.get(i).getID(), this.get(i).getCollection(), this.get(i).getType(), this.get(i).getTitle(), this.get(i).getPrice(), this.get(i).getYear());
            }
        }
        System.out.println("+--------+----------------+----------+-----------------------------------+-----------+----------+");
    }

    public void printFromFile() {
        try {
            List<String> str = NTool.readData();
            List<CD> tempList = new ArrayList<CD>();
            List<String> tempList2 = new ArrayList<String>();
            List<CD> returnList = new ArrayList<CD>();
            for (String s : str) {
                CD cd = new CD(s);
                tempList.add(cd);
            }
            for(int i = 0; i < tempList.size(); i++){
                tempList2.add(tempList.get(i).getTitle());
            }
            Collections.sort(tempList2);
            for(int i = 0; i < tempList2.size(); i++){
                for(int j = 0; j < tempList.size(); j++){
                    if(tempList.get(j).getTitle().equals(tempList2.get(i))){
                        returnList.add(tempList.get(j));
                    }
                }
            }
            System.out.println("+--------+----------------+----------+-----------------------------------+-----------+----------+");
            System.out.println("|   ID   |   COLLECTION   |   TYPE   |               TITLE               |   PRICE   |   YEAR   |");
            System.out.println("+--------+----------------+----------+-----------------------------------+-----------+----------+");
            for (int i = 0; i < returnList.size(); i++) {
                System.out.format("|%-8s|%-16s|%-10s|%-35s|$%10.2f|%10d|\n", returnList.get(i).getID(), returnList.get(i).getCollection(), returnList.get(i).getType(), returnList.get(i).getTitle(), returnList.get(i).getPrice(), returnList.get(i).getYear());
            }
            System.out.println("+--------+----------------+----------+-----------------------------------+-----------+----------+");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void update() {
        String choiceRaw;
        int choice = 0;
        boolean valid;
        do {
            Scanner SC = new Scanner(System.in);
            System.out.println("------------------------------------------UPDATE MENU--------------------------------------------");
            System.out.println("   1-Update CD's information");
            System.out.println("   2-Delete CD");
            System.out.println("   Others-Return");
            System.out.print("Choose [1..2]: ");
            choiceRaw = SC.nextLine();
            if (choiceRaw.matches(CD.CHOICE_FORMAT)) {
                choice = Integer.parseInt(choiceRaw);
                valid = true;
                switch (choice) {
                    case 1:
                        updater();
                        break;
                    case 2:
                        deleter();
                        break;
                }
            } else {
                System.out.println("Invalid input, try again");
                valid = false;
            }
        } while (choice > 0 && choice <= 2 && !valid);
    }

    public void updater() {
        String input = NTool.readPattern("Enter CD's ID: ", CD.ID_FORMAT).toUpperCase();
        String newCollection, newCollectionRaw, newType, newTypeRaw, newTitle, newPriceRaw, newID, newYearRaw, sub = "";
        double newPrice;
        int newYear;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(input)) {
                boolean valid = true;
                //update id
                do {
                    valid = true;
                    Scanner SC = new Scanner(System.in);
                    System.out.print("Enter new ID (empty for making no changes): ");
                    newID = SC.nextLine().toUpperCase();
                    if (newID.isEmpty()) {
                        this.get(i).setID(this.get(i).getID());
                    } else if (!newID.matches(CD.ID_FORMAT)) {
                        System.out.println("Invalid input, try again.");
                        valid = false;
                    } else {
                        this.get(i).setID(newID);
                    }
                } while (!valid);
                //update collection
                do {
                    valid = true;
                    Scanner SC = new Scanner(System.in);
                    System.out.print("Enter new collection (empty for making no changes): ");
                    newCollectionRaw = SC.nextLine();

                    if (newCollectionRaw.isEmpty()) {
                        this.get(i).setCollection(this.get(i).getCollection());
                    } else {
                        sub = newCollectionRaw.substring(0, 1).toUpperCase();
                        newCollection = sub + newCollectionRaw.substring(1);
                        if (!newCollection.matches(CD.COLLECTION_FORMAT)) {
                            System.out.println("Invalid input, try again.");
                            valid = false;
                        } else {
                            this.get(i).setCollection(newCollection);
                        }
                    }
                } while (!valid);
                //update type
                do {
                    valid = true;
                    sub = "";
                    Scanner SC = new Scanner(System.in);
                    System.out.print("Enter new type (empty for making no changes): ");
                    newTypeRaw = SC.nextLine();
                    if (newTypeRaw.isEmpty()) {
                        this.get(i).setType(this.get(i).getType());
                    } else {
                        sub = newTypeRaw.substring(0, 1).toUpperCase();
                        newType = sub + newTypeRaw.substring(1);
                        if (!newType.matches(CD.TYPE_FORMAT)) {
                            System.out.println("Invalid input, try again.");
                            valid = false;
                        } else {
                            this.get(i).setType(newType);
                        }
                    }
                } while (!valid);
                //update title
                do {
                    valid = true;
                    sub = "";
                    Scanner SC = new Scanner(System.in);
                    System.out.print("Enter new title (empty for making no changes): ");
                    newTitle = SC.nextLine();
                    if (newTitle.isEmpty()) {
                        this.get(i).setTitle(this.get(i).getTitle());
                    } else {
                        this.get(i).setTitle(newTitle);
                    }
                } while (!valid);
                //update price
                do {
                    valid = true;
                    Scanner SC = new Scanner(System.in);
                    System.out.print("Enter new price (empty for making no changes): ");
                    newPriceRaw = SC.nextLine();
                    if (newPriceRaw.isEmpty()) {
                        this.get(i).setPrice(this.get(i).getPrice());
                    } else {
                        if (newPriceRaw.matches(" \\d+\\.+\\d")) {
                            newPrice = Double.parseDouble(newPriceRaw);
                            this.get(i).setPrice(newPrice);
                        } else {
                            System.out.println("Invalid input, try again.");
                            valid = false;
                        }
                    }
                } while (!valid);
                //update year
                do {
                    valid = true;
                    Scanner SC = new Scanner(System.in);
                    System.out.print("Enter new year (empty for making no changes): ");
                    newYearRaw = SC.nextLine();
                    if (newYearRaw.isEmpty()) {
                        this.get(i).setYear(this.get(i).getYear());
                    } else {
                        if (newYearRaw.matches("\\d{4}")) {
                            newYear = Integer.parseInt(newYearRaw);
                            this.get(i).setYear(newYear);
                        } else {
                            System.out.println("Invalid input, try again.");
                            valid = false;
                        }
                    }
                } while (!valid);
                break;
            }
        }

    }

    public void deleter() {
        String input = NTool.readPattern("Enter CD's ID: ", CD.ID_FORMAT).toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(input)) {
                this.remove(this.get(i));
                break;
            }
        }
        System.out.println("Removed CD: " + input);
    }
}
