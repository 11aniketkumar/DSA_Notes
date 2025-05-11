# Atcoder > DP Contest > Problem J - Sushi

## Problem Statement

There are N dishes, numbered 1,2,…,N. Initially, for each i (1≤i≤N), Dish i has a<sub>i</sub> ( 1≤a<sub>i</sub> ≤3) pieces of sushi on it.

Taro will perform the following operation repeatedly until all the pieces of sushi are eaten:

Roll a die that shows the numbers
1,2,…,N with equal probabilities, and let
i be the outcome. If there are some pieces of sushi on Dish
i, eat one of them; if there is none, do nothing.
Find the expected number of times the operation is performed before all the pieces of sushi are eaten.

## Constraints

-   All values in input are integers.
-   1 ≤ N ≤ 300
-   1 ≤ a<sub>i</sub> ≤ 3

# Explanation

-   Expected value solution can be seen in image but it cannot be used directly because it will cause infinite loop, just use some algebra tricks to remove the self loop.

![black background image](./blackbg.png)
![white background image](./whitebg.png)

# Solution

```java
public static double expected(double[][][] dp, int x, int y, int z) {
    if(x < 0 || y < 0 || z < 0) return 0;
    if(x == 0 && y == 0 && z == 0) return 0;

    if(dp[x][y][z] != -1) return dp[x][y][z];

    return dp[x][y][z] = (n + x*expected(dp, x-1, y, z) + y*expected(dp, x+1, y-1, z) + z*expected(dp, x, y+1, z-1)) / (x + y + z);
}
```

```java
public class J_Sushi {
    static int n;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        int x=0,y=0,z=0;
        for(int i = 0; i < n; i++) {
            int temp = s.nextInt();

            if(temp == 1) x++;
            else if(temp == 2) y++;
            else if(temp == 3) z++;
        }

        double[][][] dp = new double[301][301][301];
        for(int i = 0; i < 301; i++) {
            for(int j = 0; j < 301; j++) {
                for(int k = 0; k < 301; k++) {
                    dp[i][j][k] = -1.0;
                }
            }
        }

        dp[0][0][0] = 0.0;

        System.out.println(expected(dp, x, y, z));
    }
}
```
