package datastructure;

/**
 * Deque:
 * pushBack: O(1)
 * pushFront: O(1)
 * popBack: O(1)
 * popFront: O(1)
 * getByIndex: O(1)
 */

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class Deque {
    Node first;
    Node last;
    Map<Integer, Integer> indexToValue;

    public Deque(){
        indexToValue = new HashMap<>();
    }

    class Node{
        int index;
        Node pre;
        Node next;

        public Node(int index){
            this.index = index;
        }
    }

    public void pushBack(int x){
        if(last == null){
            last = new Node(0);
            first = last;
            indexToValue.put(0, x);
        }else{
            Node target = new Node(last.index + 1);
            target.pre = last;
            last = target;
            indexToValue.put(last.index, x);
        }
    }

    public void pushFront(int x){
        if(first == null){
            first = new Node(0);
            last = first;
            indexToValue.put(0, x);
        }else{
            Node target = new Node(first.index - 1);
            target.next = first;
            first = target;
            indexToValue.put(first.index, x);
        }
    }

    public int getByIndex(int offset){
        int index = first.index + offset;
        return index <= last.index ? indexToValue.get(index) : -1;
    }

    public int popBack(){
        if(last == null){
            return -1;
        }
        int ret = indexToValue.get(last.index);
        if(last == first){
            last = null;
            first = null;
        }else{
            Node pre = last.pre;
            pre.next = null;
            last.pre = null;
            last = pre;
        }
        return ret;
    }

    public int popFront(){
        if(first == null){
            return -1;
        }
        int ret = indexToValue.get(first.index);
        if(first == last){
            first = null;
            last = null;
        }else{
            Node next = first.next;
            next.pre = null;
            first.next = null;
            first = next;
        }
        return ret;
    }

    public static void main(String[] args) {
        Deque deque = new Deque();
        //[1]
        deque.pushBack(1);
        Assertions.assertEquals(1, deque.getByIndex(0));
        Assertions.assertEquals(-1, deque.getByIndex(2));
        //[2,1]
        deque.pushFront(2);
        Assertions.assertEquals(2, deque.getByIndex(0));
        Assertions.assertEquals(1, deque.getByIndex(1));
        //[2,1,3]
        deque.pushBack(3);
        Assertions.assertEquals(2, deque.getByIndex(0));
        Assertions.assertEquals(1, deque.getByIndex(1));
        Assertions.assertEquals(3, deque.getByIndex(2));
        //[2,1]
        deque.popBack();
        Assertions.assertEquals(-1, deque.getByIndex(2));
        //[1]
        deque.popFront();
        Assertions.assertEquals(1, deque.getByIndex(0));
    }
}
