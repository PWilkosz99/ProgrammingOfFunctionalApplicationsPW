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

          break;

          case 5:
          scanner.close();
          return;
        }


      }

      // Squere kwdrt=new Squere(5);
      // kwdrt.print();
      // Circle kolo=new Circle(5);
      // kolo.print();
      // Triangle trojkat=new Triangle(5);
      // trojkat.print();
    }
  }