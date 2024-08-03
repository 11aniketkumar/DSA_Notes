# [TwoPointer](./twoPointer.md) / Longest Substring without Repeating character

Find the length of the longest substring without repeating characters.

-   [Youtube Link to Striver](https://www.youtube.com/watch?v=-zSxTJkcdAo&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=3)
-   [Leet Code Problem Link](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)

## Brute Force Approach

-   Time Complexity -> O(n<sup>2</sup>)
-   Space Complexity -> O(n) -> 256 size boolean array

```java
public static int longestSubstringLen(String str, int n) {
    int max_len = -1;
    for(int i = 0; i < n; i++) {
        boolean[] hash = new boolean[256];

        for(int j = i; j < n; j++) {
            if(hash[str.charAt(j)]) {
                break;
            } else {
                hash[str.charAt(j)] = true;
            }

            max_len = Math.max(max_len, j-i+1);
        }
    }

    return max_len;
}
```

## Two Pointer Approach

-   We have two approaches, the first one gives:
    -   Time Complexity -> O(n + n) -> both pointer can move n positions at most.
    -   Space Complexity -> O(n) -> 256 size boolean array

```java
public static int longestSubstringLen(String str, int n) {
    boolean[] hash = new boolean[256];
    int max_len = 0;

    int left = 0;
    for(int right = 0; right < n; right++) {
        while(hash[str.charAt(right)] && left <= right) {
            hash[str.charAt(left++)] = false;
        }

        hash[str.charAt(right)] = true;
        max_len = Math.max(max_len, right-left+1);
    }
    return max_len;
}
```

-   Above approach can be further optimized:
    -   Time Complexity -> O(n)
    -   Space Complexity -> O(n) -> 256 size integer array
    -   I only need to move the `left` pointer if the character is visited and present in the current substring, i.e it is between `left` and `right`, if in case it is less than `left` pointer

```java
public static int longestSubstringLen(String str, int n) {
    int[] hash = new int[256];
    Arrays.fill(hash, -1);
    int max_len = 0;

    int left = 0;
    for(int right = 0; right < n; right++) {
        if(hash[str.charAt(right)] != -1) {             // visited
            if(hash[str.charAt(right)] >= left) {       // but was it visited in current substring, i.e between left & right pointer
                left = hash[str.charAt(right)] + 1;
            }
        }

        hash[str.charAt(right)] = right;
        max_len = Math.max(max_len, right-left+1);
    }
    return max_len;
}
```

-   Its a trade off between time and space, to reduce time complexity we converted boolean array to integer array. But in most cases, less time complexity is more preferable, since resources can be purchased for space but not the time.
-   Anyhow, we need to keep an array, why not increase the space a little if it can reduce the time complexity.
