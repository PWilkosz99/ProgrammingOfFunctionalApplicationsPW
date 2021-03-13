public class Triangle extends Figure
 implements Printable {


    Triangle(double a){
        this.val=a;
        this.verticles=3;
    }

    double calculateArea(){
        return val*val*Math.sqrt(3)/4;
    }

    double calculatePerimeter(){
        return val*3;
    }

    public void print(){
        System.out.println("\nTROJKAT");
        System.out.println("Pole wynosi: "+calculateArea());
        System.out.println("Obwod wynosi: "+calculatePerimeter()+"\n");
    }
}

