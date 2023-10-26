// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.*;
import java.util.*;
import java.util.Properties;
import java.io.File;
public class Main {
    public static void main(String[] args) throws Exception {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        FileReader reader = new FileReader("db.properties");
        Properties p = new Properties();
        p.load(reader);
        System.out.println(p.getProperty("username"));
        System.out.println(p.getProperty("password"));
    }
}