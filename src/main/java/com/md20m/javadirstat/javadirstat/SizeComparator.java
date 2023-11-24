package com.md20m.javadirstat.javadirstat;
import java.util.Comparator;


public class SizeComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        // Split the strings into the size value and unit of measurement.
        String[] parts1 = o1.split(",");
        String[] parts2 = o2.split(",");

        // Check if the strings are valid before parsing them as doubles.
        if (parts1.length != 2 || parts2.length != 2) {
            throw new IllegalArgumentException("Invalid size format: " + o1 + ", " + o2);
        }

        // Parse the size values to doubles and compare them.
        double size1 = Double.parseDouble(parts1[0]);
        double size2 = Double.parseDouble(parts2[0]);

        if (size1 < size2) {
            return -1;
        } else if (size1 > size2) {
            return 1;
        } else {
            // If the size values are equal, compare the units of measurement.
            String unit1 = parts1[1];
            String unit2 = parts2[1];

            if (unit1.equals("B") && unit2.equals("KB")) {
                return -1;
            } else if (unit1.equals("KB") && unit2.equals("MB")) {
                return -1;
            } else if (unit1.equals("MB") && unit2.equals("GB")) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}










