package leetcode.editor.cn;
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution0015 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> returnList = new ArrayList<>();
        if (nums ==null || (nums !=null && nums.length < 3)){
            return returnList;
        }
        int len = nums.length;
        Arrays.sort(nums);// [-4,-1,-1, 0, 1, 2]
        for (int i = 0;i < nums.length;i++){
            if (nums[i] > 0){
                break;
            }
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int k = len-1,j=i+1;
            while(j < k){
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0){
                        returnList.add(Arrays.asList(nums[i],nums[j],nums[k]));
                        while(j < k && nums[j] == nums[j++]){
                            j++;
                        }
                        while(j < k && nums[k] == nums[k++]){
                            k--;
                        }
                        j++;
                        k--;
                    }else if (sum < 0){
                        j++;
                    }else if (sum > 0){
                        k--;
                    }
            }
        }
        return returnList;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
