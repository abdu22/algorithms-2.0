package mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Pair {
    String val;
    Integer freq;
    Pair(String val, Integer freq){
        this.val = val;
        this.freq = freq;
    }
}
public class TopKFrequentWords {
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> m = new HashMap<>();

        for (String word : words) {
            if (!m.containsKey(word)) {
                m.put(word, 1);
            } else {
                m.put(word, m.get(word) + 1);
            }
        }

        List<Pair> listWordPairs = m.keySet()
                .stream()
                .map(key -> new Pair(key, m.get(key)))
                .sorted((x, y) -> {
                    if (x.freq < y.freq) {
                        return 1;
                    } else if (x.freq > y.freq) {
                        return -1;
                    } else {
                        return x.val.compareTo(y.val);
                    }
                }).collect(Collectors.toList());


       // sort(listWordPairs,0,listWordPairs.size()-1);

        List<String> ans = new ArrayList<>();

        for(int i=0; i<listWordPairs.size(); i++){
            if(i == k){
                break;
            }
            ans.add(listWordPairs.get(i).val);
        }
        return  ans;
    }

    public static void sort(List<Pair> l, int i, int j){
        if(i<j){
            int m = (i+j) /2;
            sort(l, i, m);
            sort(l, m+1, j);
            // sort from highes to lowest.
            merge(l, i, m, j);
        }
    }

    public static  void merge(List<Pair> l, int i, int m,  int j){
        List<Pair> temp = new ArrayList<>();

        int a = i; int b = m+1;

        while( a <= m && b <= j ){
            // sort from highest to lowest freq.
            if(l.get(a).freq > l.get(b).freq){
                temp.add(l.get(a));
            }else if(l.get(a).freq < l.get(b).freq)
                temp.add(l.get(b));
            else {
                // if equal. sorty by val (from lowes to higher)
                if( l.get(a).val.compareTo(l.get(b).val) <=0 ){
                    temp.add(l.get(a));
                }else{
                    temp.add(l.get(b));
                }
            }
            a++; b++;
        }

        while(a <= m){
            temp.add(l.get(a));
            a++;
        }

        while(b <= j){
            temp.add(l.get(b));
            b++;
        }

        int x = 0;
        for(int k = i; k <= j ; k++ ){
            l.add(k, temp.get(x));
            x++;
        }
    }
}
