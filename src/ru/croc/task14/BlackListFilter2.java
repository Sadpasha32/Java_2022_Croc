package ru.croc.task14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface BlackListFilter2<T> {
    default List<T> filterComments(Iterable<T> comments, Predicate<T> filterPred) {
        List<T> resComments = new ArrayList<>();
        for (T comment : comments) {
            if (!filterPred.test(comment)) {
                resComments.add(comment);
            }
        }
        return resComments;
    }
}