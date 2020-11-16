package com.cr.basic.code.pcr;

import com.cr.basic.code.pcr.model.Books;
import com.cr.basic.code.pcr.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.print.Book;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class PcrApplication {

    public static void main(String[] args) {
        List<Books> bookList = new ArrayList<>();
        bookList.add(new Books("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Books("The Two Towers", 1954, "0345339711"));
        List<Books> bookList2 = new ArrayList<>();
        bookList2.add(new Books("The Return of the King", 1955, "0618129111"));
        bookList2.add(new Books(" of the King", 1956, "0618129112"));

        Map<String, List> map = new HashMap();
        map.put("id", bookList);
        map.put("id2", bookList2);

        map.forEach((k, v) -> {
            List collect = (List) v.stream().sorted(Comparator.comparing(Books::getIsbn)).collect(Collectors.toList());
            System.out.println("sussceed");
        });

        List<String> collect = bookList.stream().map(s -> s + "pcr").collect(Collectors.toList());
        List<String> strings = Arrays.asList("ab", "s", "bc", "cd", "abcd", "sd", "jkl");
        //转换成Map集合
        Map<String, Object> stringObjectMap = strings.stream()
            .collect(Collectors.toMap(k -> k, v -> v));
        System.out.println(11);
        System.out.println(UUID.randomUUID());

    }

    public static Map<String, Books> listToMap(List<Books> books) {
        Map<String, Books> collect = books.stream().collect(Collectors.toMap(Books::getIsbn, a -> a, (k1, k2) -> k1));
        return collect;
    }
}
