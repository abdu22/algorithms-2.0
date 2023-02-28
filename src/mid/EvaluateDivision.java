package mid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {
     public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(
            Arrays.asList("a", "b"),
            Arrays.asList("b", "c")
        );
        double[] values = new double[] {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(
            Arrays.asList("a", "c"),
            Arrays.asList("b", "a"),
            Arrays.asList("a", "e"),
            Arrays.asList("a", "a"),
            Arrays.asList("x", "x")
        );
        double[] res = new EvaluateDivision().calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(res));
     }
        Map<String, Map<String, Double>> G;
   
       public void addItToGraph(String a, String b, double currVal){
              if(!G.containsKey(a)) 
                         G.put(a, new HashMap<>());
   
                G.get(a).put(b, currVal);
   
                if(!G.containsKey(b))
                           G.put(b, new HashMap<>());
   
                 G.get(b).put(a, 1/currVal); 
       }
   
       public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            
            G = new HashMap<>();
            for(int i=0; i<equations.size(); i++) { 
   
                String a = equations.get(i).get(0);// currentEqu[0];
                String b = equations.get(i).get(1);// currentEqu[1];
   
                if(a.equals(b)) continue;
   
                double currVal = values[i];
                addItToGraph(a,b, currVal);
   
               //refine it 
                /* String [] refinnedEquation = helpRefineEquation(equations.get(i)); 
                if(refinnedEquation[0] != a ||refinnedEquation[1] != b  ) {
                     addItToGraph(refinnedEquation[0],refinnedEquation[1], currVal);
                } */ 
            }
           
          double [] ans = new double [queries.size()];
          int i = 0;
          for(List<String> query:queries){
               //refine it 
               String [] currentEqu = helpRefineEquation(query); 
               String first = currentEqu[0];
               String second = currentEqu[1];
   
               if(!G.containsKey(first) || !G.containsKey(second)) {
                   ans[i++] =  (double)-1;
                   continue;
               }
            /*       if(first.equals(second)) {
                    ans[i++] =  (double)1;
                     continue;
               } */
              
               ans[i++] = (double) dfs(first, second); 
          }  
        return ans;
       }
      
      public Double dfs( String first, String second) {
            
            Map<String, Double> edges = G.get(first);
   
            if(edges.containsKey(second)){
                return edges.get(second);
            }
   
            for(String key : edges.keySet()){
   
                Double current = edges.get(key);
   
                Double next = dfs(key, second);
                if(next == -1.0) return -1.0;
   
                return current * next;
                   
             }
           return -1.0;  
      }
   
   
       // turn  ab/bc to ==>  a/c 
       public String [] helpRefineEquation( List<String> equations){
            char [] a = equations.get(0).toCharArray();
            char [] b = equations.get(1).toCharArray();
   
            Arrays.sort(a);
            Arrays.sort(b);
            //are same
            boolean areSame = false;
            String sameStrg = "";
            if(a.length == b.length) {
                areSame = true;
                 for(int k=0; k<a.length; k++) {
                     if(a[k] != b[k]){
                         areSame =false;
                         break;
                     }
                     sameStrg += a[k];
                } 
            }
   
            if(areSame == true) 
                  return new String [] {sameStrg, sameStrg};
            
           
            int i=0; int j=0;
   
            String str1 = "";
            String str2 = "";
   
            while(i<a.length && j<b.length) {
                 if(a[i] == b[j]){
                     i++; j++;
                     continue; 
                 }else if( a[i] < b[j] ){
                     str1 += a[i];
                     i++;
                 }else {
                     str2 += b[j];
                     j++;
                 }
            }
   
            while(i < a.length){
                 str1 += a[i];
                 i++;
            }
            while(j < b.length){
                 str2 += b[j];
                 j++;
            }
        str1 = str1.length() ==  0 ?  str1+"." : str1;
        str2 = str2.length() ==  0 ? str2+"." : str2;
          return new String [] {str1, str2};         
       }
}
