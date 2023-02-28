package mid;

public class MaxSwap {
/*
        2 7 3 6 
        
        l = 2
        r = 7  

        9 9 6 7 3
        l 
       dplicate for deep  115 
       duplicate for peek 1993

       1 0 9 0 9 0 9 1

       1 0 0 0 5 9 0 8
    */
 public static void main(String[] args) {
    MaxSwap o = new MaxSwap();
    System.out.println(o.maximumSwap2(2736) + "," + o.maximumSwap(2736));
    System.out.println(o.maximumSwap2(99673) + "," +o.maximumSwap(99673));
    System.out.println(o.maximumSwap2(115) + "," +o.maximumSwap(115));
    System.out.println(o.maximumSwap2(1993) + "," +o.maximumSwap(1993));
    System.out.println(o.maximumSwap2(10909091) + "," +o.maximumSwap(10909091));
    System.out.println(o.maximumSwap2(10005908) + "," +o.maximumSwap(10005908));
 }   

 public int maximumSwap2(int num) {
    String strNum = "" + num;
    if(strNum.length() < 2) return num;

    char [] charArr = strNum.toCharArray();
    
    int l = 1; int r = 1;
    int deepIdx = -1;
    int peekIdx = -1;

    while(l <charArr.length){
        // find first V
        if(charArr[l] > charArr[l-1]){
            deepIdx = l-1;
            // look the peek till end right
            r = l;
            peekIdx = r;
            while(r < charArr.length){
                if(charArr[r] >= charArr[peekIdx])
                peekIdx =r;
                r++;
            }
            // find position to the end left to poot peek.
            l = deepIdx;
            while(l > 0){
                if(charArr[l-1] < charArr[peekIdx]) 
                     deepIdx = l - 1;
                l--;     
            }
            break;
        }
        l++;
    }


    if(deepIdx == -1 || peekIdx == -1) return num;

    char deepValue = charArr[deepIdx];
    char peekValue = charArr[peekIdx];

    charArr[deepIdx] = peekValue;
    charArr[peekIdx] = deepValue;
     
    String ans = "";
    for(int i=0; i<charArr.length; i++){
        ans +=charArr[i];
    }

    return Integer.parseInt(ans);
}

public int maximumSwap(int num) {
    // find first deep from left
    // find the max value to the right of the deep
    
    String strNum = "" + num;
    if(strNum.length() < 2) return num;

    char [] charArr = strNum.toCharArray();
    
    int l = 1; int r = 1;
    int deep = -1;
    int peek = -1;

    while(l < charArr.length){
        if(charArr[l] > charArr[l-1]){
            // we go the first deep @ l-1
            deep = l-1;
            //check if duplicate, move the deep as left as possible , 1115
            while(deep > 0){
                if(charArr[deep] == charArr[deep-1])
                    deep = deep - 1;
                else 
                  break;    
            }
            // look the peek at r
            r = l;
            peek = r;
            while(r < charArr.length){
                if(charArr[r] >= charArr[peek])
                    peek =r;
                r++;
            }
            break;
        }
        l++;
    }

    if(deep == -1 || peek == -1) return num;

    char deepValue = charArr[deep];
    char peekValue = charArr[peek];

    charArr[deep] = peekValue;
    charArr[peek] = deepValue;
     
    String ans = "";
    for(int i=0; i<charArr.length; i++){
        ans +=charArr[i];
    }

    return Integer.parseInt(ans);
}
    
}
