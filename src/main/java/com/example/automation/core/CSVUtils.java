package com.example.automation.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    /**
     * Reads a CSV file from the classpath and returns data as Object[][]
     * (ignores the header row).
     *
     * @param resourcePath path in classpath, e.g. "testdata/login-data.csv"
     * @return Object[][] for TestNG DataProvider
     */
    public static Object[][] readCsvData(String resourcePath) {
        List<String[]> records = new ArrayList<>();

        try (InputStream is = CSVUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new RuntimeException("CSV resource not found: " + resourcePath);
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8))) {

                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        // skip header
                        isFirstLine = false;
                        continue;
                    }
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    String[] parts = line.split(",");
                    records.add(parts);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + resourcePath, e);
        }

        // Convert List<String[]> -> Object[][]
        Object[][] data = new Object[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            data[i] = records.get(i);
        }
        return data;
    }
}
