package leetCode.TwoSum;

import java.util.Arrays;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        int counterOne = 0;
        int counterTwo = 0;
        int[] resultArr = new int[2];
        for (int i = 0; i < nums.length; i += 1) {
            counterOne = i;
            for (int j = 1; j < nums.length; j += 1) {
                counterTwo = j;
                if (nums[i] + nums[j] == target) {
                    counterTwo = j;
                    counterOne = i;

                }
            }
            resultArr[0] = counterOne;
            resultArr[1] = counterTwo;
            System.out.println(Arrays.toString(resultArr));
            return resultArr;
        }
        return resultArr;
    }
}
