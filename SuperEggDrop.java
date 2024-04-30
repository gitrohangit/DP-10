// Time Complexity : O(n^2 * k) 
// Space Complexity : O(nk)
// Did this code successfully run on Leetcode : No,TLE 
// Approach : start from smaller problem i.e. smaller floor and less eggs and build it to larger problem. Order of the floor also matters.

// Your code here along with comments explaining your approach

class Solution {
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[k+1][n+1];

        for(int j = 0; j <= n ; j++){ // for 1 egg we have to linearly check each floor
            dp[1][j] = j;
        }

        for(int i = 2 ; i <= k ; i++){ // eggs //k
            for(int j = 1 ; j <= n; j++ ){ //floors //n
                dp[i][j] = Integer.MAX_VALUE;
                //floor permutations
                for(int f = 1 ; f <= j ;f++){         //n            //break,no break
                    dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[i-1][f-1], dp[i][j-f]));
                }
            }
        }

        return dp[k][n];
    }
}

//Optimized approach
// Time Complexity : O(nk) 
// Space Complexity : O(nk)
// Did this code successfully run on Leetcode : yes
// Approach : reverse engineer the solution by finding how floors i can explore with certain number of moves and eggs

class Solution {
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[n+1][k+1];
        int moves = 0;

        while(dp[moves][k] < n){
            moves++;
            for(int j = 1 ; j <= k ; j++){//eggs
                //1 attempt + egg break(below floors) + egg no break(above floors)
                dp[moves][j] = 1 + dp[moves-1][j-1] + dp[moves-1][j]; 
            }
            
        }
        
        return moves;
    }
}