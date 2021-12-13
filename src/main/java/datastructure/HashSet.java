package datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashSet {

    public static void main(String[] args) {
        HashSet myHashSet = new HashSet();
        myHashSet.add(1);
        myHashSet.add(2);
        //{1,2}
        System.out.println(myHashSet.contains(1));
        System.out.println(myHashSet.contains(3));
        myHashSet.add(2);
        System.out.println(myHashSet.contains(2));
        myHashSet.remove(2);
        System.out.println(myHashSet.contains(2));
    }

    final int BASE = 769;
    List[] buckets;

    public HashSet() {
        buckets = new List[BASE];
        for(int i=0; i<BASE; i++){
            buckets[i] = new ArrayList<>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = buckets[h].iterator();
        while(iterator.hasNext()){
            if(key == iterator.next()){
                return;
            }
        }
        buckets[h].add(key);
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = buckets[h].iterator();
        while(iterator.hasNext()){
            if(key == iterator.next()){
                buckets[h].remove(new Integer(key));
                return;
            }
        }
    }

    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = buckets[h].iterator();
        while(iterator.hasNext()){
            if(key == iterator.next()){
                return true;
            }
        }
        return false;
    }

    public int hash(int key){
        return key % BASE;
    }
}
