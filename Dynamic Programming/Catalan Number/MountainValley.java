import java.util.*;

class MountainValley {
    public static int count(int up, int down) {
        if(up == 0 && down == 0) {
            return 1;
        }

        int first = 0;
        int second = 0;

        if(up > 0) first = count(up-1, down);
        if(up < down) second = count(up, down-1);
        
        return first + second;
    }


    /////////////////////////////////////////////////////////////////////
    // This is catalan sequence, could have directly used catalan code //
    /////////////////////////////////////////////////////////////////////
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        System.out.println(count(n,n));

    }
}