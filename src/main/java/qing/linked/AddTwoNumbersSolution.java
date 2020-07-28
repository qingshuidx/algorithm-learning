package qing.linked;

/**
 * description: AddTwoNumbersSolution
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yq
 * @date 2020/4/28.
 */
public class AddTwoNumbersSolution {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(0);
        ListNode reNext = result;
        boolean isCarry = false;

        // 全部个位数加
        if(l1.next == null && l2.next == null) {
            int val = l1.val + l2.val;
            if(val > 9) {
                val = val - 10;
                result.next = new ListNode(1);
            }
            result.val = val;
            return result;
        }

        while (true) {
            int x = (l1 == null) ? 0 : l1.val;
            int y = (l2 == null) ? 0 : l2.val;
            int sum = x + y + (isCarry ? 1 : 0);
            isCarry = false;
            if(sum > 9) {
                isCarry = true;
                sum = sum - 10;
            }
            reNext.val = sum;

            if(l1 != null ) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }

            if(l1 == null && l2 == null && !isCarry) {
                break;
            }
            reNext.next = new ListNode(0);
            reNext = reNext.next;
        }

        return result;
    }



    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(4);
//        ListNode l1next1 = l1.next;
//        l1next1.next = new ListNode(5);
//        ListNode l1next2 = l1next1.next;
//        l1next2.next = new ListNode(9);
//        l1next2.next.next = new ListNode(7);
//        l1next2.next.next.next = new ListNode(8);


        ListNode l2 = new ListNode(9);
//        l2.next = new ListNode(9);
//        l2.next.next = new ListNode(7);
//        l2.next.next.next = new ListNode(7);
//        l2.next.next.next.next = new ListNode(7);
//        l2.next.next.next.next.next = new ListNode(7);
        ListNode sum = addTwoNumbers(l1, l2);

        System.out.println(sum.val);
    }


     static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
  }
}
