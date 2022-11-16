package ru.croc.task10;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ThreadBruteForcePassword implements Runnable {
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private Long startPoint;
    private final Long endPoint;
    private final String hash;
    public static WorkStatus workStatus = new WorkStatus();
    public static String foundedPassword;

    public static String makeString(Long x) { // Тут я как бы перевожу число из 26сс в 10. а - 0, b - 1, c - 2 и т. д.
        char[] currentString = "aaaaaaa".toCharArray();
        int i = 0;
        while (x > 0) {
            currentString[6 - i] = (char) (97 + x % 26);
            x = x / 26;
            i++;
        }
        return new String(currentString);
    }

    public String nextPassword(String currPass) {
        char[] password = currPass.toCharArray();
        for (int i = 6; i >= 0; i--) {
            if (currPass.charAt(i) != 'z') {
                password[i] = (char)(currPass.charAt(i)+1);
                return new String(password);
            }
            password[i] = 'a';
        }
        return new String(password);
    }
    public ThreadBruteForcePassword(Long startPoint, Long endPoint, String hash) { // Передаю в конструктор откуда нужно начинать считать и до куда + хэш
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.hash = hash;
    }

    @Override
    public void run() {
        String currPassword = makeString(startPoint);
        while (startPoint < endPoint && !workStatus.isCompleted()) {
            if (hashPassword(currPassword).equals(hash)) {
                System.out.println("Found password: " + currPassword);
                foundedPassword = currPassword;
                workStatus.markCompleted();
            }
            currPassword = nextPassword(currPassword);
            startPoint++;
        }
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    private static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }

}
