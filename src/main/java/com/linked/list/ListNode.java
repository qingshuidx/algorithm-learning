package com.linked.list;

/**
 * description: ListNode
 * 单向链表
 * @author yq
 * @date 2020/4/28.
 */
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
