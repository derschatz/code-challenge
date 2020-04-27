package com.example.searchapp.utils;

import androidx.annotation.NonNull;

public final class TypoUtils {

    private TypoUtils() {}
    /**
     * Check if String w2 is a typo of String w1.
     * @param w1
     * @param w2
     * @return
     */
    public static boolean isTypo(@NonNull final String w1, @NonNull final String w2){
        return countTypos(w1, w2) <= 1;
    }

    /**
     * Levenshtein Distance algorithm
     * @param w1 a string
     * @param w2 as string
     * @return the Levenhstein distance between w1 and w2.
     */
    private static int countTypos(@NonNull final String w1, @NonNull final String w2) {

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

    /**
     * Returns the minimum value from a collection of values.
     * @param values collection of integer values
     * @return minimum value
     */
    private static int min(@NonNull int... values) {
        int vMin = values[0];
        for(int i = 1; i < values.length; i++) {
            if (vMin > values[i]) {
                vMin = values[i];
            }
        }
        return vMin;
    }
}
