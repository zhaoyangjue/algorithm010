package leetcode.editor.cn;//给定一个二叉树，返回它的中序 遍历。
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> returnList = new ArrayList<>();
        getElement(root,returnList);
        return returnList;
    }
    private void getElement(TreeNode root,List<Integer> returnList){
        if (root == null) return;
        getElement(root.left,returnList);
        returnList.add(root.val);
        getElement(root.right,returnList);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
