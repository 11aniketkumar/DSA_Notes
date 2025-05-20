# [Recursion](./recursion%20and%20backtracking.md) / nQueens

```java
class Solution {
    public boolean checkPosition(int row, int col, boolean[][] board) {
        // vertical check
        for(int i = 0; i < row; i++) {
            if(board[i][col]) return false;
        }

        // top left diagonal check
        for(int i=row, j=col; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j]) return false;
        }

        // top right diagonal check
        for(int i=row, j=col; i >= 0 && j < board.length; i--,j++) {
            if(board[i][j]) return false;
        }

        return true;
    }

    // converting solution from 2d boolean array to list of list of string
    public void save(boolean[][] board, List<List<String>> allBoards) {
        List<String> ans = new ArrayList<>();

        for(int i = 0; i < board.length; i++) {
            String s = "";
            for(int j = 0; j < board.length; j++) {
                if(board[i][j]) {
                    s += "Q";
                } else {
                    s += ".";
                }
            }
            ans.add(s);
        }

        allBoards.add(ans);
    }

    // actual recursion code that will solve the problem
    public void solve(int row, boolean[][] board, List<List<String>> allBoards) {
        if(row >= board.length) {
            save(board, allBoards);
            return;
        }

        for(int i = 0; i < board.length; i++) {
            if(checkPosition(row, i, board)) {
                board[row][i] = true;
                solve(row+1, board, allBoards);
                board[row][i] = false;
            }
        }
    }

    // parent function that will keep track of arrays and lists and will return the final answer
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> allBoards = new ArrayList<>();

        boolean[][] board = new boolean[n][n];
        solve(0, board, allBoards);
        return allBoards;
    }
}
```
