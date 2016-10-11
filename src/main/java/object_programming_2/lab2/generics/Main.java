package object_programming_2.lab2.generics;
import java.util.*;

import static object_programming_2.lab2.generics.ListCreator.collectFrom;

public class Main {

    public Main() {
        List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
        System.out.println(test1(src1));

        List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv" );
        System.out.println(test2(src2));
    }

    public List<Integer> test1(List<Integer> src) {
        Selector<Integer> sel = new Selector<Integer>() {
            @Override
            public boolean select(Integer number) {
                return number < 10;
            }
        };
        Mapper<Integer> map = new Mapper<Integer>() {
            @Override
            public Integer map(Integer number) {
                return number + 10;
            }
        };

        return collectFrom(src).when(sel).mapEvery(map);
    }

    public List<Integer> test2(List<String> src) {
        Selector<String> sel = new Selector<String>() {
            @Override
            public boolean select(String text) {
                return text.length() > 3;
            }
        };
        Mapper<String> map = new Mapper<String>() {
            @Override
            public Integer map(String text) {
                return text.length() + 10;
            }
        };

        return collectFrom(src).when(sel).mapEvery(map);
    }

    public static void main(String[] args) {
        new Main();
    }

}
