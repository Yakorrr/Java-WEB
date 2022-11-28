package lab1;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

public class ProcessFiles implements Callable<Integer> {
    private final File startDir;
    private final String word;
    private final ExecutorService pool;

    public ProcessFiles(File startDir, String word, ExecutorService pool) {
        this.startDir = startDir;
        this.word = word;
        this.pool = pool;
    }

    @Override
    public Integer call() {
        int counter = 0;

        try {
            File[] files = startDir.listFiles();
            List<Future<Integer>> result = new ArrayList<>();

            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        ProcessFiles process = new ProcessFiles(f, word, pool);
                        Future<Integer> res = pool.submit(process);
                        result.add(res);
                    } else if (f.getName().endsWith(".c")) {
                        new SearchFile(f).search(word);
                        counter++;
                    }
                }
            }

            for (Future<Integer> res : result) {
                counter += res.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return counter;
    }
}
