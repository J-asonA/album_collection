import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CollectionManager {
    //get user input thru scanner class
    public static void check() {
        System.out.println("Enter Commands:");
        Scanner user_input = new Scanner(System.in).useDelimiter(",");
//        if (!user_input.toString().equals("Q")) {
            do {
                System.out.println(user_input.next());
            } while (!user_input.toString().equals("Q"));
        }
//    }
}