# [Recursion](./recursion%20and%20backtracking.md) / Permutations

-   Given an array `nums` of distinct integers, return all the possible permutations. You can return the answer in any order.

[LeetCode Problem Link](https://leetcode.com/problems/permutations/description/)

-   Total Number of permutations = n _ (n-1) _ (n-2) _ ...... _ 2 \* 1

# Using additional map Space

-   Time Complexity : `O(n! * n)`
-   Space Complexity : `cur` + `visited` => `O(n) + O(n)`
    <br>Ignoring `ans` space, as instead of adding to it, we can directly print the cur

```java
public void create(List<List<Integer>> ans, int[] nums, boolean[] visited List<Integer> cur) {
    if(cur.size() == nums.length) {
        ans.add(new ArrayList<>(cur));
        return;
    }
    for(int i = 0; i < nums.length; i++) {
        if(!visited[i]) {
            visited[i] = true;
            cur.add(nums[i]);
            create(ans, nums, visited, cur);
            cur.remove(cur.size() - 1);
            visited[i] = false;
        }
    }
}
```

```java
public List<List<Integer>> permute(int[] nums) {
    List<Integer> cur = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();
    boolean[] visited = new boolean[nums.length];

    create(ans, nums, visited, cur);

    return ans;
}
```

# Space Optimized Solution

-   Time Complexity : `O(n! * n)`
-   Space Complexity : `O(1)`
    <br>Ignoring `ans` space, as instead of adding to it, we can directly print the cur

```java
public void create(List<List<Integer>> ans, int[] nums, int n) {
    if(n == nums.length) {
        ArrayList<Integer> cur = new ArrayList<>();
        for(int i : nums) cur.add(i);
        ans.add(cur);
        return;
    }

    for(int i = n; i < nums.length; i++) {
        swap(nums, n, i);
        create(ans, nums, n+1);
        swap(nums, n, i);
    }
}
```

```java
public void swap(int[] nums, int n, int i) {
    int temp = nums[n];
    nums[n] = nums[i];
    nums[i] = temp;
}

public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    create(ans, nums, 0);
    return ans;
}
```
