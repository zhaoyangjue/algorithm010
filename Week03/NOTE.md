学习笔记

树的面试题解法一般都是递归：1.节点的定义；2.重复性（自相似性）
递归-循环：通过函数体来进行的循环；
e.g.:求解n! = 1* 2* 3*...*n

递归模板：1.递归终止条件；2.处理当前层逻辑；3.下探到下一层；4.清理当前层（全局变量或者不适用本层调用的过程）；

~~~java
public void recur(int level,int param){
	// terminator
	if (level > max_level){
	// process result
		return ;
	}
	// process current logic
	process(level,param);
	// drill down
	recur(level:level+1,newParam);
	// restore current status
}
~~~

思维要点
1.不要人肉进行递归；
2.找到最近最简方法，将其拆解为可重复解决的问题（重复子问题）
3.数学归纳法思维

二叉搜索树的中序遍历是递增的。

分治和回溯虽然和迭代很相似，但是总感觉像是在遍历各种情况，理解很好理解，但是在做题的时候感觉还是稍微有些压力。

