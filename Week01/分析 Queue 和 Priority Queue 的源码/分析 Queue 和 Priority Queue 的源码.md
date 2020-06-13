# Queue和PriorityQueue源码分析

## 1.【Queue基本介绍】

Queue是一种被设计的用来处理之前保存数据的集合；除了基本的Collection操作之外，提供额外的插入、提取、检查操作，每一种方式提供两种形式，一种是当操作发生异常时，会抛出一个异常；一种是针对容量限制的队列的操作，返回特定的值，null或者false；当然了，在大多数情况下，插入元素是不会报错的。

通常排列元素是FIFO（first-in-first-out）先入先出的方式，优先队列（它会根据提供的comparator,或者元素的自然顺序）和LIFO队列（或者说stacks--last-in-first-out）后入先出队列是例外情况，无论采用什么样的排列方式，header元素能够被remove()或者poll()移除掉。在先进先出的队列中，所有的新元素都被插入在队列尾部。

offer()能够插入一个元素，否则的话返回一个false,和Collection中的add()不同的是，add()异常时返回的是异常，而offer()返回的是一个特殊的值。

remove()和poll()移除并且返回队列的header元素。确切来说，哪一个元素被删除是这个队列的排序策略决定的，具体的每一个实现类的实现方式都不一样。remove()和poll()只有当队列是空的时候才有差别，remove()抛出异常，而poll()返回null;

element()和peek()只是返回，但不会删除header元素。

在Queue中没有定义并发编程中的阻塞队列**（BlockingQueue）的方法；

Queue的实现类中不允许插入null元素，尽管一些实现类，比如linkedList并没有约束不能插入null;但即使在允许输入null的实现中，也不要将null插入到队列中；因为null也被用于作为poll()方法判断队列中有无元素的特殊返回值。

Queue的实现类通常不定义基于元素的equals()和hashCode(),因为对于元素相同但是排序属性不同的队列，基于元素的幂等性并非总是定义的比较好的。

## 2.【Queue继承关系】

<img src=" Queue继承关系.jpg" style="zoom:67%;" />

其实现类或接口：

<img src=" Queue部分实现关系.jpg" style="zoom:67%;" />

## 3.【Queue定义方法】

~~~java
// 添加成功，返回true.如果超过队列容量限制，则抛出IllegalStateException异常
boolean add(E e)
// 添加成功，返回true.不成功返回false;
boolean offer(E e);
// 检索并移除队列的header元素；如果队列为空，抛出NoSuchElementException异常；
E remove();
// 检索并移除队列的header元素；如果队列为空，返回null;
E poll();
// 检索并返回队列的header元素，如果队列为空，抛出NoSuchElementException异常；
E element();
// 检索并返回队列的header元素，如果队列为空，返回null
E peek();
~~~

## 4.【Queue实现分析】

### 4.1 PriorityQueue

#### 4.1.1 PriorityQueue简介

PriorityQueue是基于优先级堆的无限优先级队列；是根据自然顺序或者根据构造过程中使用的Comparator来定义元素的优先级；优先级队列不允许null值和不可比较的对象（会抛出异常）；

header元素按照指定的规则来看，通常是最小的元素；

优先级队列是无限制的，但是具有用于控制在队列上存储元素的数组大小的内部容量；该容量总是至少和队列一样大；当元素被加入之后，其容量会自动增加；

优先队列中的迭代以及实现都是基于Collection和Iterator接口，iterator()中提供的Iterator不保证以任何特定顺序遍历优先级队列的元素,如果需要考虑有序遍历，则优先使用Arrays.sort();

优先队列不是线程安全的；在多线程环境下应该使用PriorityBlockingQueue，而不是PriorityQueue；

内部方法的时间复杂度：offer(),poll(),remove(),add()----O(log(n));remove(Object)，contains(Object)---线性时间复杂度；peek(),element(),size()为常数级别的时间复杂度。



【查阅】

PriorityQueue在实现上是基于二叉小顶堆实现的；https://www.cnblogs.com/CarpenterLee/p/5488070.html

堆其实就是利用完全二叉树的结构来维护的一维数组；

按照堆的特点可以把堆分为**大顶堆**和**小顶堆**

大顶堆：每个结点的值都**大于**或**等于**其左右孩子结点的值

小顶堆：每个结点的值都**小于**或**等于**其左右孩子结点的值

https://www.cnblogs.com/lanhaicode/p/10546257.html

#### 4.1.2 PriorityQueue继承关系

<img src=" 优先队列继承关系.jpg" style="zoom:60%;" />

从继承关系上看，PriorityQueue并非是Queue的直接实现类，其父类为AbstractQueue;而在PriorityQueue的方法中并没有E element()，反而在AbstractQueue里存在element();此处应用到了【**适配器设计模式**】；

#### 4.1.3 PriorityQueue分析

~~~java
// 重要的成员变量
int DEFAULT_INITIAL_CAPACITY = 11; // 默认的队列元素个数
int size = 0; // PriorityQueue元素个数
int modCount = 0; // 修改队列的次数
int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
~~~

