package LRU_Cache;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by demshi on 12/8/15.
 */
public class LRUCache {
    class DoubleLinkedNode {
        int key;
        int value;
        DoubleLinkedNode prev;
        DoubleLinkedNode next;
    }

    DoubleLinkedNode head = null;
    DoubleLinkedNode tail = null;
    Map<Integer, DoubleLinkedNode> values = new HashMap<Integer, DoubleLinkedNode>();

    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (values.containsKey(key)) {
            touch(key);
            return values.get(key).value;
        } else
            return -1;
    }

    private void touch(int key) {
        DoubleLinkedNode node = values.get(key);
        if (node == head) return;

        DoubleLinkedNode next = node.next;
        if (null != next) {
            next.prev = node.prev;
        }
        DoubleLinkedNode prev = node.prev;
        if (null != prev) prev.next = next;
        if(node==tail){
            tail=node.prev;
        }
        node.prev = null;
        node.next = head;
        if(head!=null)
            head.prev = node;
        head = node;

    }

    public void set(int key, int value) {
        if (values.containsKey(key)) {
            values.get(key).value = value;
            touch(key);
        } else {
            DoubleLinkedNode node = new DoubleLinkedNode();
            node.key = key;
            node.value = value;
            node.next = head;
            values.put(key, node);

            if (head != null)
                head.prev = node;
            head = node;
            if (null == tail) {
                tail = node;
            }
            if (values.size() > capacity) {
                values.remove(tail.key);
                tail = tail.prev;
                tail.next = null;
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(10);

        cache.set(10, 13);
        cache.set(3, 17);
        cache.set(6, 11);
        cache.set(10, 5);
        cache.set(9, 10);
        cache.get(13);
        cache.set(2, 19);
        cache.get(2);
        cache.get(3);
        cache.set(5, 25);
        cache.get(8);
        cache.set(9, 22);
        cache.set(5, 5);
        cache.set(1, 30);
        cache.get(11);
        cache.set(9, 12);
        cache.get(7);
        cache.get(5);
        cache.get(8);
        cache.get(9);
        cache.set(4, 30);
        cache.set(9, 3);
        cache.get(9);
        cache.get(10);
        cache.get(10);
        cache.set(6, 14);
        cache.set(3, 1);
        cache.get(3);
        cache.set(10, 11);
        cache.get(8);
        cache.set(2, 14);
        cache.get(1);
        cache.get(5);
        cache.get(4);
        cache.set(11, 4);
        cache.set(12, 24);
        cache.set(5, 18);
        cache.get(13);
        cache.set(7, 23);
        cache.get(8);
        cache.get(12);
        cache.set(3, 27);
        cache.set(2, 12);
        cache.get(5);
        cache.set(2, 9);
        cache.set(13, 4);
        cache.set(8, 18);
        cache.set(1, 7);
        cache.get(6);
        cache.set(9, 29);
        cache.set(8, 21);
        cache.get(5);
        cache.set(6, 30);
        cache.set(1, 12);
        cache.get(10);
        cache.set(4, 15);
        cache.set(7, 22);
        cache.set(11, 26);
        cache.set(8, 17);
        cache.set(9, 29);
        cache.get(5);
        cache.set(3, 4);
        cache.set(11, 30);
        cache.get(12);
        cache.set(4, 29);
        cache.get(3);
        cache.get(9);
        cache.get(6);
        cache.set(3, 4);
        cache.get(1);
        cache.get(10);
        cache.set(3, 29);
        cache.set(10, 28);
        cache.set(1, 20);
        cache.set(11, 13);
        cache.get(3);
        cache.set(3, 12);
        cache.set(3, 8);
        cache.set(10, 9);
        cache.set(3, 26);
        cache.get(8);
        cache.get(7);
        cache.get(5);
        cache.set(13, 17);
        cache.set(2, 27);
        cache.set(11, 15);
        cache.get(12);
        cache.set(9, 19);
        cache.set(2, 15);
        cache.set(3, 16);
        cache.get(1);
        cache.set(12, 17);
        cache.set(9, 1);
        cache.set(6, 19);
        cache.get(4);
        cache.get(5);
        cache.get(5);
        cache.set(8, 1);
        cache.set(11, 7);
        cache.set(5, 2);
        cache.set(9, 28);
        cache.get(1);
        cache.set(2, 2);
        cache.set(7, 4);
        cache.set(4, 22);
        cache.set(7, 24);
        cache.set(9, 26);
        cache.set(13, 28);
        cache.set(11, 26);

    }
}
