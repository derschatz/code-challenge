
/**
 * 2. Check words with jumbled letters : Our brain can read texts even if
 * letters are jumbled, like the following sentence: “Yuo cna porbalby raed tihs
 * esaliy desptie teh msispeillgns.” Given two strings, write a method to decide
 * if one is a partial­permutation of the other. Consider a partial­permutation
 * only if: - The first letter hasn’t changed place - If word has more than 3
 * letters, up to 2/3 of the letters have changed place Examples: you, yuo ­>
 * true probably, porbalby ­> true despite, desptie ­> true moon, nmoo ­> false
 * misspellings, mpeissngslli ­> false
 */

import java.util.HashSet;
import java.util.Set;

public final class Algo2 {

    public boolean isJumbled(String w1, String w2) {
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

    private static boolean hasUpToThreeThirdChanges(int stringLength, int countJumbled){
        return (countJumbled > 0) && (countJumbled <= (2* stringLength)/3.0);
    }

    private static boolean isJumbledWhenSizeThree(int stringLength, int countJumbled){
        return (countJumbled > 0) && (stringLength == 3);
    }

    public static void main(String... args) {
        printJumbled("you", "yuo");
        printJumbled("you", "you");
        printJumbled("probably", "porbalby");
        printJumbled("probably", "probably");
        printJumbled("despite", "desptie");
        printJumbled("moon", "nmoo");
        printJumbled("misspellings", "mpeissngslli");
    }

    private static void printJumbled(String a, String b){
        final Algo2 algo2 = new Algo2();
        System.out.println(String.format("%s, %s --> %b", a, b, algo2.isJumbled(a, b)));
    }
}
