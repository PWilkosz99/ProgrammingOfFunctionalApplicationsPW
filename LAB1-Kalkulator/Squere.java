public class Squere extends Figure
 implements Printable {

    double x;

    double calculateArea(double x){
        return x*x;
    }

    double calculatePerimeter(double x){
        return x*4;
    }
}
