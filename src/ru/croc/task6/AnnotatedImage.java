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
        for (Annotation annotation : annotations) {
            if (annotation.getFigure().containsPoint(x, y)) {
                return annotation;
            }
        }
        throw new Exception("Нет подходящей аннотации!");
    }
    public Annotation findByLabel(String label) throws Exception{
        for (Annotation annotation : annotations) {
            if (annotation.getName().contains(label)) {
                return annotation;
            }
        }
        throw new Exception("Нет подходящей аннотации!");
    }
}

