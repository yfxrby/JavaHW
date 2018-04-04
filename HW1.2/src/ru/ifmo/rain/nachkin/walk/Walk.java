package ru.ifmo.rain.nachkin.walk;

import java.io.*;

public class Walk {

    public static void main(String[] args) {
        String in = "";
        String out = "";


        try {
            if (args.length != 2) {
                System.out.println("incorrect number arguments");
                return;
            }

            try {
                in = args[0];
            } catch (NullPointerException e) {
                System.out.println("incorrect input");
                return;
            }
            out = args[1];
        } catch (NullPointerException e) {
            System.out.println("incorrect output");
            return;
        }

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(in), "UTF-8"));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), "UTF-8"))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {

                int hashSum = 0x811c9dc5;
                try (InputStream temp = new FileInputStream(line)) {
                    int len, FNV_32_PRIME = 0x01000193;
                    byte[] buf = new byte[1024];
                    while ((len = temp.read(buf)) >= 0) {
                        for (int i = 0; i < len; i++) {
                            hashSum = (hashSum * FNV_32_PRIME) ^ (buf[i] & 0xff);
                        }
                    }
                } catch (IOException e) {
                    hashSum = 0;
                } finally {
                    writer.write(String.format("%08x", hashSum) + " " + line + "\n");
                }

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
