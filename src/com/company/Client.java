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
        String file_path = null;

        file_path = "C:\\Users\\dtms\\Documents\\names.txt";
        File name_record_file = new File(file_path);

        try {
            inputStream = new FileInputStream(file_path);
            sc = new Scanner(inputStream, "UTF-8");
            Levenshtein levenshtein = new Levenshtein("Ala Ola Roman");

            while(sc.hasNextLine()){
                String name_record = sc.nextLine();
                System.out.println(levenshtein.calculate(name_record));
            }

            if (sc.ioException() != null) {
                throw sc.ioException();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        

    }
}
