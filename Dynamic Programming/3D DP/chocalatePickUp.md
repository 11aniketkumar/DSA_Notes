# Chocolates Pickup

[Dynamic Programming](../DynamicProgramming.md)

You are given an n rows and m cols matrix grid representing a field of chocolates where grid[i][j] represents the number of chocolates that you can collect from the (i, j) cell.

You have two robots that can collect chocolates for you:

Robot #1 is located at the top-left corner (0, 0), and
Robot #2 is located at the top-right corner (0, cols - 1).
Return the maximum number of chocolates collection using both robots by following the rules below:

From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
When any robot passes through a cell, It picks up all chocolates, and the cell becomes an empty cell.
When both robots stay in the same cell, only one takes the chocolates.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in grid.

[GFG Problem Link](https://www.geeksforgeeks.org/problems/chocolates-pickup/1)

## Memoization
``` java
public int maxSum(int[][][] dp, int[][] grid, int row, int j1, int j2) {
    if(j1 < 0 || j1 >= grid[0].length || j2 < 0 || j2 >= grid[0].length) {
        return 0;
    }
    if(row == grid.length) return 0;
    
    if(dp[row][j1][j2] != -1) return dp[row][j1][j2];
    
    int max_count = 0;
    for(int i = -1; i <= 1; i++) {
        for(int j = -1; j <= 1; j++) {
            max_count = Math.max(max_count, maxSum(dp, grid, row+1, j1+i, j2+j));
        }
    }
    
    max_count += grid[row][j1] + grid[row][j2];
    if(j1 == j2) max_count -= grid[row][j1];
    
    return dp[row][j1][j2] = max_count;
}
```
``` java
public int solve(int n, int m, int grid[][]) {
    int[][][] dp = new int[n][m][m];
    
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            for(int k = 0; k < m; k++) {
                dp[i][j][k] = -1;
            }
        }
    }
    
    return maxSum(dp, grid, 0, 0, m-1);

}
```

## Tabulation
``` java
public int solve(int n, int m, int grid[][]) {
    int[][][] dp = new int[n][m][m];
    
    for(int j1 = 0; j1 < m; j1++) {
        for(int j2 = 0; j2 < m; j2++) {
            dp[n-1][j1][j2] = grid[n-1][j1] + grid[n-1][j2];
            if(j1 == j2) dp[n-1][j1][j2] = grid[n-1][j1];
        }
    }

    for(int row = n-2; row >= 0; row--) {
        for(int j1 = 0; j1 < m; j1++) {
            for(int j2 = 0; j2 < m; j2++) {
                int max_count = 0;
                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        if(j1+i < 0 || j1+i >= m || j2+j < 0 || j2+j >= m) {
                            continue;
                        }
                        max_count = Math.max(max_count, dp[row+1][j1+i][j2+j]);
                    }
                }
                max_count += grid[row][j1] + grid[row][j2];
                if(j1 == j2) max_count -= grid[row][j1];
                
                dp[row][j1][j2] = max_count;
            }
        }
    }
    
    return dp[0][0][m-1];
}
```

## Space Optimization
``` java
public int solve(int n, int m, int grid[][]) {
    int[][] prev = new int[m][m];
    
    for(int j1 = 0; j1 < m; j1++) {
        for(int j2 = 0; j2 < m; j2++) {
            prev[j1][j2] = grid[n-1][j1] + grid[n-1][j2];
            if(j1 == j2) prev[j1][j2] = grid[n-1][j1];
        }
    }
    
    int[][] cur = new int[m][m];
    for(int row = n-2; row >= 0; row--) {
        for(int j1 = 0; j1 < m; j1++) {
            for(int j2 = 0; j2 < m; j2++) {
                int max_count = 0;
                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        if(j1+i < 0 || j1+i >= m || j2+j < 0 || j2+j >= m) {
                            continue;
                        }
                        max_count = Math.max(max_count, prev[j1+i][j2+j]);
                    }
                }
                max_count += grid[row][j1] + grid[row][j2];
                if(j1 == j2) max_count -= grid[row][j1];
                
                cur[j1][j2] = max_count;
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                prev[i][j] = cur[i][j];
            }
        }
    }
    
    return prev[0][m-1];
}
```