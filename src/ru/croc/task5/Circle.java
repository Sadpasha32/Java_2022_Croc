package ru.croc.task5;

class Circle extends Figure{
    int X0;
    int Y0;
    int R;
    @Override
    public String toString() {
        return "C ("+X0+", "+Y0+"), "+R;
    }
    public Circle(int x0, int y0, int r) throws Exception {
        if(r < 0){
            throw new Exception("Неверно задан радиус!");
        }
        X0 = x0;
        Y0 = y0;
        R = r;
    }
}