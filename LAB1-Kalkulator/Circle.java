public class Circle extends Figure
 implements Printable {

    double r;

    Circle(double r){
        this.r=r;
    }

    double calculateArea(){
        return Math.PI * r * r ;
    }

    double calculatePerimeter(){
        return 2 * Math.PI * r;
    }

    public void print(){
        System.out.println("\nKOŁO");
        System.out.println("Pole wynosi: "+calculateArea());
        System.out.println("Obwod wynosi: "+calculatePerimeter()+"\n");
    }
}
