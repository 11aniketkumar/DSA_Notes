# [TwoPointer](./twoPointer.md) / Minimum Window Substring

Given two strings `s` and `t` of lengths `m` and `n` respectively, return the minimum window substring of `s` such that every character in `t` (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

-   [Youtube Link to Striver](https://www.youtube.com/watch?v=WJaij9ffOIY)
-   [Leet Code Problem Link](https://leetcode.com/problems/minimum-window-substring/description/)

## Brute Force Approach

-   Just generate all possible substring and check for substring using hashmap concept explained below.

## Two Pointer Approach

-   Use a HashMap to store the count of each character in string `t`, assuming the count for all other characters to be zero.
-   Use two pointers to loop through the entire string `s`. If the character count in `char_hash` is positive, it means the character is from string `t`. Since we found a matching character, increase the `count`. When `count` reaches the length of `t`, it means we have found all required characters.
-   For every character included in the substring, reduce the character count in `char_hash`. The reverse is also true as explained below.
-   If you find a substring with all required characters of `t`, try to make the substring as small as possible by increasing the `l` pointer. For every character removed from the substring, increase the character count in `char_hash`. If the character count ever increases above zero, it means that specific character was part of string `t`, but due to the increasing value of `l`, it got excluded from the substring, so we reduce the `count` variable.

```java
public String minWindow(String s, String t) {
    int[] char_hash = new int[256];

    for(int i = 0; i < t.length(); i++) {
        char_hash[t.charAt(i) - 'A']++;
    }

    int min_len = Integer.MAX_VALUE;
    int start_idx = -1;
    int count = 0;
    int l = 0;
    for(int r = 0; r < s.length(); r++) {
        int idx = s.charAt(r) - 'A';

        if(char_hash[idx] > 0) count++;
        char_hash[idx]--;

        while(count == t.length()) {
            if(r-l+1 < min_len) {
                start_idx = l;
                min_len = r - l + 1;
            }

            idx = s.charAt(l) - 'A';

            char_hash[idx]++;
            if(char_hash[idx] > 0) count--;
            l++;
        }
    }

    if(start_idx == -1) {
        return "";
    } else {
        return s.substring(start_idx, start_idx + min_len);
    }
}
```
