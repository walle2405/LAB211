package data;

public class CD {
    public static final String COLLECTION_FORMAT = "Game|Movie|Music";
    public static final String TYPE_FORMAT = "Audio|Video";
    public static final String YEAR_FORMAT = "\\d{4}";
    public static final String ID_FORMAT = "cd\\{4}|CD\\d{4}";
    public static final char SEPERATOR = ',';
    public static final String CHOICE_FORMAT = "\\d{1}";
    
    private String collection;
    private String type;
    private String title;
    private double price;
    private String ID;
    private int year;
    
    public CD(String collection, String type, String title, double price, String ID, int year){
        this.collection = collection;
        this.type = type;
        this.title = title;
        this.price = price;
        this.ID = ID;
        this.year = year;
    }
    
    public CD(String s){
        String [] parts = s.split("" + this.SEPERATOR);
        collection = parts[0].trim();
        type = parts[1].trim();
        title = parts[2].trim();
        price = Double.parseDouble(parts[3].trim());
        ID = parts[4].trim();
        year = Integer.parseInt(parts[5].trim());
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return collection + SEPERATOR + type + SEPERATOR + title + SEPERATOR + price + SEPERATOR + ID + SEPERATOR + year;
    }
}
