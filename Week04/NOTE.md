学习笔记

深度优先搜索和广度优先搜索
每个节点都要访问一次，且每个节点仅访问一次；
对于节点的访问顺序不限：深度优先（DFS）和广度优先（BFS）；
递归：
visited = set() 
def dfs(node, visited):
    if node in visited: # terminator

already visited 

​    	return 
​	visited.add(node) 

process current node here. 

​	...
​	for next_node in node.children(): 
​		if next_node not in visited: 
​			dfs(next_node, visited)
​			
非递归模板：
def DFS(self, tree): 
​	if tree.root is None: 
​		return [] 
​	visited, stack = [], [tree.root]
​	while stack: 
​		node = stack.pop() 
​		visited.add(node)
​		process (node) 
​		nodes = generate_related_nodes(node) 
​		stack.push(nodes) 

other processing work 

​	...

贪心算法：
一种在每一步选择中都采取在当前状态下最好或最优（最有利）的选择，从而希望导致结果是全局最好或最优的算法。
与动态规划的不同：
对每一个子问题的解决方案都做出选择，不能回退，动态规划会保留以前的运算结果，并根据以前的结果对当前进行选择，有回退的功能。
适用的场景：
问题能够分解为子问题来解决，子问题的最优解能递推到最终问题的最优解，这种子问题最优解称为最优子结构。

二分查找：
前提：
	1.目标函数单调性（单调递增或递减）
	2.存在上下界
	3.能够通过索引访问