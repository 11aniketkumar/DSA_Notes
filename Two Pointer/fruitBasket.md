# [TwoPointer](./twoPointer.md) / Fruit Basket

You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array of arr[], where arr[i] is the type of fruit the ith tree produces.
You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow :

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of the baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array of fruits, return the maximum number of fruits you can pick.

-   [Youtube Link to Striver](https://www.youtube.com/watch?v=e3bs0uA1NhQ&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=5)
-   [Problem Link](https://www.geeksforgeeks.org/problems/fruit-into-baskets-1663137462/1)

<br>

<b>Trimed Question</b> - Find the max length of the subarray with at most two unique elements.

## Brute Force Approach

-   Generate all subarrays and check for the max number of zeroes in it.
-   Time complexity -> O(n<sup>2</sup>)

```java
public static int totalFruits(Integer[] arr) {
    int n = arr.length;
    int max_len = 0;

    for(int i = 0; i < n; i++) {
        Set<Integer> s = new HashSet<>();
        for(int j = i; j < n; j++) {
            s.add(arr[j]);

            if(s.size() > 2) {
                break;
            }
            max_len = Math.max(max_len, j-i+1);
        }
    }
    return max_len;
}
```

## Two Pointer Approach

-   Time Complexity -> O(2n) -> Ignore time taken by hashmap due to very small size
-   Space Complexity -> O(3) -> Hashmap can contain at max 3 element
-   We are keeping total count of certain fruit encountered in hashmap.

```java
public static int totalFruits(Integer[] arr) {
    int n = arr.length;
    int max_len = 0;
    HashMap<Integer,Integer> fruits = new HashMap<>();

    int l = 0;
    for(int r = 0; r < n; r++) {
        int f = arr[r];
        int count = fruits.getOrDefault(f, 0);
        fruits.put(f, count + 1);

        while(fruits.size() > 2) {
            f = arr[l++];
            count = fruits.get(f) - 1;

            if(count > 0) {
                fruits.put(f, count);
            } else {
                fruits.remove(f);
            }
        }
        max_len = Math.max(max_len, r-l+1);
    }
    return max_len;
}
```

-The other approach that I thought was to store directly the index of the fruits in hashmap, so I would know on which index this fruit was seen the last time.

-   So, if a fruit is repeated million times, i can directly increment `l` to the smallest fruit `index+1`.
-   This approach also doesn't require keeping track of count. So, we can directly put elements in hashmap without checking if the element exists in hashmap or not, fetching and adding 1 to count etc.
-   But, it still wastes time on finding minimum of all the three keys in hashmap.

```java
public static int totalFruits(Integer[] arr) {
    int n = arr.length;
    int max_len = 0;
    HashMap<Integer,Integer> fruits = new HashMap<>();

    int l = 0;
    for(int r = 0; r < n; r++) {
        fruits.put(arr[r], r);

        while(fruits.size() > 2) {
            int fruit = -1;
            int min_index = Integer.MAX_VALUE;
            for(int f : fruits.keySet()) {
                if(fruits.get(f) < min_index) {
                    fruit = f;
                    min_index = fruits.get(f);
                }
            }
            fruits.remove(fruit);
            l = min_index + 1;
        }

        max_len = Math.max(max_len, r-l+1);
    }
    return max_len;
}
```

-   Another approach that's definitely O(n).
-   Instead of shrinking window, we can simply move the window to right by 1 position.
-   After moving `left` pointer by 1, the size of the window will remain same as the previous loop window, so no need of calculating `max_len` again in this loop, so we put it in `else` block.

```java
public static int totalFruits(Integer[] arr) {
    int n = arr.length;
    int max_len = 0;
    HashMap<Integer,Integer> fruits = new HashMap<>();

    int l = 0;
    for(int r = 0; r < n; r++) {
        int f = arr[r];
        int count = fruits.getOrDefault(f, 0);
        fruits.put(f, count + 1);

        if(fruits.size() > 2) {
            f = arr[l++];
            count = fruits.get(f) - 1;

            if(count > 0) {
                fruits.put(f, count);
            } else {
                fruits.remove(f);
            }
        } else {
            max_len = Math.max(max_len, r-l+1);
        }
    }
    return max_len;
}
```

-   Combining both of the above approaches:
    -   store index in hashmap
    -   if `size() > 2` then don't try to find the lowest index, instead just move the window by 1 position to right. Thus maintaining the window size
    -   After adjusting the left pointer, check if the element removed from the left has a last-seen index that is less than the current left index. If it does, remove the element from the hashmap.
    -   This approach doesn't require to keep track of count of element. No need to check if element exists or not, then adding 1 to existing count etc.
    -   While removing also, we were again and again fetching count, reducing by 1 and updating hashmap, and comparing with 0, which is not required.

```java
public static int totalFruits(Integer[] arr) {
    int n = arr.length;
    int max_len = 0;
    HashMap<Integer,Integer> fruits = new HashMap<>();

    int l = 0;
    for(int r = 0; r < n; r++) {
        fruits.put(arr[r], r);

        if(fruits.size() > 2) {
            int f = arr[l++];
            int f_index = fruits.get(f);

            if(f_index < l) {
                fruits.remove(f);
            }
        } else {
            max_len = Math.max(max_len, r-l+1);
        }
    }
    return max_len;
}
```
