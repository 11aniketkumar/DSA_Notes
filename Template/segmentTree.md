# Basic Segment Tree Template

``` json
{
  "Segment Tree Template": {
    "prefix": "sgt",
    "body": [
      "static class SGTree {",
      "    int[] tree;",
      "",
      "    public SGTree(int size) {",
      "        this.tree = new int[4 * size + 1];",
      "    }",
      "",
      "    public void build(int idx, int start, int end, int[] arr) {",
      "        if (start == end) {",
      "            tree[idx] = arr[start];",
      "            return;",
      "        }",
      "",
      "        int mid = start + (end - start) / 2;",
      "        build(2 * idx + 1, start, mid, arr);",
      "        build(2 * idx + 2, mid + 1, end, arr);",
      "",
      "        tree[idx] = Math.min(tree[2 * idx + 1], tree[2 * idx + 2]);",
      "    }",
      "",
      "    public int query(int idx, int start, int end, int l, int r) {",
      "        if (start > r || end < l) return Integer.MAX_VALUE;",
      "        if (start >= l && end <= r) return tree[idx];",
      "",
      "        int mid = start + (end - start) / 2;",
      "        int left = query(2 * idx + 1, start, mid, l, r);",
      "        int right = query(2 * idx + 2, mid + 1, end, l, r);",
      "",
      "        return Math.min(left, right);",
      "    }",
      "",
      "    public void update(int idx, int start, int end, int i, int val) {",
      "        if (start == end) {",
      "            tree[idx] = val;",
      "            return;",
      "        }",
      "",
      "        int mid = start + (end - start) / 2;",
      "",
      "        if (i <= mid) {",
      "            update(2 * idx + 1, start, mid, i, val);",
      "        } else {",
      "            update(2 * idx + 2, mid + 1, end, i, val);",
      "        }",
      "        tree[idx] = Math.min(tree[2 * idx + 1], tree[2 * idx + 2]);",
      "    }",
      "}"
    ],
    "description": "Segment tree template for range minimum queries."
  }
}
```