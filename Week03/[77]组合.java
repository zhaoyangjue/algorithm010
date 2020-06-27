package leetcode.editor.cn;//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution077 {
    public List<List<Integer>> combine(int n, int k) {
        // 去除特殊边界
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k){
            return res;
        }
        process(n,k,1,new ArrayList<Integer>(),res);
        return res;
    }

    private void process(int n, int k, int i, ArrayList<Integer> objects,List<List<Integer>> res) {
        if (objects .size() == k){
            res.add(new ArrayList<>(objects));
            return ;
        }
        for (int j = i; j <= n-(k-objects.size())+1; j++) {
            objects.add(j);
            process(n,k,j+1,objects,res);
            objects.remove(objects.size()-1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
