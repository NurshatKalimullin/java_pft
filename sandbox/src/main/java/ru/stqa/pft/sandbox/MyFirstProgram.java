package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("Nurshat");

        Square s = new Square(5.0);
        System.out.println("Площадь квадрата со стороной " + s.l + " равна " + s.area());

        Rectangle r = new Rectangle(2.0, 5.0);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area());

        Point p1 = new Point(-1, 1);
        Point p2 = new Point(4, 4);
        System.out.println("Distance between points is " + p1.distance(p2));

        Point p3 = new Point(1, 1);
        Point p4 = new Point(4, 5);
        System.out.println("Distance between points is " + p3.distance(p4));
    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }

}