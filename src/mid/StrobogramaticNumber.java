package mid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StrobogramaticNumber {

        public static void main(String [] args) {
            System.out.print("[ ");
            for(String s:findStrobogrammatic(4)){
                System.out.print(s + ", ");
            }
            System.out.print(" ] ");
        }
    
        public static List<String> findStrobogrammatic(int n) {
            List<String> l1 = new ArrayList<>(Arrays.asList("0", "1", "8"));
            if(n == 1) return l1;
            List<String> l2 = new ArrayList<>(Arrays.asList("11", "69", "88", "96"));
            if(n == 2) return l2;
            
    
            l2.add("00");
    
            Set<String> set = new HashSet<>(l2);
    
            int start = 2 + 2;
           
            while(start <= n){
                 Set<String> tempSet = new HashSet<>();
                // put each 
                for(String s:set){
                    for(String l:l2){
                        int mid = s.length() / 2;
                        String temp = s.substring(0,mid) + l + s.substring(mid, s.length());
                        if(temp.charAt(0) != '0')
                             tempSet.add(temp); 
                    }
                }
    
                set.clear();
                set = new HashSet<>(tempSet);
                start = start + 2;
            }
    
            //final case if it is even number
            if(start == n+1){
                Set<String> tempSet = new HashSet<>();
                for(String s:set){ 
                     for(String l:l1){
                          int mid = s.length() / 2;
                          String temp = s.substring(0,mid) + l + s.substring(mid, s.length());
                          if(temp.charAt(0) != '0') 
                            tempSet.add(temp);
                     }
                }
                set.clear();
                set = new HashSet<>(tempSet);
            }
    
             return new ArrayList<>(set);
        }
    
}
