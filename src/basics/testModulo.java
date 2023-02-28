package basics;

/* 
 * 
 
  Modulo 10^9+7 (1000000007) is used to avoid overflow.

  
 */
public class testModulo {
    
  public static void main(String[] args) {
     System.out.println(moudlo());
  }

  public static long moudlo(){
    int n = 1000000007; // int n = (10^9) +7; ** don't use this format

    long factorial = 1;
     for ( int i=1; i <100 ; i++) {
         factorial = (factorial * i) % n; 
        //factorial = (factorial * i) ;
     }
     return factorial;
     //return factorial % n ;
  }

}
