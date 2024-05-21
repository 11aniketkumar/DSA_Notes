# Target Sum
[Dynamic Programming](DynamicProgramming.md)
* Check if the required sum can be achieved using any sum of subset of the given array
``` java
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
* Time Complexity -> O(2<sup>n</sup>)
* Space Complexity -> Recursion stack space -> max calls = n (array length)
``` java
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
* Time Complexity -> O(n * req_sum)
* Space Complexity -> 2d array space -> O(n * req_sum)
* `i` represents no. of allowed array element and `j` represents req_sum, final answer will be stored in `dp[n][req_sum]`.
* `dp[n][req_sum]` -> using all `n` items can we achieve `req_sum`.
* we can only add an element if it is less than or equal to required sum `val <= j`.
* <b><u>intiution</u></b>: 
     - If target sum is 5 and cur item is 2, then cur will fill 2 out of 5, I need to check whether rest of the element can fill remaining 3.
    - If the remaining items can fill the space completely then target sum is acieved i.e true or else false.
* `dp[i-1][j-val]` represents can we achieve `j-val` with `i-1` items. (Check Intiution)
``` java
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
