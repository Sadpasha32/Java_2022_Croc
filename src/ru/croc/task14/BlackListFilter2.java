package ru.croc.task14;

import java.util.ArrayList;
import java.util.List;

public interface BlackListFilter2<T1 extends Iterable<T>, T> {
    boolean filterPred(T comments, T1 banWords);

    default List<T> filterComments(T1 comments, T1 banWords) {
        List<T> resComments = new ArrayList<>();
        for (T comment : comments) {
            if (!filterPred(comment, banWords)) {
                resComments.add(comment);
            }
        }
        return resComments;
    }
}
