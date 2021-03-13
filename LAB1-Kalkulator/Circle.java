public class Circle extends Figure
 implements Printable {


    Circle(double r){
        this.val=r;
        this.verticles=0;
    }

    double calculateArea(){
        return Math.PI * val * val ;
    }

    double calculatePerimeter(){
        return 2 * Math.PI * val;
    }

    public void print(){
        System.out.println("\nKOŁO");
        System.out.println("Pole wynosi: "+calculateArea());
        System.out.println("Obwod wynosi: "+calculatePerimeter()+"\n");
    }
}
