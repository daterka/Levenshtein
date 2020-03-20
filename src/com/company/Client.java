package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;

        String file_path = args[0];
        String test_record = args[1];

        String regex = "\\s+";

        int line_number = 0;

        try {
            inputStream = new FileInputStream(file_path);
            sc = new Scanner(inputStream, "UTF-8");
            int levenshtein_min_record_diff = -1;
            int line_counter = 0;
            while(sc.hasNextLine()){
                String name_record = sc.nextLine();
                line_counter+=1;
//                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~" + line_counter);
                int levenshtein_record_diff = 0;
                for (String test_string : test_record.split(regex)) {
                    int levenstein_string_diff = -1;
                    for (String name_string : name_record.split(regex)) {
//                        System.out.println(test_string + " " + name_string);
                        int levenstein_diff = Levenshtein.calculate(test_string, name_string);
                        if(levenstein_string_diff == -1){
                            levenstein_string_diff = levenstein_diff;
                        }
                        else if (levenstein_string_diff > levenstein_diff){
                            levenstein_string_diff = levenstein_diff;
                        }
//                        System.out.println(" ############  " + levenstein_diff);
//                        System.out.println(" $$$$$$$$$$$$  " + levenstein_string_diff);
                    }
                    levenshtein_record_diff +=  levenstein_string_diff;
//                    System.out.println("-> : " + levenshtein_record_diff);


                }

                if(levenshtein_min_record_diff == -1){
                    levenshtein_min_record_diff = levenshtein_record_diff;
                }
                else if(levenshtein_min_record_diff > levenshtein_record_diff){
                    levenshtein_min_record_diff = levenshtein_record_diff;
                    line_number = line_counter;
                }
                System.out.println(levenshtein_record_diff);
                levenshtein_record_diff = 0;

                if(levenshtein_min_record_diff == 0){
                    break;
                }

            }

            if (sc.ioException() != null) {
                throw sc.ioException();
            }

        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

        System.out.println("Linia : " + line_number);

        System.exit(0);
    }
}
