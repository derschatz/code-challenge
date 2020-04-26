/**
 * Thi is the proposed solution for the problem described bellow:
 * 
 * 1. Replacing characters in place:
 * 
 * Given an array of characters, write a method to replace all the spaces with “&32”.
 * You may assume that the array has sufficient slots at the end to hold the additional
 * characters, and that you are given the “true” length of the array. (Please perform this
 * operation in place with no other auxiliary structure).
 * 
 * Example:
 * Input: “User is not allowed “, 19
 * Output: “User&32is&32not&32allowed”
 * 
 * This solution has time complexity of O(n)
 */
public final class Algo1 {

    public void replace(char[] array, int trueSize){
        int whiteSpacesCount = 0;

        for(int i =0; i < trueSize; i++){
            if(array[i] == ' '){
                whiteSpacesCount++;
            }
        }

        // calculate the position of the last char 
        // of the new string in the array
        // adding 2 new characters per white space
        int newSize = trueSize + (whiteSpacesCount * 2);
        int destIndex = newSize - 1;
        int origIndex = trueSize - 1;

        while(destIndex >= 0 && origIndex >= 0)
        {
            if (array[origIndex]== ' '){
                // &32
                array[destIndex--] = '2';
                array[destIndex--] = '3';
                array[destIndex--] = '&';
                origIndex --;
                continue;
            }
            array[destIndex--] = array[origIndex--];
        }

        System.out.print(array);
    }

    public static void main(String... args) {
        String inputSample = "User is not allowed ";
        int inputSampleSize = 19;
        char[] sampleArray = copyStr2Array(inputSample, inputSampleSize);

        Algo1 algo1 = new Algo1();
        algo1.replace(sampleArray, inputSampleSize);
    }

    private static char[] copyStr2Array(String str, int trueSize) {
        // 3 new chars for every empty space
        // if the string has n empty spaces and size n
        // we need an array of size 3 * n
        char[] array = new char[trueSize * 3]; 
        for (int i = 0; i < trueSize; ++i) {
            array[i] = str.charAt(i);
        }
        return array;
    }
}