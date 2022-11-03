package ru.croc.task6;

public class Task6 {
    public static void main(String[] args) throws Exception {
        AnnotatedImage annotatedImage;
        Annotation[] annotations = new Annotation[]{new Annotation(new Rectangle(100,100,150,200),"Car"),
                new Annotation(new Circle(100,100,10),"Tree")};
        annotatedImage = new AnnotatedImage("",annotations);
        int x = 120;
        int y = 120;
        String lable = "ree";
        try {
            System.out.println(annotatedImage.findByPoint(x,y));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        try {
            System.out.println(annotatedImage.findByPoint(-1000,-1000));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        try {
            System.out.println(annotatedImage.findByLabel(lable));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(annotations[0].getFigure());
        annotations[0].getFigure().move(100,50);
        System.out.println(annotations[0].getFigure());
      }
}


