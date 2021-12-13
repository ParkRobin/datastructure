package datastructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashMap {

    public static void main(String[] args) {
        HashMap myHashMap = new HashMap();
        myHashMap.put(1, 1); // The map is now [[1,1]]
        myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
        System.out.println(myHashMap.get(1));    // return 1, The map is now [[1,1], [2,2]]
        System.out.println(myHashMap.get(3));    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
        myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
        System.out.println(myHashMap.get(2));    // return 1, The map is now [[1,1], [2,1]]
        myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
        System.out.println(myHashMap.get(2));    // return -1 (i.e., not found), The map is now [[1,1]]
    }

    final int BASE = 769;
    List[] buckets;

    public HashMap() {
        buckets = new List[BASE];
        for(int i=0; i<BASE; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Node> iterator = buckets[h].iterator();
        while(iterator.hasNext()){
            Node node = iterator.next();
            if(node.key == key){
                node.val = value;
                return;
            }
        }
        buckets[h].add(new Node(key, value));
    }

    public int get(int key) {
        int h = hash(key);
        Iterator<Node> iterator = buckets[h].iterator();
        while(iterator.hasNext()){
            Node node = iterator.next();
            if(node.key == key){
                return node.val;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Node> iterator = buckets[h].iterator();
        Node node = null;
        while(iterator.hasNext()){
            node = iterator.next();
            if(node.key == key){
                buckets[h].remove(node);
                return;
            }
        }
    }

    public int hash(int key){
        return key % BASE;
    }

    class Node{
        int key;
        int val;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
}
