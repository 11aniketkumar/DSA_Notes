# Subset Sum

[Dynamic Programming](../DynamicProgramming.md)

-   Check if the required sum can be achieved using any sum of subset of the given array

## Latest Code
``` java
class Solution {
    static boolean subsetSum(Boolean[][] dp, int[] arr, int target, int n) {
        if(target == 0) return true;
        if(n <= 0) return false;
        
        // state already calculated
        if(dp[n][target] != null) {
            return dp[n][target];
        }
        
        // exclude case
        dp[n][target] = subsetSum(dp, arr, target, n-1);
        
        // include case -> only runs if exclude returned false
        if(!dp[n][target] && target >= arr[n-1]) {
            dp[n][target] = subsetSum(dp, arr, target - arr[n-1], n-1);
        }
        
        return dp[n][target];
    }
    
    static Boolean isSubsetSum(int arr[], int target) {
        int n = arr.length;
        Boolean[][] dp = new Boolean[n+1][target+1];
        
        // Memoization
        // return subsetSum(dp, arr, target, arr.length);
        
        for(int i = 0; i < n+1; i++) {
            dp[i][0] = true;
        }
        
        for(int j = 1; j < target+1; j++) {
            dp[0][j] = false;
        }
        
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < target+1; j++) {
                dp[i][j] = dp[i-1][j];
                if(!dp[i][j] && j >= arr[i-1]) {
                    dp[i][j] = dp[i-1][j-arr[i-1]];
                }
            }
        }
        
        return dp[n][target];
    }
}
```
## Older Version

```java
public static void main(String[] args) {
    int[] numbers = {4,2,7,1,3};
    int required_sum = 10;

    // Basic Recursion
    System.out.println(targetSum(numbers.length - 1, 0, numbers, required_sum));

    // Tabulation
    System.out.println(targetSumTab(numbers, required_sum));

}
```

## Basic Recursion

-   Time Complexity -> O(2<sup>n</sup>)
-   Space Complexity -> Recursion stack space -> max calls = n (array length)

```java
public static boolean targetSum(int i, int sum, int[] num, int req_sum) {
    if(sum > req_sum) {         // no need to check further
        return false;
    }
    if(sum == req_sum) {        // sol found
        return true;
    }
    if(i < 0) {                 // Array IndexOutOfRange
        return false;
    }

    if(targetSum(i-1, sum + num[i], num, req_sum)) {    // include item
        return true;            // return, no need to run exclude recursion
    }
    return targetSum(i-1, sum, num, req_sum);           // exclude item
}
```

## Tabulation

-   Time Complexity -> O(n \* req_sum)
-   Space Complexity -> 2d array space -> O(n \* req_sum)
-   `i` represents no. of allowed array element and `j` represents req_sum, final answer will be stored in `dp[n][req_sum]`.
-   `dp[n][req_sum]` -> using all `n` items can we achieve `req_sum`.
-   we can only add an element if it is less than or equal to required sum `val <= j`.
-   <b><u>intiution</u></b>:
    -   If target sum is 5 and cur item is 2, then cur will fill 2 out of 5, I need to check whether rest of the element can fill remaining 3.
    -   If the remaining items can fill the space completely then target sum is acieved i.e true or else false.
-   `dp[i-1][j-val]` represents can we achieve `j-val` with `i-1` items. (Check Intiution)

```java
public static boolean targetSumTab(int[] num, int req_sum) {
    int n = num.length;
    boolean[][] dp = new boolean[n+1][req_sum+1];

    for(int i = 0; i <= n; i++) {   // initially sum=0, so if req_sum=0
        dp[i][0] = true;
    }

    for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= req_sum; j++) {
            int val = num[i-1];
            if(val <= j && dp[i-1][j-val]) { // num is smaller than req_sum, and sum without val is true
                dp[i][j] = true;
            } else {                        // num is bigger than req_sum, exclude it
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    return dp[n][req_sum];
}
```
