import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

      Scanner scanner = new Scanner(System.in);

      while(true){
        System.out.print("Wybierz opcję:\n1 Kwadrat\n2 Trójkąt\n3 Koło\n4 Graniastosłup prawidłowy\n5 Wyjcie z programu\nOpcja: ");
        var input = scanner.nextInt();

        switch(input){
          case 1:
          System.out.print("\nPodaj bok kwadratu: ");
          var value = scanner.nextDouble();
          Squere squere = new Squere(value);
          squere.print();

          break;

          case 2:
          System.out.print("\nPodaj podstawe trojkata: ");
          var value2 = scanner.nextDouble();
          Triangle triangle = new Triangle(value2);
          triangle.print();

          break;

          case 3:
          System.out.print("\nPodaj promien koła: ");
          var value3 = scanner.nextDouble();
          Circle circle = new Circle(value3);
          circle.print();

          break;

          case 4:
          System.out.print("Wybierz figurę w podstawie:\n1 Kwadrat\n2 Trójkąt\nOpcja: ");
          var value4=scanner.nextInt();
          if(value4==1){
            System.out.print("\nPodaj długość boku:");
            var value5=scanner.nextDouble();
            Squere squerep = new Squere(value5);
            System.out.print("\nPodaj wysokość:");
            var h1=scanner.nextDouble();
            Prism prism = new Prism(squerep, h1);
            prism.print();

          }else if(value4==2){
            System.out.print("\nPodaj promien koła: ");
            var value6 = scanner.nextDouble();
            Triangle trianglep = new Triangle(value6);
            System.out.print("\nPodaj wysokość:");
            var h2=scanner.nextDouble();
            Prism prism = new Prism(trianglep, h2);
            prism.print();

          }else{
            System.out.print("Blędna wartość");
            break;
          }

          break;

          case 5:
          scanner.close();
          return;
        }
      }
    }
  }