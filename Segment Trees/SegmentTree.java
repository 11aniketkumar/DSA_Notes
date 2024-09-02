import java.util.*;
import java.io.*;


class SegmentTree {
    public static void createTree(int[] arr, int[] tree, int idx, int l, int r) {
        if(r == l) {
            tree[idx] = arr[l];
            return;
        }
        
        int mid = l + (r - l) / 2;
        int left_idx = 2 * idx + 1;
        int right_idx = 2 * idx + 2;

        createTree(arr, tree, left_idx, l, mid);
        createTree(arr, tree, right_idx, mid+1, r);
        tree[idx] = tree[left_idx] + tree[right_idx];
    }

    public static int query(int[] tree, int idx, int start, int end, int l, int r) {
        if((r < start) || (l > end)) {
            return 0;
        } if((start >= l) && (end <= r)) {
            return tree[idx];
        } else {
            int mid = start + (end - start) / 2;
            int left = query(tree, 2*idx+1,start,mid,l,r);
            int right = query(tree, 2*idx+2,mid+1,end,l,r);
            return left + right;
        }
    }

    public static int update(int[] tree, int idx, int start, int end, int i, int val) {
        if((i < start) || (i > end)) {
            return tree[idx];
        } else if((start == end) && (start == i)) {
            tree[idx] = val;
            return tree[idx];
        } else {
            int mid = start + (end - start) / 2;
            int left = update(tree, 2*idx+1, start, mid, i, val);
            int right = update(tree, 2*idx+2, mid+1, end, i, val);
            tree[idx] = left + right;
            return tree[idx];
        }
    }

    public static void main(String[] args) {
        int[] arr = {14,3,12,23};

        int[] tree = new int[arr.length * 4];
        createTree(arr, tree, 0, 0, arr.length - 1);

        for(int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();

        System.out.println(query(tree, 0, 0, arr.length - 1, 1,2));
        update(tree, 0, 0, arr.length - 1, 1, 6);
        System.out.println(query(tree, 0, 0, arr.length - 1, 1,2));

        for(int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }
}