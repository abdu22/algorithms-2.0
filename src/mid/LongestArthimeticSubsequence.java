package mid;

public class LongestArthimeticSubsequence {
    /* 
                i j
    [20,1,15,3,10,5,8] 

    [
     [0, 1, 0, 0, 0, 0, 0]
     [0, 0, 0, 0, 0, 0, 0]
     [0, 0, 0, 0, 0, 0, 0]
     [0, 0, 0, 0, 0, 0, 0]
    ]



      
     */
    public static void main(String [] args) {
       //int [] nums = new int [] {20,1,15,3,10,5,8};
        int [] nums = new int [] {9,4,7,2,10};
        LongestArthimeticSubsequence obj = new LongestArthimeticSubsequence();
        System.out.println(obj.longestArithSeqLength(nums));
    }

    int maxLength = 0;

    public  int longestArithSeqLength(int[] nums) {
        int [][] m = new int[nums.length][nums.length];

        for(int i=0; i<nums.length; i++) {
            for (int j=i+1; j < nums.length; j++){
               int length = 2 + helpExplore(nums, i, j, m);
               maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }
    public  int helpExplore(int[] nums, int i, int j, int[][] m ){
        if(m[i][j] > 0) {
            return m[i][j];
        }
        int gap = nums[j] - nums[i];
        for(int a=j+1; a<nums.length; a++){
            if(nums[a] - nums[j] == gap){
              int length = 1 + helpExplore(nums, j, a, m);
              m[i][j] = length;
              return length;
            }
        }
        return 0;
    } 
}
