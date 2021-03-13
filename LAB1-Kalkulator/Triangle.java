public class Triangle extends Figure
 implements Printable {

    double a;

    Triangle(double a){
        this.a=a;
    }

    double calculateArea(){
        return a*a*Math.sqrt(3)/4;
    }

    double calculatePerimeter(){
        return a*3;
    }

    public void print(){
        System.out.println("\nTROJKAT");
        System.out.println("Pole wynosi: "+calculateArea());
        System.out.println("Obwod wynosi: "+calculatePerimeter()+"\n");
    }
}

