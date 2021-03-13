public class Prism implements Printable{

    double h;
    double x;
    double basearea;
    int verticles;


    Prism(Figure f, double h){
        this.h=h;
        x=f.val;
        basearea=f.calculateArea();
        this.verticles=f.verticles;
    }


    double calculateArea() {
        return 2*basearea+x*h*verticles;
    }

    double calculateVolume() {
        return h*basearea;
    }
    
    public void print() {
        System.out.println("\nGRANIASTOSŁUP PRAWIDŁOWy");
        System.out.println("Pole wynosi: "+calculateArea());
        System.out.println("Objetość wynosi: "+calculateVolume()+"\n");
    }
}
