# [TwoPointer](./twoPointer.md) / Longest Repeating Character Replacement

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

-   [Youtube Link to Striver](https://www.youtube.com/watch?v=_eNhaDCr6P0)
-   [Leet Code Problem Link](https://leetcode.com/problems/longest-repeating-character-replacement/description/)

## HashMap

-   Time Complexity -> O(n), hashmap size can be atmost 26 so its constant

```java
public int characterReplacement(String s, int k) {
    int max_len = 0;
    HashMap<Character, Integer> hmap = new HashMap<>();

    int l = 0;
    for(int r = 0; r < s.length(); r++) {
        char c = s.charAt(r);
        int count = hmap.getOrDefault(c, 0);
        hmap.put(c, count + 1);

        int max = 0;
        for(char key : hmap.keySet()) {
            max = Math.max(hmap.get(key), max);
        }

        if(r-l+1-max > k) {
            c = s.charAt(l++);
            count = hmap.get(c) - 1;
            if(count <= 0) {
                hmap.remove(c);
            } else {
                hmap.put(c, count);
            }
        }
        max_len = Math.max(max_len, r-l+1);
    }
    return max_len;
}
```

## Optimization

-   If we are shifting the window, then no need to calculate `max_len`, it will remain same, so we can put it in `else` block.
-   Avoid calculating `max` again and again, we don't want to loop through entire hashmap again and again. We can calculate `max` just by checking it with frequency of rightmost character.
-   You might think we need to reduce `max` while moving the `l` pointer, as it might be deleting the character with the maximum count. However, reducing `max` is unnecessary.
    -   That's what the commented part was doing.
    -   For longer substring we will require longer repeating `max` count as well, since we can only manipulate `k` characters. The `length of substring - max = k` change the length.
    -   The commented part is not required, since the condition `(r-l+1) - max > k` becomes true exactly when `length of substring - max` was equal to `k`, since we are gonna look for greated `length of substring`, its only obvious that we will require greater `max` value than now, to equate the equation again to `k` value.

```java
public int characterReplacement(String s, int k) {
        int max_len = 0;
        HashMap<Character, Integer> hmap = new HashMap<>();

        int max = 0;
        int l = 0;
        for(int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            int count = hmap.getOrDefault(c, 0);
            hmap.put(c, count + 1);

            max = Math.max(max, count + 1);

            if(r-l+1-max > k) {
                c = s.charAt(l++);
                count = hmap.get(c) - 1;

                if(count <= 0) {
                    hmap.remove(c);
                } else {
                    hmap.put(c, count);
                    // for(char key : hmap.keySet()) {
                    //     max = Math.max(hmap.get(key), max);
                    // }
                }
            } else {
                max_len = Math.max(max_len, r-l+1);
            }
        }
        return max_len;
    }
```

# Array Method

-   Adding above optimization and replacing hashmap with a simple array
-   Time Complexity -> O(n)
-   Space Complexity -> O(26)

```java
public int characterReplacement(String s, int k) {
        int max_len = 0;
        int[] freq = new int[26];

        int max = 0;
        int l = 0;
        for(int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            int count = freq[c - 'A'] + 1;
            freq[c - 'A'] = count;

            max = Math.max(max, count);

            if(r-l+1-max > k) {
                c = s.charAt(l++);
                freq[c - 'A'] = freq[c - 'A'] - 1;
            } else {
                max_len = Math.max(max_len, r-l+1);
            }
        }
        return max_len;
    }
```
