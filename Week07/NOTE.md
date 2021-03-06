学习笔记

## Trie树的基本实现和特性

* **应用场景**：搜索联想词
* **定义**：是一种树形结构，应用于统计和排序大量的字符串（不仅限于字符串），可以被搜索引擎用于文本词频统计。
* **优点**：最大限度地减少无谓的字符串比较，查询效率比哈希表高。

* **字典树的核心思想**：空间换时间
* **字典树的基本性质**：
  * 节点本身不存完整单词；
  * 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串；
  * 每个节点的所有子节点路径代表的字符都不相同

## 并查集

* **适用场景**:组团、配对问题;Group or not ?

* **基本操作:**
  * makeSet(s):建立一个新的并查集，其中包含s个单元素集合
  * unionSet(x,y):把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并;
  * find(x):找到元素x所在的集合的代表，该操作也可以用作判断两个元素是否位于同一个集合，只要将它们各自的代表比较一下就可以了。

## 高级搜索

* 剪枝，其实质就是回溯方式的前置应用，将不符合最终结果的分支进行去除，过滤，类似于二分法的处理过程，率先滤除不符合的分支，然后对符合条件的分支进行进一步的细化逻辑处理。

* 双向BFS，类似于双指针实现的夹逼法，只是将指针换做了BFS整体的业务逻辑，可以极大程度的缩减程序消耗。

* 启发式搜索的重点在于找到估价函数，在原有BFS的基础上使用了优先队列来替换原来的队列。

红黑树和AVL树还没有完全熟悉和练习，暂时不做记录。