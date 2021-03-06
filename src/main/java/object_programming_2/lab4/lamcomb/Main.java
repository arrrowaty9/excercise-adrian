package object_programming_2.lab4.lamcomb;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws Exception {
        Function<String, List<String>> flines = getFlinesFunction();
        Function<List<String>, String> join = Main::getStringsFunction;
        Function<String, List<Integer>> collectInts = getCollectIntsFunction();
        Function<List<Integer>, Integer> sum = getSumFunction();

        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        String text = fileConv.convertBy(flines, join);
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

        System.out.println(lines);
        System.out.println(text);
        System.out.println(ints);
        System.out.println(sumints);

        List<String> arglist = Arrays.asList(args);
        InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
        sumints = slistConv.convertBy(join, collectInts, sum);
        System.out.println(sumints);
    }

    private static Function<String, List<String>> getFlinesFunction() {
        return (filePath) -> {
            try {
                Path path = Paths.get(filePath);
                return Files.readAllLines(path);
            } catch (IOException e) {
                return Lists.newArrayList();
            }
        };
    }

    private static Function<String, List<Integer>> getCollectIntsFunction() {
        return (s) -> {
            s = s.replaceAll("[^0-9]+", " ");
            Scanner scanner = new Scanner(s);
            List<Integer> numbers = new ArrayList<>();
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
            return numbers;
        };
    }

    private static String getStringsFunction(List<String> strings) {
        return strings.stream()
                .collect(Collectors.joining());
    }

    static Function<List<Integer>, Integer> getSumFunction() {
        return ints -> ints.stream().mapToInt(Integer::intValue).sum();
    }
}
