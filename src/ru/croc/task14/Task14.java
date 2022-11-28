package ru.croc.task14;

import java.util.*;

public class Task14 implements BlackListFilter2<List<String>,String>{
    @Override
    public boolean filterPred(String comments, List<String> blackList) {
        for(String str: blackList){
            if(comments.contains(str)){
                return true;
            }
        }
        return false;
    }

}
