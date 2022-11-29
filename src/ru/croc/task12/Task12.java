package ru.croc.task12;

import java.util.*;

public class Task12 implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        int indexOfComment = 0;
        while(comments.size()>0 && indexOfComment < comments.size() ){
            for(String blackListWord: blackList){
                if(comments.get(indexOfComment).contains(blackListWord)){
                    comments.remove(indexOfComment);
                    indexOfComment--;
                    break;
                }
            }
            indexOfComment++;
        }
    }

}
