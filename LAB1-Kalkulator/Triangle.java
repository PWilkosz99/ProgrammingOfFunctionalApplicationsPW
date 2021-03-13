public class Triangle extends Figure
 implements Printable {

    double h;
    double a, b, c;

    double calculateArea(double a, double h){
        return a*h*0.5;
    }

    double calculatePerimeter(double a, double b, double c){
        return a+b+c;
    }
}

