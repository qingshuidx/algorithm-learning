package qing.linked;

import java.util.ArrayList;

/**
 * description: ReorderListSolution
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * @author yq
 * @date 2020/5/22.
 */
public class ReorderListSolution {


    public void reorderList(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // 链表转数组
        ArrayList<ListNode> arr = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            arr.add(p);
            p = p.next;
        }

        int size = arr.size();
        for (int i = 0; i < size - 1; i ++) {
            int j;
            if(i > (j = size - 1 - i)) {
                return;
            }
            ListNode q = arr.get(i);
            ListNode h = arr.get(j);
            if(q == h) {
                h.next = null;
                return;
            }
            ListNode tmp = q.next;
            if(tmp == h) {
                h.next = null;
                return;
            }
            q.next = h;
            h.next = tmp;

        }


    }

    // todo 目前没有想到其他解法

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
