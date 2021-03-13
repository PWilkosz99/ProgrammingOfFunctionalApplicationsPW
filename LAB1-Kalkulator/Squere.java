public class Squere extends Figure
 implements Printable {

    double x;

    Squere(double x){
        this.x=x;
    }

    double calculateArea(){
        return x*x;
    }

    double calculatePerimeter(){
        return x*4;
    }

    public void print(){
        System.out.println("\nKWADRAT");
        System.out.println("Pole wynosi: "+calculateArea());
        System.out.println("Obwod wynosi: "+calculatePerimeter()+"\n");
    }
}
