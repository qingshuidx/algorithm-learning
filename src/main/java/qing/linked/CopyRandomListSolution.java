package qing.linked;

import java.util.HashMap;

/**
 * description: CopyRandomListSolution
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 *
 * 要求返回这个链表的 深拷贝。 
 *
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yq
 * @date 2020/5/7.
 */
public class CopyRandomListSolution {

    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }
        HashMap<Node, Node> nodeHashMap = new HashMap<>();
        Node p = head;
        while (p != null) {
            Node copyHead = new Node(p.val);
            nodeHashMap.put(p, copyHead);
            p = p.next;
        }

        p = head;
        while (p != null) {
            Node copyHead = nodeHashMap.get(p);
            copyHead.next = nodeHashMap.get(p.next);
            copyHead.random = nodeHashMap.get(p.random);
            p = p.next;
        }
        return nodeHashMap.get(head);
    }

    public Node copyRandomList2(Node head) {
        if(head == null) {
            return null;
        }


        // 生成新链表
        Node p = head;
        while (p != null) {
            Node tmp = new Node(p.val);
            tmp.next = p.next;
            p.next = tmp;
            p = p.next.next;
        }

        //复制随机指针
        p = head;
        while (p != null) {
            p.next.random = (p.random == null ? null : p.random.next);
            p = p.next.next;
        }

        p = head;
        Node cpHead = head.next;
        Node cp = cpHead;
        while (p != null) {
            p.next = p.next.next;
            p = p.next;

            if(cp.next == null) {
                break;
            }
            cp.next = cp.next.next;
            cp =  cp.next;
        }

        return cpHead;
    }
    

    public static void main(String[] args) {
        // [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);
        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;

        CopyRandomListSolution copyRandomListSolution = new CopyRandomListSolution();
        Node copy = copyRandomListSolution.copyRandomList2(head);

        print(head);
        System.out.println("----------------------");
        print(copy);
    }

    static void print(Node h){
        Node head = h;
        while (head != null) {
            System.out.println( "["+ head.val + "," + (head.next == null ? " " : head.next.val) + "," + (head.random == null ? "null" : head.random.val) + "]" );
            head = head.next;
        }
    }


    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
