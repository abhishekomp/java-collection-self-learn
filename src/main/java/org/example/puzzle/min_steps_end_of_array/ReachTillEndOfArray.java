package org.example.puzzle.min_steps_end_of_array;

public class ReachTillEndOfArray {
    public boolean canReachTillEndOfArray(int[] inputArr) {
        int arrLength = inputArr.length;
        if(arrLength == 1) {
            System.out.println("Single element array, hence already at the end of the array.");
            return true;
        }
        if(inputArr[0] == 0) {
            System.out.println("First element is 0 hence cannot reach");
            return false;
        }
        int target = arrLength - 1;
        for(int i = target - 1; i >= 0; i--){
            if(i + inputArr[i] >= target) {
                //System.out.println("Can reach till " + inputArr[target] + " at " + target + " from " + inputArr[i] + " at " + i);
                target = i;
            } else {
                //System.out.println("Cannot reach till " + inputArr[target] + " at " + target + " from " + inputArr[i] + " at " + i);
            }
        }
        return target == 0;
    }


    /**
     * Number of jumps required to land exactly on the last element of the array
     * @param inputArr Given input integer array
     * @return  int value of minimum jumps to reach exactly the end of the array
     */
    int minNumberOfJumpsToReachEnd(int[] inputArr) {
//        if(!canReachTillEndOfArray(inputArr)) {
//            return -1;
//        }

        // int[] inputArr = new int[] {2, 4, 1, 2, 3, 1, 1, 2} -> min jumps = 3
        int totalJumps = 0;
        int destination = inputArr.length - 1;
        int coverage = 0;
        int lastJumpIndex = 0;

        //base case
        if(inputArr.length == 1) {
            return 0;
        }

        for(int i = 0; i < inputArr.length - 1; i++) {
            coverage = Math.max(coverage, i + inputArr[i]);
            if(i == lastJumpIndex) {
                lastJumpIndex = coverage;
                totalJumps++;

                if(coverage >= destination) {
                    System.out.println("Reach directly or beyond on the end element");
                    return totalJumps;
                }

            }
        }
        return totalJumps;
    }

    public static void main(String[] args) {

        //int[] inputArr = new int[] {2, 4, 1, 2, 3, 1, 1, 2};    //5
        //int[] inputArr = new int[] {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};    //3
        //int[] inputArr = new int[] {2, 3, 1, 0, 4}; //2
        //int[] inputArr = new int[] {1, 0, 4};
        //int[] inputArr = new int[] {2, 3, 1, 0, 4}; //lands exactly on the end of array
        int[] inputArr = new int[] {2, 4, 1, 0, 4};
        ReachTillEndOfArray runner = new ReachTillEndOfArray();
//        int[] inputArr = new int[] {2, 3, 1, 1, 4};
//        int[] inputArr = new int[] {1, 1, 2, 0, 4};
//        int[] inputArr = new int[] {2, 3, 1, 0, 4};
//        int[] inputArr = new int[] {0, 3, 1, 0, 4};
//        ReachTillEndOfArray runner = new ReachTillEndOfArray();
//        boolean result = runner.canReachTillEndOfArray(inputArr);
//        System.out.println("result = " + result);

        // Minimum number of steps
        // Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9} Output: 3 (1-> 3 -> 8 ->9)
        // First element is 1, so can only go to 3. Second element is 3, so can make at most 3 steps i.e. to 5 or 8 or 9.
        // 2nd example:  { 4, 2, 0, 3, 2, 0, 1, 8 } will have minimum jumps = 3
        // 3 jumps: (4 —> 3 —> 1 —> 8) or (4 —> 2 —> 1 —> 8)
        // 4 jumps: (4 —> 2 —> 3 —> 1 —> 8) or (4 —> 3 —> 2 —> 1 —> 8)
        // 5 jumps: (4 —> 2 —> 3 —> 2 —> 1 —> 8)
        // 3rd example: int[] nums = { 1, 3, 6, 1, 0, 9 }; will have minimum jumps = 3

        int jumpsToReachEnd = runner.minNumberOfJumpsToReachEnd(inputArr);
        System.out.println("jumpsToReachEnd = " + jumpsToReachEnd);

    }
}
