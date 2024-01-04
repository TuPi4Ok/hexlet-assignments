package exercise;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String file1, String file2, String file3) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            List<String> list;
            try {
                list = Files.readAllLines(Path.of(file1), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StringBuilder result = new StringBuilder();
            for (String l : list)
                result.append(l);
            return result.toString();
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });;

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            List<String> list;
            try {
                list = Files.readAllLines(Path.of(file2), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StringBuilder result = new StringBuilder();
            for (String l : list)
                result.append(l);
            return result.toString();
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });;

        CompletableFuture<String> future3 = future1.thenCombine(future2, (string1, string2) -> {
            var result = string1 + string2;
            try {
                Files.write(Path.of(file3), result.lines().collect(Collectors.toList()), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return result;
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });;
        return future3;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("/home/ivan/Hexlet/hexlet-assignments/java-advanced-ru/asynchrony/src/main/resources/file1.txt", "/home/ivan/Hexlet/hexlet-assignments/java-advanced-ru/asynchrony/src/main/resources/file2.txt", "/home/ivan/Hexlet/hexlet-assignments/java-advanced-ru/asynchrony/src/main/resources/file3.txt");
        // END
    }
}

