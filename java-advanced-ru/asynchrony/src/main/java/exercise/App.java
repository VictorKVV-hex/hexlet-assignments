package exercise;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String file1, String file2, String file_result) {

        CompletableFuture<String> string1 = CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(file1).toAbsolutePath().normalize();
                String file = Files.readString(path);
                System.out.println("First file read = " + file);
                return file;
//                return Files.readString(Paths.get(file1).toAbsolutePath().normalize());
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        });

        CompletableFuture<String> string2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                Path path = Paths.get(file2).toAbsolutePath().normalize();
                String file = Files.readString(path);
                System.out.println("Second file read= " + file);
                return file;
//                return Files.readString(Paths.get(file1).toAbsolutePath().normalize());
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        });

        CompletableFuture<String> unionString = string1.thenCombine(string2, (f1, f2) -> {
            try {
                if (Files.notExists(Paths.get(file_result).toAbsolutePath().normalize())) {
                    System.out.println("Result file create");
                    Files.createFile(Paths.get(file_result).toAbsolutePath().normalize());
                }

//                FileWriter writer = new FileWriter(file_result);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file_result));
                writer.write(f1);
//                writer.newLine();
                writer.write(f2);
                writer.close();
                String file = Files.readString(Paths.get(file_result).toAbsolutePath().normalize());
                System.out.println("Writing to result file completed = "  + file);
            } catch (IOException e) {
                System.out.println("Result file not found");
                throw new RuntimeException(e);
            }

            String result = f1 + f2;
            System.out.println("Sum of files=" + result);

            return result;
        }).exceptionally(ex -> { // Если при работе задач возникли исключения их можно обработать в методе exceptionally
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
        return unionString;
    }

        // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles("src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/dest.txt");
        System.out.println("Result= " + result.toString());
        // END
    }
}

