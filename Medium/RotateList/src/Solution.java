/**
 * Created by pratikku on 14/8/15.
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k){
        int length=0;
        if(head == null)
            return null;
        ListNode curr = head;
        ListNode tail = head;
        while(curr != null){
            tail = curr;
            ++length;
            curr = curr.next;
        }
        int rotate = k % length;
        if(rotate == 0)
            return head;
        curr = head;
        for(int i=2; i<= (length - rotate); ++i){
            curr = curr.next;
        }

        tail.next = head;
        head = curr.next;
        curr.next = null;
        return head;
    }

    public void print(ListNode head){
        ListNode curr = head;
        while(curr != null){
            System.out.print(curr.val+"->");
            curr = curr.next;
        }
        System.out.format("null%n");
    }

    public static void main(String[] args){
        ListNode n = new ListNode(1);
        ListNode curr = n;
        for(int i=2; i<=5; ++i){
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        Solution s = new Solution();
        s.print(n);
        n = s.rotateRight(n,102);
        s.print(n);
    }
}
