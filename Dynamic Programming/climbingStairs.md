# Climbing Stairs
[Dynamic Programming](DynamicProgramming.md)
* Count all possible ways to reach nth stair. The person can either climb 1 or 2 stairs at a time.

## Basic Recursion Code
* Time Complexity -> O(2<sup>n</sup>)
* Space Complexity -> recursion stack space which is n (array size). -> O(n)
``` java
public static int countWays(int n) {
    if(n==0) {
        return 1;
    }
    if(n < 0) {
        return 0;
    }
    return countWays(n-1) + countWays(n-2);
}
```

## Memoization Code
* Time Complexity -> O(n)
* Space Complexity -> n(array size) + recursion stack space. -> O(n) + O(n)
``` java
public static int countWays(int n, int[] ways) {
    if(n==0) return 1;
    if(n < 0) return 0;

    if(ways[n] != -1) {  // check if already calculated
        return ways[n];
    }

    ways[n] = countWays(n-1, ways) + countWays(n-2, ways);
    return ways[n];
}
```

## Tabulation Code
* Time Complexity -> O(n)
* Space Complexity -> n (array size). -> O(n)
* Further optimization can reduce the space complexity to O(1). For calculation we only require last two values and not the complete array.
``` java
public static int countWaysTab(int n) {
    int dp[] = new int[n+1];
    dp[0] = 1;
    dp[1] = 1;

    for(int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
```

## Main Method
``` java
public static void main(String[] args) {
    int n = 5;
    int ways[] = new int[n+1];
    Arrays.fill(ways, -1);

    System.out.println(countWays(n)); // Normal recursion
    System.out.println(countWays(n, ways)); // memoization
    System.out.println(countWaysTab(n));    // tabulation
}
```