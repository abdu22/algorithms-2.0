package basics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class XTest {
    public static void main(String[] args) {
       String [] arr = new String [] {"1", "2", "15", "-7", "300"};
       Arrays.sort(arr);
       Arrays.stream(arr).forEach(a -> System.out.print(a + ", "));
       System.out.println();

        int sum = 0;
        int i = 10;
        while(i < 1) {
            sum += i;
            sum = sum * 2;
            i--;
        }

        System.out.println(sum);

        System.out.println(methodCheck(new int[] {4, 3, 5, 27, 10}));
    }

    public static boolean methodCheck(int[] arr) {
        int item = 0;
        for(int a:arr) {
            if( a % 2 == 1){
               item += 1;
            }else {
                item = 0;
            }
            if(item == 3) 
              return true;
        }
        return false;
    }
/*    Consumer<List<String>> deletEmpty = (items) -> {
    for(int i=0; i< items.size(); i++){
        if(items.get(i).length() == 0) {

        }
    }
  }  */ 
}
