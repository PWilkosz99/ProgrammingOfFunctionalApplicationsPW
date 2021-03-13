public class Circle extends Figure
 implements Printable {

    double r;

    Circle(double r){
        this.r=r;
    }

    double calculateArea(double r){
        return Math.PI * r * r ;
    }

    double calculatePerimeter(double r){
        return 2 * Math.PI * r;
    }

    public void print(){
        System.out.println("\nKOŁO");
        System.out.println("Pole wynosi: "+calculateArea(r));
        System.out.println("Obwod wynosi: "+calculatePerimeter(r)+"\n");
    }
}
