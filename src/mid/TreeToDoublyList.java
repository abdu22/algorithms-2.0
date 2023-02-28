package mid;


public class TreeToDoublyList {
    /* 
    [4,2,5,1,3]
           
           [4]
       [2]    [5]  
     [1] [3]
     */
    public static void main(String[] args) {
            String s = "" ;
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeToDoublyList o = new TreeToDoublyList();

        TreeNode ans = o.treeToDoublyList(root);
        

        System.out.println();

    
    }

     public TreeNode treeToDoublyList(TreeNode root) {
        TreeNode [] endToEnd =  helpTraveseTree(root);
         return endToEnd[0];
      }
  
      public TreeNode [] helpTraveseTree(TreeNode root){
  
        if(root == null) return null;
  
        TreeNode [] current = new TreeNode []{root, root};
  
        TreeNode [] left = helpTraveseTree(root.left);
        TreeNode [] right = helpTraveseTree(root.right);
        
        if(left != null){
            root.left = left[1];
            left[1].right = root;
            current[0] = left[0];
        }
        if(right != null){
            root.right = right[0];
            right[0].left = root;
            current[1] = right[1];
        }
  
         return current;   
      }
}

class TreeTreeNode {
     int val;
     TreeTreeNode left;
     TreeTreeNode right;

    public TreeTreeNode() {}

    public TreeTreeNode(int val) {
        this.val = val;
    }

    public TreeTreeNode(int val, TreeTreeNode left,TreeTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
};
