# [DSA](../README.md) / Bit Manipulation

| Operation   | Symbol | Description                                      |
| ----------- | ------ | ------------------------------------------------ |
| AND         | &      | Returns 1 only if both operands are 1            |
| OR          | \|     | Returns 1 if at least one of the operands is 1   |
| NEGATION    | ~      | Flips all the bits                               |
| XOR         | ^      | Returns 1 if exactly one of the operands is 1    |
| LEFT SHIFT  | <<     | Shifts the bits of the left operand to the left  |
| RIGHT SHIFT | >>     | Shifts the bits of the left operand to the right |

## Check if number is Even or Odd

<table>
<tr>
<td>

```java
if ((n & 1) == 0) {
    // even
} else {
    // odd
}
```

</td>
<td>

```java
if((n & 1) != 0) {
    // odd
} else {
    // even
}
```

</td>
</tr>
</table>
