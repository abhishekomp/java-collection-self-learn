package org.example.puzzle.min_steps_end_of_array;

// Java program to count Minimum number
// of jumps to reach end
//https://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/
//This program is from their website
class MinimumStepsCalculator {
    static int minJumps(int arr[])
    {
        if (arr.length <= 1)
            return 0;

        //If value of first index guarantees
        //only 1 jump is needed, return 1
        if (arr[0] >= arr.length-1)
            return 1;

        // Return -1 if not possible to jump
        if (arr[0] == 0)
            return -1;

        // initialization
        int maxReach = arr[0];
        int step = arr[0];
        int jump = 1;

        // Start traversing array
        for (int i = 1; i < arr.length; i++) {
            // Check if we have reached
// the end of the array
            if (i == arr.length - 1)
                return jump;

            //Check if value at current index guarantees jump to end
            if (arr[i] >= (arr.length-1) - i)
                return jump + 1;

            // updating maxReach
            maxReach = Math.max(maxReach, i + arr[i]);

            // we use a step to get to the current index
            step--;

            // If no further steps left
            if (step == 0) {
                // we must have used a jump
                jump++;

                // Check if the current
// index/position or lesser index
                // is the maximum reach point
// from the previous indexes
                if (i >= maxReach)
                    return -1;

                // re-initialize the steps to the amount
                // of steps to reach maxReach from position i.
                step = maxReach - i;
            }
        }

        return -1;
    }

    // Driver method to test the above function
    public static void main(String[] args)
    {
        //int arr[] = new int[] { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };
        //int arr[] = new int[] { 3, 1, 4, 1, 1, 1 }; //2
        //int arr[] = new int[] { 2, 2, 0, 1 };   //2
        int[] arr = new int[] {3, 4, 2, 0, 1, 1};

        // calling minJumps method
        System.out.println(minJumps(arr));
    }
}

