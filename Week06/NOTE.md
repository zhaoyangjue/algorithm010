学习笔记

动态规划：将复杂问题简单化（递归的方式）。
分治+最优子结构（缓存局部最优值，淘汰次优值，递推之后就能获取到全局最优解）

和递归或分治没有根本上的区别：关键是看有无最优的子结构。
共性：找到重复子问题
差异性：最优子结构、中途可以淘汰次优解。
必须储存中间状态opt[i];

1.最优子结构
2.储存中间状态
3.DP方程

【例题】斐波那契数列；

public int fib(int n,int[] memo){
	if (n<=1) return n;
	if (memo[n] == 0){
		memo[n] = fib(n-1) + fib(n-2);
	}
	return memo[n];
}

【例题】路径计数62：

int countPaths(boolean[][] grid,int row,int col){
	if (!validSquare(grid,row,col)) return 0;
	if (isAtEnd(grid,row,col)) return 1;
	return countPaths(grid,row+1,col) + countPaths(grid,row,col+1);
}

状态转移方程：opt[i,j] = opt[i+1,j]+opt[i,j+1];



解题难点：确定边界条件；

动态方程可以根据枚举的办法来总结和归纳出来，尤其是需要注意规划的边界问题。