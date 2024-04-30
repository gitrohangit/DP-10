// TC :O(n^3) - to iterate over the nums for 2d matrix and going through
//             the n elements in each burstible array
// SC :O(n^2) - to store the dp

//Approach: the order of bursting the balloons mattered so find all possible permutations by burstinig each balloon at the end.

class Solution {
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;

        int[][] dp = new int[n][n];

        //burstible array
        for(int l = 1 ; l <= n ; l++){

            //start
            for(int i = 0 ; i <= n-l ; i++){
                //end
                int j = i+l-1;

                //bursting kth balloon at the end
                for(int k = i ; k <= j ; k++){
                    //before ->  left already burst
                    //after -> right already burst
                    int before = 0; //value from left that is already burst
                    if(k != i){
                        before = dp[i][k-1];
                    }

                    int after = 0; //value from right that is already burst
                    if(k != j){
                        after = dp[k+1][j];
                    }

                    int left = 1;
                    if(i != 0){
                        left = nums[i-1];
                    }

                    int right = 1;
                    if(j != n-1){
                        right = nums[j+1];
                    }


                    int curr = before +left * nums[k] * right + after;

                    dp[i][j] = Math.max(curr, dp[i][j]);
                }
            }

        }

        return dp[0][n-1];
    }
}