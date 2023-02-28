
package mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Pair {
    TreeNode node;
    int count;
    Pair(TreeNode node, int count){
        this.node = node;
        this.count = count;
    }
}

/* 
 
          0
     2       1
           3
     
 */

public class AllNodesDistanceKTree {
    public static void main(String [] args){

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(2);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(3);

        List<Integer> ans = distanceK(root, root.right.left, 3);

    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Set<TreeNode> memory = new HashSet<>();

        List<Integer> ans = new ArrayList<>();
        
        //get all parent
        traverseTree(root, parentMap);

        // do BFS traversl with paths left, right, parent 
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(target, 0));
        
        while(q.size() > 0) {
            int size = q.size();
            while(size > 0) {
                size --; 
                Pair current = q.poll();
                if(memory.contains(current.node)){
                    break;
                }
                memory.add(current.node);
                if(current.count == k){
                    ans.add(current.node.val);
                    break;
                }
                //add parent
                TreeNode currentPar = parentMap.get(current.node);
                if(currentPar != null) q.add(new Pair(currentPar, current.count + 1));
                //add left
                if(current.node.left != null) q.add(new Pair(current.node.left, current.count + 1));
                //add right
                if(current.node.right != null) q.add(new Pair(current.node.right, current.count + 1)); 
            }
            

        }

        return ans;
    }

    public static void traverseTree(TreeNode root, Map<TreeNode, TreeNode> parentMap){
        if(root == null){
            return;
        }
        if(root.left != null){
            parentMap.put(root.left, root);
            traverseTree(root.left, parentMap);
        }
        if(root.right != null) {
            parentMap.put(root.right, root);
            traverseTree(root.right, parentMap);
        }
    }
}
