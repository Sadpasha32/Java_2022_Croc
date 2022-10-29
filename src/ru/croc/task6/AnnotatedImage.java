package ru.croc.task6;


class AnnotatedImage {

    private final String imagePath;

    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public Annotation findByPoint(int x, int y) throws Exception{
        Annotation res = new Annotation();
        for (Annotation annotation : annotations) {
            if (annotation.getFigure().containsPoint(x, y)) {
                res = annotation;
                break;
            }
        }
        if (res.getName().equals("")){
            throw new Exception("Нет подходящей аннотации!");
        }
        return res;
    }
    public Annotation findByLabel(String label) throws Exception{
        Annotation res = new Annotation();
        for (Annotation annotation : annotations) {
            if (annotation.getName().contains(label)) {
                res = annotation;
                break;
            }
        }
        if (res.getName().equals("")){
            throw new Exception("Нет подходящей аннотации!");
        }
        return res;
    }
}

