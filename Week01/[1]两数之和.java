package leetcode.editor.cn;//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表
/**
 * 暴力求解：遍历每一个元素，然后使用目标值与当前值做差，拿着差去看数组中有没有，有的话就返回；
 * 哈希表：将数组值和下标作为键值放入哈希表中，只需要遍历一次数组元素，然后查看哈希表中是否存在对应的键就可以了（不看题解想不出来）
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution001 {
    public int[] twoSum(int[] nums, int target) {
        /*int tempInt = 0;
       for (int i = 0;i< nums.length;i++){
           tempInt =  target - nums[i];
           for (int j = i + 1; j < nums.length;j++){
               if (tempInt == nums[j]){
                   return new int[]{i,j};
               }
           }
       }
       return null;*/
        Map testMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (testMap.containsKey(target-nums[i])){
                return new int[]{i,(int)testMap.get(target-nums[i])};
            }
            testMap.put(nums[i],i);
        }
        return null;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
