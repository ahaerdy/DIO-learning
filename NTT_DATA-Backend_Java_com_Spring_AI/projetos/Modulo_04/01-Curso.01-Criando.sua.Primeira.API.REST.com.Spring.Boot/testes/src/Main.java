import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome!");

        int[] numbers = new Random().ints(50, 0, 100).toArray();

        for (int number : numbers) {
            if (number%2 == 0) System.out.print(number + "-par / ");
            else System.out.print(number + "-ímpar / ");
        }

    }
}