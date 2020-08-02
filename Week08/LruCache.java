//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。 
//
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。 
//写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在
//写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
//
// 
//
// 进阶: 
//
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例: 
//
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得关键字 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得关键字 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
// 
// Related Topics 设计 
// 👍 785 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache {
  public static void main(String[] args) {
    LRUCache cache = new LruCache().new LRUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    System.out.println(cache.get(1));
    cache.put(3, 3);
    System.out.println(cache.get(2));
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class LRUCache {
    class LinkedNode {
      int key;
      int value;
      LinkedNode prev;
      LinkedNode next;

      public LinkedNode() {
      }

      public LinkedNode(int _key, int _value) {
        key = _key;
        value = _value;
      }
    }

    private Map<Integer, LinkedNode> cacheMap = new HashMap<Integer, LinkedNode>();
    private int size;
    private int capacity;

    private LinkedNode head;
    private LinkedNode tail;


    public LRUCache(int capacity) {
      this.size = 0;
      this.capacity = capacity;
      head = new LinkedNode();
      tail = new LinkedNode();
      head.next = tail;
      tail.next = head;
    }

    public int get(int key) {
      LinkedNode node = cacheMap.get(key);
      if (node == null) {
        return -1;
      }
      moveToHead(node);
      return node.value;
    }

    private void moveToHead(LinkedNode node) {
      removeNode(node);
      addToHead(node);
    }

    private void addToHead(LinkedNode node) {
      node.prev = head;
      node.next = head.next;
      head.next.prev = node;
      head.next = node;
    }

    private void removeNode(LinkedNode node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }

    public void put(int key, int value) {
      LinkedNode node = cacheMap.get(key);
      if (node == null) {
        LinkedNode newNode = new LinkedNode(key, value);
        cacheMap.put(key, newNode);
        addToHead(newNode);
        ++size;
        if (size > capacity) {
          LinkedNode tail = removeTail();
          cacheMap.remove(tail.key);
          --size;
        }
      } else {
        node.value = value;
        moveToHead(node);
      }
    }

    private LinkedNode removeTail() {
      LinkedNode res = tail.prev;
      removeNode(res);
      return res;
    }
  }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}