# [DSA](../README.md) / Two Pointer Sliding Window

-   [Youtube Link to Striver](https://www.youtube.com/watch?v=9kdHxplyl5I&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL)

## 1. Constant Window

-   In such type of questions window size is constant. <br>Eg - Pick 4 consecutive element from array with sum equal to 4.

## 2. Longest subarray/subset where { condition }

-   <b>Brute Force approach[ O(n2) ]:</b> Generate all subarray.

-   <b>Sliding Window [ O(n + n) ]: </b> Start with window size of 1, and increase and decrease according to the condition.
    -   expand window from right - `r++`
    -   shrink window from left - `l++`
    -   basically whether shrinking the window or expanding the pointer always moves in right direction.
-   <b>Optimization O(n) :</b> If the problem requires only the length of the subarray, if the condition ever fails don't reduce the size of window, but just start moving it to right side. Replace the shrinking `while` loop with an `if` statement.

## 3. No. of subarrays where { condition }

TODO :: will be solved using pattern 2

## 4. Shortest/Minimum Window where { condition }

-   TODO :: if a valid window is found, shrink it until it is not valid.

<br><br>

# Problems

1. [Length of longest Substring without repeating character](./longestSubstring.md)
2. [Max Consecutive Ones](./maxConsecutiveOne.md)
3. [Fruit Basket](./fruitBasket.md)
4. [Longest Repeating Character Replacement](./longestRepeatingCharacterReplacement.md)
5. [Count of binary Subarray with given sum](binarySubarrayWithSum.md) \*\*\*
6. [Minimum Substring window](./minimumSubstringWindow.md)
