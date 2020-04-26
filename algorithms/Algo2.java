/**
 * 2. Check words with jumbled letters :
 * Our brain can read texts even if letters are jumbled, like the following sentence: 
 * “Yuo cna porbalby raed tihs esaliy desptie teh msispeillgns.” Given two strings, write a
 * method to decide if one is a partial­permutation of the other. Consider a 
 * partial­permutation only if:
 * - The first letter hasn’t changed place 
 * - If word has more than 3 letters, up to 2/3 of the letters have changed place
 * Examples:
 * you, yuo ­> true
 * probably, porbalby ­> true
 * despite, desptie ­> true
 * moon, nmoo ­> false
 * misspellings, mpeissngslli ­> false
 */
public final class Algo2 {

    public boolean isJumbled(String a, String b) {
        // strings must have the same size
        // string must have the same initial char
        if(a.length()!= b.length() || a.charAt(0)!=b.charAt(0)){
            return false;
        }

        int countJumbled = 0;
        for(int i = 1; i < a.length(); i++){
            if(a.charAt(i)!=b.charAt(i)){
                countJumbled ++;
            }
        }
        
        // At least one change

        return isJumbledWhenSizeThree(a.length(), countJumbled) || 
            hasUpToThreeThirdChanges(a.length(), countJumbled);
    }

    private static boolean hasUpToThreeThirdChanges(int stringLenght, int countJumbled){
        return (countJumbled > 0) && (countJumbled <= (2* stringLenght)/3.0);
    }

    private static boolean isJumbledWhenSizeThree(int stringLenght, int countJumbled){
        return (countJumbled > 0) && (stringLenght == 3);
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