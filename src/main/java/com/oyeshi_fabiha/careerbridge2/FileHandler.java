package com.oyeshi_fabiha.careerbridge2;

import java.io.*;
import java.util.Scanner;

public class FileHandler {
    private static final String STUDENT_FILE = "students.txt";

    public static void saveStudent(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verifyLogin(String identifier, String password) {
        File file = new File(STUDENT_FILE);
        if (!file.exists()) return false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\|");

                // Index 0 = Email, Index 1 = Password
                if (parts.length >= 2) {
                    String savedEmail = parts[0].trim();
                    String savedPassword = parts[1].trim();

                    if (savedEmail.equalsIgnoreCase(identifier) && savedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
