public class Squere extends Figure
 implements Printable {

    double x;

    Squere(double x){
        this.x=x;
    }

    double calculateArea(double x){
        return x*x;
    }

    double calculatePerimeter(double x){
        return x*4;
    }

    public void print(){
        System.out.println("\nKWADRAT");
        System.out.println("Pole wynosi: "+calculateArea(x));
        System.out.println("Obwod wynosi: "+calculatePerimeter(x)+"\n");
    }
}
