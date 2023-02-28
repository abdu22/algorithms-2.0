package hard;

/* 
   [ -10 , 9 , 20 , null, null , 15 , 7 ]
   
          -10
       9       20
            15    7     
  
     ans ; 42 -> 15 + 20 + 7
 */
public class MaxPathSumTree {
    public static void main(String[] args) {
        TreeNode r = new TreeNode(-10);
        r.left = new TreeNode(9);
        r.right = new TreeNode(20);
        r.right.left = new TreeNode(15);
        r.right.right = new TreeNode(7);

        MaxPathSumTree obj = new MaxPathSumTree();

        int ans = obj.maxPathSum(r);
        System.out.println(ans);
        
    }
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helpFindPathSum(root);
        return maxSum;
      }
     
    public int helpFindPathSum(TreeNode root){
        if(root == null) return 0;

        int left = helpFindPathSum(root.left);
        int right = helpFindPathSum(root.right);

        int all = left + right + root.val;
        int currentLeft = left  + root.val;
        int currentRight = right + root.val;
         // return path, current or current + left or current + right
        int returnPath = Math.max(root.val,Math.max(currentLeft, currentRight));

        //check if we find the maxSum
        int isMaxSum = Math.max(all, returnPath);
        if(maxSum < isMaxSum)
               maxSum = isMaxSum;


        return returnPath;
    }  
    
}
