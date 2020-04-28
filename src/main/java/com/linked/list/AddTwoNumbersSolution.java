package com.linked.list;

/**
 * description: AddTwoNumbersSolution
 *
 * @author yq
 * @date 2020/4/28.
 */
public class AddTwoNumbersSolution {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(0);
        ListNode reNext = result;
        boolean isCarry = false;
        ListNode zeroNode = new ListNode(0);

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

        while (l1 != null || l2 != null || isCarry) {
            if (l1 == null) {
                l1 = zeroNode;
            }

            if (l2 == null) {
                l2 = zeroNode;
            }
            int val = l1.val + l2.val + (isCarry ? 1 : 0);
            isCarry = false;
            if(val > 9) {
                isCarry = true;
                val = val - 10;
            }
            reNext.val = val;

            l1 = l1.next;
            l2 = l2.next;

            if(l1 == null && l2 == null && !isCarry) {
                break;
            }
            reNext.next = new ListNode(0);
            reNext = reNext.next;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(6);
        l1.next = new ListNode(4);
        ListNode l1next1 = l1.next;
        l1next1.next = new ListNode(5);
//        ListNode l1next2 = l1next1.next;
//        l1next2.next = new ListNode(9);
//        l1next2.next.next = new ListNode(7);
//        l1next2.next.next.next = new ListNode(8);


        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(8);
        l2.next.next = new ListNode(7);
//        ListNode l2next = l2;
//        l2next.val = 9;
//        System.out.println(l2.val);
        ListNode sum = addTwoNumbers(l1, l2);

        System.out.println(sum.val);
    }
}
