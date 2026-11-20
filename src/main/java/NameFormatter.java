import java.util.ArrayList;

public class NameFormatter {
    private String firstName;
    private String lastName;
    private String middleName;
    private String prefix;
    private String suffix;

    private NameFormatter(String lastName, String Prefix, String firstName, String middleName, String suffix) {
        this.lastName = lastName;
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleName = middleName;
        this.suffix = suffix;
    }

    private NameFormatter(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public static String format(String firstName, String lastName) {
        NameFormatter name = new NameFormatter(firstName, lastName);
        return name.display("firstAndLast");
    }

    public static String format(String prefix,
                                String firstName,
                                String middleName,
                                String lastName,
                                String suffix) {
        NameFormatter name = new NameFormatter(lastName, prefix, firstName, middleName, suffix);
        return name.display("full");
    }

    public static String format(String fullName) {
        String first;
        String last;
        String middle;
        String prefix;
        String suffix;
        String result = "";

        fullName = fullName.trim();
        String[] tokens = fullName.split(" ");

        int count = tokens.length;

        switch (count) {
            case 2 -> {
                first = tokens[0];
                last = tokens[tokens.length - 1];
                result = format(first, last);
            }
            case 3 -> {
                first = tokens[0];
                middle = tokens[1];
                last = tokens[2];
                result = format(null, first, middle, last, null);
            }
            case 4 -> {
                // Check if first token looks like a prefix (ends with .)
                if (tokens[0].endsWith(".")) {
                    prefix = tokens[0];
                    first = tokens[1];
                    middle = tokens[2];
                    last = tokens[3];
                    result = format(prefix, first, middle, last, null);
                } else {
                    first = tokens[0];
                    middle = tokens[1];
                    last = tokens[2];
                    suffix = tokens[3];
                    result = format(null, first, middle, last, suffix);
                }
            }
            case 5 -> {
                prefix = tokens[0];
                first = tokens[1];
                middle = tokens[2];
                last = tokens[3].replace(",", ""); // Remove comma
                suffix = tokens[4];
                result = format(prefix, first, middle, last, suffix);
            }
        }
        return result;
    }

        public String display (String option){
            String result = "";
            switch (option) {
                case "firstAndLast" -> result = this.lastName + ", " + this.firstName;
                case "full" ->
                        result = this.lastName + ", " + this.prefix + ", " + this.firstName + " " + this.middleName + ", " + this.suffix;
            }
            return result;
        }
    }