package com.oyeshi_fabiha.careerbridge2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    private static final String STUDENT_FILE  = "students.txt";
    private static final String ALUMNI_FILE   = "alumni.txt";
    private static final String STORIES_FILE  = "stories.txt";
    private static final String JOBS_FILE     = "jobs.txt";
    private static final String MESSAGES_FILE = "messages.txt";

    // ─── STUDENT ────────────────────────────────────────────────────────────────

    public static void saveStudent(String data) {
        appendLine(STUDENT_FILE, data);
    }

    public static boolean verifyLogin(String emailOrUsername, String password) {
        for (String line : readLines(STUDENT_FILE)) {
            String[] p = line.split("\\|");
            if (p.length >= 2 && p[0].trim().equals(emailOrUsername) && p[1].trim().equals(password))
                return true;
        }
        return false;
    }

    public static String getStudentName(String emailOrUsername) {
        for (String line : readLines(STUDENT_FILE)) {
            String[] p = line.split("\\|");
            if (p.length >= 3 && p[0].trim().equalsIgnoreCase(emailOrUsername))
                return p[2].trim();
        }
        return "Student";
    }

    // ─── ALUMNI ─────────────────────────────────────────────────────────────────

    public static void saveAlumni(String data) {
        appendLine(ALUMNI_FILE, data);
    }

    public static boolean verifyAlumniLogin(String username, String password) {
        for (String line : readLines(ALUMNI_FILE)) {
            String[] p = line.split("\\|");
            if (p.length >= 2 && p[0].trim().equals(username) && p[1].trim().equals(password))
                return true;
        }
        return false;
    }

    public static String getAlumniName(String username) {
        for (String line : readLines(ALUMNI_FILE)) {
            String[] p = line.split("\\|");
            if (p.length >= 3 && p[0].trim().equals(username))
                return p[2].trim();
        }
        return "Alumni";
    }

    public static List<Alumni> getAllAlumni() {
        List<Alumni> list = new ArrayList<>();
        for (String line : readLines(ALUMNI_FILE)) {
            String[] p = line.split("\\|");
            if (p.length >= 3) {
                Alumni a = new Alumni();
                a.username = p[0].trim();
                a.name     = p.length > 2 ? p[2].trim() : "";
                a.email    = p.length > 3 ? p[3].trim() : "";
                a.year     = p.length > 4 ? p[4].trim() : "";
                a.major    = p.length > 5 ? p[5].trim() : "";
                a.company  = p.length > 6 ? p[6].trim() : "";
                a.position = p.length > 7 ? p[7].trim() : "";
                list.add(a);
            }
        }
        return list;
    }

    public static List<Alumni> searchAlumni(String query) {
        String q = query.toLowerCase().trim();
        List<Alumni> results = new ArrayList<>();
        for (Alumni a : getAllAlumni()) {
            if (a.name.toLowerCase().contains(q)
                    || a.company.toLowerCase().contains(q)
                    || a.position.toLowerCase().contains(q)
                    || a.major.toLowerCase().contains(q)
                    || a.year.contains(q)) {
                results.add(a);
            }
        }
        return results;
    }

    // ─── STORIES ────────────────────────────────────────────────────────────────

    public static void saveStory(String authorName, String storyText) {
        appendLine(STORIES_FILE, authorName + "§" + storyText);
    }

    public static List<String[]> getAllStories() {
        List<String[]> list = new ArrayList<>();
        for (String line : readLines(STORIES_FILE)) {
            String[] parts = line.split("§", 2);
            if (parts.length == 2)
                list.add(parts);
        }
        return list;
    }

    // ─── JOBS ───────────────────────────────────────────────────────────────────

    public static void saveJob(String data) {
        appendLine(JOBS_FILE, data);
    }

    public static List<Job> getAllJobs() {
        List<Job> list = new ArrayList<>();
        for (String line : readLines(JOBS_FILE)) {
            String[] p = line.split("\\|");
            if (p.length >= 5) {
                Job j = new Job();
                j.title    = p[0].trim();
                j.company  = p[1].trim();
                j.location = p[2].trim();
                j.salary   = p[3].trim();
                j.postedBy = p[4].trim();
                list.add(j);
            }
        }
        return list;
    }

    public static List<Job> searchJobs(String query) {
        String q = query.toLowerCase().trim();
        List<Job> results = new ArrayList<>();
        for (Job j : getAllJobs()) {
            if (j.title.toLowerCase().contains(q)
                    || j.company.toLowerCase().contains(q)
                    || j.location.toLowerCase().contains(q)) {
                results.add(j);
            }
        }
        return results;
    }

    // ─── MESSAGES ───────────────────────────────────────────────────────────────
    // Format: sender§receiver§timestamp§message

    public static void saveMessage(String sender, String receiver, String message) {
        String timestamp = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        appendLine(MESSAGES_FILE, sender + "§" + receiver + "§" + timestamp + "§" + message);
    }

    public static List<Message> getConversation(String userA, String userB) {
        List<Message> list = new ArrayList<>();
        for (String line : readLines(MESSAGES_FILE)) {
            String[] p = line.split("§", 4);
            if (p.length == 4) {
                String sender   = p[0].trim();
                String receiver = p[1].trim();
                if ((sender.equalsIgnoreCase(userA) && receiver.equalsIgnoreCase(userB))
                        || (sender.equalsIgnoreCase(userB) && receiver.equalsIgnoreCase(userA))) {
                    Message m = new Message();
                    m.sender    = sender;
                    m.receiver  = receiver;
                    m.timestamp = p[2].trim();
                    m.text      = p[3].trim();
                    list.add(m);
                }
            }
        }
        return list;
    }

    /** Return list of unique contact usernames who have messaged or been messaged by this user */
    public static List<String> getContacts(String username) {
        List<String> contacts = new ArrayList<>();
        for (String line : readLines(MESSAGES_FILE)) {
            String[] p = line.split("§", 4);
            if (p.length == 4) {
                String sender   = p[0].trim();
                String receiver = p[1].trim();
                if (sender.equalsIgnoreCase(username) && !contacts.contains(receiver))
                    contacts.add(receiver);
                else if (receiver.equalsIgnoreCase(username) && !contacts.contains(sender))
                    contacts.add(sender);
            }
        }
        return contacts;
    }

    // ─── UTIL ───────────────────────────────────────────────────────────────────

    private static void appendLine(String filename, String data) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(filename, true))) {
            w.write(data);
            w.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return lines;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String l = sc.nextLine().trim();
                if (!l.isEmpty()) lines.add(l);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // ─── DATA MODELS ────────────────────────────────────────────────────────────

    public static class Alumni {
        public String username, name, email, year, major, company, position;
    }

    public static class Job {
        public String title, company, location, salary, postedBy;
    }

    public static class Message {
        public String sender, receiver, timestamp, text;
    }
}