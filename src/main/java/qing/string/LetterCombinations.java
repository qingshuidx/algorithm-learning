package qing.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * description: LetterCombinations
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * @author yq
 * @date 2020/9/18.
 */
public class LetterCombinations {

    private final static String[] keyboards = new String[]{
            "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"};


    /**
     * 回溯法
     * @param digits
     * @return
     */
    public static List<String> solution(String digits) {
        List<String> result = new LinkedList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }

        iterStr(digits, 0, new StringBuilder(), result);

        return result;
    }

    private static void iterStr(String digits, int index, StringBuilder sb, List<String> result) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }

        String keyboard = keyboards[digits.charAt(index)-'2'];
        int len = keyboard.length();
        for (int i = 0; i < len; i++) {
            sb.append(keyboard.charAt(i));
            iterStr(digits, index+1, sb, result);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    /**
     * 使用队列
     * 全排序
     * @param digits
     * @return
     */
    public static List<String> solution1(String digits) {
        List<String> queue = new LinkedList<>();
        if (digits == null || digits.isEmpty()) {
            return queue;
        }
        int len = digits.length();
        // 添加一个空字符
            queue.add("");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < len; i++) {
                String keyboard = keyboards[digits.charAt(i)-'2'];
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    String temp = queue.remove(0);
                    for (int k = 0; k < keyboard.length(); k++) {
                        queue.add(builder.append(temp).append(keyboard.charAt(k)).toString());
                        builder.delete( 0, builder.length());
                    }
                }
        }

        return queue;
    }

    /**
     * 求余法
     * @param digits
     * @return
     */
    public static List<String> solution2(String digits) {
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0){
            return result;
        }
        //1.先算出一共有几种
        int len = 1;
        for(int i = 0; i < digits.length(); i++){
            int c = digits.charAt(i)-'2';
            len *= keyboards[c].length();
        }

        //再用求余方法拿到每一种
        for(int i = 0 ; i < len; i++){
            int last = i;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < digits.length(); j++){
                int c = digits.charAt(j)-'2';
                int pos = last % keyboards[c].length();
                sb.append(keyboards[c].charAt(pos));
                last = last / keyboards[c].length();
            }
            result.add(sb.toString());
        }


        return result;
    }

    public static void main(String[] args) {
        String digits = "234";
        System.out.println(solution(digits));
        System.out.println(solution(digits).size());
    }
}
