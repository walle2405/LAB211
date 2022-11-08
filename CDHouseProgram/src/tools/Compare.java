package tools;

import java.util.Comparator;


public class Compare implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return extractInt(o1) - extractInt(o2);
    }
    
    int extractInt(String s){
        String num = s.replaceAll("\\D", "");
        if(num.isEmpty()){
            return 0;
        } else {
            return Integer.parseInt(num);
        }
    }
}
