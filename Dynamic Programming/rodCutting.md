# Rod Cutting
[Dynamic Programming](DynamicProgramming.md)

* Given an array of rod lengths `len` and an array of prices `price` for each length, and a target rod length `L`, write a function to find the maximum revenue that can be obtained by cutting the rod into smaller pieces and selling them individually.<br>
The function should return the maximum revenue that can be obtained by cutting the rod into smaller pieces and selling them individually.

``` java
public static void main(String[] args) {
    int[] len = {1,2,3,4,5,6,7,8};
    int[] price = {1,5,8,9,10,17,17,20};
    int rodLength = 8;

    System.out.println(rodCutting(len, price, rodLength));
}
```


## Tabulation
* Time Complexity -> O(n * L)
* Space Complexity -> O(n * L)

``` java
public static int rodCutting(int[] len, int[] price, int L) {
    int n = len.length;
    int[][] dp = new int[n+1][L+1];

    // initialize i=0 with 0 and j=0 with 0

    for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= L; j++) {
            if(len[i-1] <= j) {         // since len[i-1]=i, could have directly written i<=j
                dp[i][j] = Math.max(price[i-1] + dp[i][j-len[i-1]], dp[i-1][j]);
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }
    return dp[n][L];
}
```
* In this case since rod length from 1 to 8 is given, no need to check `len[i-1]`, we could have directly accessed it using `i`. In fact, the code looks more clean and less complex, to reduce complexity we could have also saved value of length of cur and price of cur in a variable.
``` java
if(i <= j) {         // since len[i-1]=i, could have directly written i<=j
    dp[i][j] = Math.max(price[i-1] + dp[i][j-i], dp[i-1][j]);
} else {
    dp[i][j] = dp[i-1][j];
}
```