PriorityQueue表示为平衡二进制堆的优先级队列：queue [n]的两个子级是queue [2 * n + 1]和queue [2 *（n + 1）]。如果比较器为空，则按比较器或元素的自然排序对优先级队列进行排序：对于堆中的每个节点n和n的每个后代d，n <= d。假定队列为非空，则最小值的元素在queue [0]中；

**构造函数**

~~~java
// 构造方法
public PriorityQueue(); // 默认容量，无comparator
public PriorityQueue(int initialCapacity); // 指定初始化容量，无comparator
public PriorityQueue(Comparator<? super E> comparator); // 默认容量，指定comparator
// 构造函数里面会初始化指定容量或者默认容量，指定比较器或者自然顺序，并赋值给queue和comparator；如果容量<1,则抛出IllegalArgumentException异常；
public PriorityQueue(Collection<? extends E> c) ; // 构造含有集合元素(从另一个优先队列或sortedSet获取到comparator)的优先队列，优先转换为数组，获取到数组长度作为size取值，将queue赋值为数组；
public PriorityQueue(PriorityQueue<? extends E> c);
public PriorityQueue(SortedSet<? extends E> c);
~~~

**常用方法**

~~~java
// add(E e)调用offer(E e)
public boolean offer(E e) {
    if (e == null)
        throw new NullPointerException();
    modCount++;
    int i = size;
    if (i >= queue.length) 
        grow(i + 1);
    size = i + 1;
    if (i == 0)
        queue[0] = e;
    else
        siftUp(i, e);
    return true;
}
// 增加元素时，如果元素为null,则会抛出异常----优先队列新增元素不能为null
// 如果队列元素个数>=队列长度，则调用grow(i + 1),将i+1和64比较，如果小于64，则新的容量暂时为2i+4[即为两倍];否则，新的容量暂定为1.5(i+1)[即扩充一半]；再将新的容量与MAX_ARRAY_SIZE比较，如果比MAX_ARRAY_SIZE大，则比较i+1和MAX_ARRAY_SIZE，如果i+1大，则最终容量为Integer.MAX_VALUE，否则为MAX_ARRAY_SIZE；
// 如果此时队列中无元素，则将新元素放置在0位；
// 如果此时队列中有元素，则调用siftUp(i, e)：获取当前（队列个数-1）/2的坐标值；其对应的节点值即为非叶子节点，比较要插入的值和该节点值的大小关系，如果要插入的值小于该节点值，则将两节点值互换，并多次比较，直到满足小顶堆规则。
private void siftUpComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>) x;
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (key.compareTo((E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = key;
}
~~~

peek():如果队列为空，则返回null,否则返回header元素；

~~~java
public E peek() {
        return (size == 0) ? null : (E) queue[0];
}
~~~

remove(Object o)

~~~java
public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1) 
            return false;
        else {
            removeAt(i);
            return true;
        }
    }
// 判断要删除的元素是否在队列中，如果不在的话，则返回false.如果在的话，则调用removeAt(i):
// 在removeAt中，先判断要删除的元素是否是最后一个元素，如果是的话，则将最后元素置为null;
// 如果不是的话，先将最后一个位置置为null,将要删除的位置i和最后一个元素作为入参调用下列方法：
private void siftDownComparable(int k, E x) { // k为删除元素的下标，x为最后位置元素的值
    Comparable<? super E> key = (Comparable<? super E>)x;
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        if (key.compareTo((E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = key;
}
// 比较半个树上的元素，查看元素的左右子节点，比较最后元素和左右节点点的值大小关系，直到最后元素比左右节点值小，将要删除的元素位置替换为最后元素值；
// 以上操作可能会破坏小顶堆规则，则继续执行向上调整中的调整方法，使其满足小顶堆规则；
~~~

removeEq(Object o)

~~~java
// 此方法比较用的是==,而不是indexOf(o)中的equals,删除逻辑和remove(Object o)中的主要逻辑一致
boolean removeEq(Object o) {
    for (int i = 0; i < size; i++) {
        if (o == queue[i]) {
            removeAt(i); 
            return true;
        }
    }
    return false;
}
~~~

contains(Object o) 

```java
// 主要注意点还是在indexOf(o)中的equals.
public boolean contains(Object o) {
    return indexOf(o) != -1;
}
```

与数组之间的转换可以使用toArray()方法；

poll():删除header元素

~~~java
public E poll() {
    if (size == 0)
        return null;
    int s = --size;
    modCount++;
    E result = (E) queue[0];
    E x = (E) queue[s];
    queue[s] = null;
    if (s != 0)
        siftDown(0, x);
    return result;
}
// 先将header元素赋值给result.作为返回值；
// 取最后元素并将最后位置赋值为null,将最后元素的值作为的header元素向下比较调整，按照siftDown的处理逻辑调整使其符合小顶堆规则。
~~~

#### 4.1.4 PriorityQueue核心方法引用代码逻辑---堆化调整筛选

~~~java
private void siftUpComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>) x;
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (key.compareTo((E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = key;
}

private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        if (key.compareTo((E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = key;
}
~~~

offer图示：

![](offer.jpg)

poll()图示

![](poll.jpg)

remove(E e)图示

![](remove.jpg)

