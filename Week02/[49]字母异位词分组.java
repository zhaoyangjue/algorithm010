package leetcode.editor.cn;
//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串

//1.暴力求解；
import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution049 {
    /*private boolean isAnagrams(String a,String b){
        if (a == null || b ==null){
            return false;
        }
        if (a.length() != b.length()){
            return false;
        }
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        Arrays.sort(aChar);
        Arrays.sort(bChar);
        return Arrays.equals(aChar,bChar);
    }*/
    public List<List<String>> groupAnagrams(String[] strs) {
        /*
        // 1.暴力求解
        boolean flag = false;
        List singleList = null;
        List returnList = new ArrayList<List<String>>();
        if (strs.length > 1){
            for (int i = 0;i < strs.length;i++){
                singleList = new ArrayList<String>();
                for (int j = strs.length-1; j > 0; j--) {
                    if (i < j && isAnagrams(strs[i],strs[j])){
                        if (strs[j] != null){
                            singleList.add(strs[j]!=""?strs[j]:"\"\"");
                            strs[j] = null;
                            flag = true;
                        }
                    }
                }
                if (flag){
                    if (strs[i] != null){
                        singleList.add(strs[i]!=""?strs[i]:"\"\"");
                        returnList.add(singleList);
                    }
                }else{
                    singleList.add(strs[i]!=""?strs[i]:"\"\"");
                    returnList.add(singleList);
                }
            }
        }else{
            singleList = new ArrayList();
            singleList.add(strs[0]!=""?strs[0]:"\"\"");
            returnList.add(singleList);
        }
        return returnList;*/
        // 2.HashMap
        Map<String,List<String>> map = new HashMap<>();
        for (String str:strs){
            char[] charStr = str.toCharArray();
            Arrays.sort(charStr);
            String find = new String(charStr);
            if (map.containsKey(find)){
                List<String> arr = map.get(find);
                arr.add(str);
                map.put(find,arr);
            }else{
                List<String> arr = new ArrayList<>();
                arr.add(str);
                map.put(find,arr);
            }
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] testStrs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Solution049 solution049 = new Solution049();
        List<List<String>> lists = solution049.groupAnagrams(testStrs);
        System.out.println(lists);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
