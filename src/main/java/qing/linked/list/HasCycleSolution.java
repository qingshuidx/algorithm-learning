package qing.linked.list;

import java.util.HashSet;

/**
 * description: HasCycleSolution
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 3 -> 2 -> 0 -> -4
 *      ^          |
 *      |__________|
 * @author yq
 * @date 2020/5/12.
 */
public class HasCycleSolution {


    static boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }

        HashSet<ListNode> nodeHashSet = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if(nodeHashSet.contains(p)) {
                return true;
            }
            nodeHashSet.add(p);

            p = p.next;
        }
        return false;
    }

    static boolean hasCycle2(ListNode head) {

        if (head == null) {
            return false;
        }

        ListNode sp = head;
        ListNode fp = head.next;
        while (sp != null && fp != null) {
            if (sp == fp) {
                return true;
            }

            sp = sp.next;
            if(fp.next == null) {
                return false;
            }
            fp = fp.next.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = head;
//        head.next.next = new ListNode(0);
//        head.next.next.next = head.next;
        System.out.println(hasCycle2(head));
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
