package ru.croc.task12;

import java.util.List;
import java.util.Set;

public class Task12 implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        int indexOfComment = 0;
        int flagOfDelete;
        while(comments.size()>0 && indexOfComment < comments.size() ){
            flagOfDelete = 0;
            for(String blackListWord: blackList){
                if(comments.get(indexOfComment).contains(blackListWord)){
                    comments.remove(indexOfComment);
                    flagOfDelete = 1;
                    break;
                }
            }
            if(flagOfDelete == 0){
                indexOfComment++;
            }
        }
    }
}
