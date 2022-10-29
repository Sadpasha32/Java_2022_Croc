package ru.croc.task5;

public class Task5 {
    public static void main(String[] args) throws Exception {
        Annotation[] annotations = new Annotation[]{new Annotation(new Rectangle(100,100,150,200),"Car"),
        new Annotation(new Circle(100,100,10),"Tree")};
        for(Annotation annotation: annotations){
            System.out.println(annotation);
        }
    }
}


