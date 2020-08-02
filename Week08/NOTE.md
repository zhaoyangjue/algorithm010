学习笔记

## 位运算

* 位运算符：左移（<<）,右移（>>）,按位或（|）,按位与（&），按位取反（~）.按位异或（^）【异或：相同为0.不同为1】；

~~~html
x^0 = x;
x^1(全1) = ~x;
x^(~x)=1(全1)；
    x^x=0
    c = a^b   -----> a^c=b,b^c=a;
a^b^c=a^(b^c)=(a^b)^c;
指定位置的位运算：
    1.将x最右边的n位清零:x&(~0<<n);
	2.获取x的第n位值（0或1）：(x>>n)&1;
	3.获取x的第n位的幂值：x&(1<<n)；
    4.仅将第n位置为1：x|(1<<n);
	5.仅将第n位置为0：x&(~(1<<n));
	6.将x最高位至第n位（含）清零：x&(1<<n)
判断奇偶：
    x%2==1  ---->  (x&1)==1
    x%2==0  ---->  (x&1)==0
    x>>1 --->x/2
清零最低位的1：x=x&(x-1);
得到最低位的1：x&-x
    x&~x=0 
~~~

* 布隆过滤器:用于检索一个元素是否在一个集合中;
  * 优点：空间效率和查询时间都远超过一般的算法
  * 缺点：存在一定的误识别率和删除困难。元素如果不在布隆过滤器中，则肯定不在，如果在的话，这是可能存在。
* LRU Cache：最近最少淘汰，结合redis的淘汰策略理解

缓存的两个要素：大小和替换策略；查询、修改、更新O(1)---->哈希表+双向列表；

也可以使用java中的LinkedHashMap类进行操作,需要熟悉LinkedHashMap的API操作

~~~java
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
	// add和remove操作要注意指针的指向变动
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
}
~~~

* 排序

  * 比较类排序：通过比较元素间的相对次序，由于时间复杂度不能突破O(nlogn),因此也称为非线性时间比较类排序。
  * 非比较类排序：不通过比较元素间的相对次序，可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序。限制就是只能针对整形数据进行排序。

* 初级排序：
  		1.选择排序：每次找最小值，然后放到待排序数组的起始位置。

  ~~~java
  public class ChooseSort {
    class Solution {
      public int[] sort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
          int k = i;
          for (int j = k + 1; j < length; j++) {
            if (nums[j] < nums[k]) {
              k = j;
            }
          }
          if (i != k) {
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
          }
        }
        return nums;
      }
    }
  
    public static void main(String[] args) {
      int[] nums = {1, 4, 5, 8, 23, 43, 32, 17};
      Solution solution = new ChooseSort().new Solution();
      int[] num = solution.sort(nums);
      for (int i : num) {
        System.out.print(i);
        System.out.print(" ");
      }
    }
  }
  ~~~

  ​		2.插入排序：从前到后逐步构建有序序列；对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。

  ~~~java
  public class InsertSort {
    class Solution {
      public int[] sort(int[] nums) {
        int j;
        int t;
        int length = nums.length;
        for (int i = 1; i < length; i++) {
          if (nums[i] < nums[i - 1]) {
            t = nums[i];
            for (j = i - 1; j >= 0 && nums[j] > t; j--) {
              nums[j + 1] = nums[j];
            }
            nums[j + 1] = t;
          }
        }
        return nums;
      }
    }
  
    public static void main(String[] args) {
      int[] nums = {1, 4, 5, 8, 23, 43, 32, 17};
      Solution solution = new InsertSort().new Solution();
      int[] num = solution.sort(nums);
      for (int i : num) {
        System.out.print(i);
        System.out.print(" ");
      }
    }
  }
  ~~~

  ​		3.冒泡排序：嵌套循环，每次查看相邻的元素，如果逆序，则交换。

  ~~~java
  public class ChooseMaxSort {
    class Solution {
      public int[] sort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
          for (int j = i + 1; j < length; j++) {
            if (nums[i] > nums[j]) {
              int temp = nums[i];
              nums[i] = nums[j];
              nums[j] = temp;
            }
          }
        }
        return nums;
      }
    }
  
    public static void main(String[] args) {
      int[] nums = {1, 4, 5, 8, 23, 43, 32, 17};
      Solution solution = new ChooseMaxSort().new Solution();
      int[] num = solution.sort(nums);
      for (int i : num) {
        System.out.print(i);
        System.out.print(" ");
      }
    }
  }
  ~~~

* 高级排序：
  * 快速排序：数组取标杆pivot,将小元素放pivot左边，大元素放右边，然后依次对右边及右边的子数组继续快排，以达到整个序列有序。
  * 归并排序：将长度为n的输入序列分成两个长度为n/2的子序列；对这两个子序列分别采用归并排序；将两个排序好的子序列合并成一个最终的排序序列；体现了分治的思想。
  * 堆排序：数组元素依次建立小顶堆；依次取堆元素，并删除。插入O(logN)；取最大/小值O(1);



