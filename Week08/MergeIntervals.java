//给出一个区间的集合，请合并所有重叠的区间。 
//
// 示例 1: 
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
// Related Topics 排序 数组 
// 👍 530 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

public class MergeIntervals {
  public static void main(String[] args) {
    int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    Solution solution = new MergeIntervals().new Solution();
    int[][] merge = solution.merge(intervals);
    for (int[] singleMerge : merge) {
      System.out.println(singleMerge[0] + " " + singleMerge[1]);
    }
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[][] merge(int[][] intervals) {
      Arrays.sort(intervals, (n1, n2) -> n1[0] - n2[0]);
      int[][] res = new int[intervals.length][2];
      int idx = -1;
      for (int[] interval : intervals) {
        if (idx == -1 || interval[0] > res[idx][1]) {
          res[++idx] = interval;
        } else {
          res[idx][1] = Math.max(res[idx][1], interval[1]);
        }
      }
      return Arrays.copyOf(res, idx + 1);
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}