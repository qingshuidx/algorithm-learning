package qing.linked;

import java.util.HashSet;

/**
 * description: DetectCycleSolution
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 3 -> 2 -> 0 -> -4
 *      ^          |
 *      |__________|
 * @author yq
 * @date 2020/5/22.
 */
public class DetectCycleSolution {


    public static ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        HashSet<ListNode> nodeHashSet = new HashSet<>();

        ListNode p = head;
        while ( p != null) {
            if (nodeHashSet.contains(p)) {
                return p;
            }
            nodeHashSet.add(p);
            p = p.next;
        }

        return null;
    }

    public static ListNode detectCycle2(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }

        ListNode f = head;
        ListNode s = head;
        ListNode m;

        while (f.next != null) {
            f = f.next.next;
            s = s.next;

            // 相遇
            if(f == s) {
                m = s;
                while (true) {
                    if(head == m) {
                        return head;
                    }
                    head = head.next;
                    m = m.next;
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(3);
//        head.next = head;
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next;
        System.out.println(detectCycle2(head).val);
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
