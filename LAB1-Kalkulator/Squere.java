public class Squere extends Figure
 implements Printable {


    Squere(double x){
        this.val=x;
        this.verticles=4;
    }

    double calculateArea(){
        return val*val;
    }

    double calculatePerimeter(){
        return val*val;
    }

    public void print(){
        System.out.println("\nKWADRAT");
        System.out.println("Pole wynosi: "+calculateArea());
        System.out.println("Obwod wynosi: "+calculatePerimeter()+"\n");
    }
}
