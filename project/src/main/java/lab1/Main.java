package lab1;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter directory: ");
        String dir = sc.nextLine();

        while (!new File(dir).isDirectory()) {
            System.out.println("Wrong directory! Please try again\n");
            System.out.print("Enter directory: ");
            dir = sc.nextLine();
        }

        System.out.println("Keyword: 'for'");
        String word = "for";
        System.out.println("========================================");

        ExecutorService pool = Executors.newCachedThreadPool();
        ProcessFiles running = new ProcessFiles(new File(dir), word, pool);
        Future<Integer> process = pool.submit(running);

        try {
            System.out.println(process.get() + " files processed.");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }
}
