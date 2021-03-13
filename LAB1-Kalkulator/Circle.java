public class Circle extends Figure
 implements Printable {

    double r;

    double calculateArea(double r){
        return Math.PI * r * r ;
    }

    double calculatePerimeter(double r){
        return 2 * Math.PI * r;
    }
}
