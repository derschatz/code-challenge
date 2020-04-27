package com.example.searchapp.utils;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

public final class JumbledUtils {
    private JumbledUtils(){}

    /**
     * Check if two words are the same word but with jumbled letters.
     * @param w1 a String
     * @param w2
     * @return true if w1 and w2 are the same word with jumbled letters, false otherwise
     */
    public static boolean isJumbled(@NonNull String w1, @NonNull String w2) {

        if(w1.charAt(0)!=w2.charAt(0)){
            return false;
        }

        // map characters from both string to check if they are the same
        final Set<Character> chars1 = new HashSet<>();
        for(int i = 0; i < w1.length(); i++){
            chars1.add(w1.charAt(i));
        }

        final Set<Character> chars2 = new HashSet<>();
        for(int i = 0; i < w2.length(); i++) {
            chars2.add(w2.charAt(i));
        }

        // words must have the same number of characters
        if(chars2.size()!=chars1.size()) {
            return false;
        }

        int countJumbled = 0;
        for(int i = 1; i < w1.length(); i++){
            if(!chars1.contains(w2.charAt(i)) || !chars2.contains(w1.charAt(i)))
            {
                return false;
            }

            if(w1.charAt(i)!=w2.charAt(i)){
                countJumbled ++;
            }
        }

        return isJumbledWhenSizeThree(w1.length(), countJumbled) ||
                hasUpToThreeThirdChanges(w2.length(), countJumbled);
    }

    /**
     * Check if words are jumbled when string size is greater than 3.
     * @param stringLength the size of the string
     * @param countJumbled the number of jumbled letters
     * @return true if string size greater than 3 letters and the number of jumbled letters
     * is up to 2/3 of the letters, returns false otherwise.
     */
    private static boolean hasUpToThreeThirdChanges(int stringLength, int countJumbled){
        return (countJumbled > 0) && (countJumbled <= (2* stringLength)/3.0);
    }

    /**
     * Check if words are jumbled when string size is less than 3.
     * @param stringLength the size of the string
     * @param countJumbled the number of jumbled letters
     * @return true if the string has three letters and any jumbled letter.
     */
    private static boolean isJumbledWhenSizeThree(int stringLength, int countJumbled){
        return (countJumbled ==2) && (stringLength == 3);
    }
}
