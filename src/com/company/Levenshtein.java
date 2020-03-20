package com.company;

import java.util.Arrays;

import static java.lang.Math.min;

public class Levenshtein {
    private String test_name_record;
    private int test_name_rec_len;

    public Levenshtein(String test_string) {
        this.test_name_record = test_string;
        this.test_name_rec_len = test_string.length();
    }

    private int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }

    public int calculate(String name_record) {
        int[][] dp = new int[name_record.length() + 1][this.test_name_rec_len + 1];

        for (int i = 0; i <= name_record.length(); i++) {
            for (int j = 0; j <= this.test_name_rec_len; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(name_record.charAt(i - 1), this.test_name_record.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[name_record.length()][this.test_name_rec_len];
    }
}
