package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String file_path = "C:\\Users\\dtms\\Documents\\names.txt";
        File name_record_file = new File(file_path);

        Levenshtein levenshtein = new Levenshtein("Ala Ola Roman");

        try {
            Scanner scanner = new Scanner(name_record_file);

            while(scanner.hasNextLine()){
                String name_record = scanner.nextLine();
                System.out.println(levenshtein.calculate(name_record));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
