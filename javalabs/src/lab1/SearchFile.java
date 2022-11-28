package lab1;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.util.Scanner;

public class SearchFile {
    private final File file;

    public SearchFile(File file) {
        this.file = file;
    }

    public void search(String word) {
        int counter = 0;

        try (Scanner sc = new Scanner(Files.newInputStream(file.toPath()))) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();

                if (str.contains(word + "(") || str.contains(word + " (")) counter++;
            }

            System.out.println("File '" + file.getPath() + "' has the operator '" + word + "' " + counter + " times.");
        } catch (AccessDeniedException e) {
            System.out.println("Access denied!");
        } catch (IOException e) {
            System.out.println("Something wrong with IO!");
        }
    }
}
