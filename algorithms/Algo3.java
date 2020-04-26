/**
 * 3. Check words with typos:
 * 
 * There are three types of typos that can be performed on strings: insert a character,
 * remove a character, or replace a character. Given two strings, write a function to
 * check if they are one typo (or zero typos) away.
 * 
 * Examples:
 * pale, ple ­> true
 * pales, pale ­> true
 * pale, bale ­> true
 * pale, bake ­> false
 * 
 * Solution:
 * The description of the problem is very similar to the description of Levenshtein Distance.
 * An algorithm that measures the number of changes that needs to be performed in one string
 * in other to transform it in a second string, where each change is a single character modification 
 * (deletion, insertion or substitution).
 * 
 * Time complexity O(n*m) where n is the size of the first string and m is the size of the second.
 */

public final class Algo3 {
    /**
     * Check if String w2 is a typo of String w1.
     * @param w1
     * @param w2
     * @return
     */
    public boolean isTypo(final String w1, final String w2){
        return countTypos(w1, w2) <= 1;
    }

    /**
     * Levenshtein Distance algorithm
     * @param w1
     * @param w2
     * @return
     */
    public int countTypos(final String w1, final String w2) {
        
        int[] distances1 = new int[w2.length() + 1];
        int[] distances2 = new int[w2.length() + 1];

        for (int j = 0, size = w2.length(); j <= size; j++){
            distances1[j] = j;
        }
        
        for(int i = 0, size1 = w1.length(); i < size1; i++) {
            distances2[0] = i + 1;
            for(int j = 0, size2 = w2.length(); j < size2; j++) {
                final int delCost = distances1[j + 1] + 1;
                final int insCost = distances2[j] + 1;
                final int subsCost = distances1[j] +  (w1.charAt(i) == w2.charAt(j) ? 0 : 1);

                distances2[j+1] = min(delCost, insCost, subsCost);
            }
            final int[] aux = distances1;
            distances1 = distances2;
            distances2 = aux;
        }

        return distances1[w2.length()];
    }

    private static int min(int... values) {
        int vMin = values[0];
        for(int i = 1; i < values.length; i++) {
            if (vMin > values[i]) {
                vMin = values[i];
            }
        }
        return vMin;
    }

    public static void main(String... args){
        printTypos("pale", "ple");
        printTypos("pales", "pale"); 
        printTypos("pale", "bale");
        printTypos("pale", "bake");
    }
    
    private static void printTypos(final String w1, final String w2) {
        final Algo3 algo3 = new Algo3();
        System.out.println(String.format("%s, %s, %b", w1, w2, algo3.isTypo(w1, w2)));
    }
}