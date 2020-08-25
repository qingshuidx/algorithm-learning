package qing.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * description: LengthOfLongestSubstring
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @author yq
 * @date 2020/8/25.
 */
public class LengthOfLongestSubstring {

    public static int solution(String s) {
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        for(int i = 0; i < len; i++) {
            if(map.containsKey(s.charAt(i))) {
                maxLen = Math.max(map.size(), maxLen);
                i = map.get(s.charAt(i));
                map.clear();
                continue;
            }
            map.put(s.charAt(i), i);
        }
        return Math.max(maxLen, map.size());
    }

    /**
     * solution优化
     * @return
     */
    public static int solution2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 网上优化方案
     * 滑动窗口
     * @return
     */
    public static int solution3(String s) {
        char[] arr = s.toCharArray();
        int size, i = 0, j, k, max = 0;
        size = s.length();
        for(j = 0; j < size; j++){
            for(k = i; k < j; k++){
                if(arr[k]==arr[j]){
                    i = k+1;
                    break;
                }
            }
            if(j-i+1 > max) {
                max = j-i+1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "pwwkewq";
        System.out.println(solution(s));
    }
}
