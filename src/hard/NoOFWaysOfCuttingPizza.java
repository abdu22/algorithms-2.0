package hard;

import java.util.Arrays;

public class NoOFWaysOfCuttingPizza {
    /* 
         
        [  A  .  .  ]
        [  A  A  A  ] 
        [  .  .  .  ]  
     */
    public static void main(String[] args) {
        NoOFWaysOfCuttingPizza o = new NoOFWaysOfCuttingPizza();
        String [] pizza = new String [] {"A..","AAA","..."};
        String [] pizza2 = new String [] {".A..A","A.A..","A.AA.","AAAA.","A.AA."};
        int k = 3;
       // System.out.println(o.ways(pizza, k));
        System.out.println(o.ways(pizza2, 5));
    }

    public boolean doWeHavePize(Character [][] p, int r, int c, int x, int y) {
        for(int i=r; i <= x ; i++) {
            for(int j=c; j<= y; j++){
                if( p[i][j] == 'A') return true;
            }
        }
        return false;
    }

    int [][] [] m;
    
    public int ways(String[] pizza, int k) {
        
        int r = pizza.length;
        int c = pizza[0].length();
        Character [][] p = new Character [r][c];
        m = new int [k+1] [r][c];
        
        Arrays.stream(m).forEach(a -> Arrays.stream(a).forEach(b -> Arrays.fill(b, -1)));

        for(int i=0; i < pizza.length ; i++) {
           String row = pizza[i];
           for(int j=0; j < row.length(); j++ ) {
              p[i][j] = row.charAt(j); 
           }
        }
       return    helpSlice(p, 0, 0, k);     
    }

    public int helpSlice(Character [] [] p, int r, int c, int k ){
        if(r >= p.length || c >= p[0].length) return 0;
        if(m[k][r][c] != -1) return m[k][r][c];
        if(k == 1 ){
           return  doWeHavePize(p, r, c, p.length-1, p[0].length-1) ?  1 : 0;
        }
       
        int count = 0;

        // slice horizontally
         for(int i = r; i< p.length; i++){
             // check if possible 
             if(!doWeHavePize(p, r, c, i , p[0].length-1))
                continue;
            
                int temp =  helpSlice(p, i+1, c, k-1);
                if(temp != 0) count += temp;
         }
        // slice vertically
        for(int j=c; j< p[0].length; j++) {
            if(!doWeHavePize(p, r, c, p.length-1, j))
               continue;

               int temp =  helpSlice(p, r, j+1, k-1);  
               if(temp != 0) count += temp;
            } 

      m[k][r][c] = count;      
      return count;
  }

}
