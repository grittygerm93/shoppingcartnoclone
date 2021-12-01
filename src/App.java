import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to your shopping cart");
        Scanner s = new Scanner(System.in);

        while (true) {
            String input = s.nextLine();

            if("0".equals(input))
                break;

            List<String> inputArr = new ArrayList<>(Arrays.asList(input.split("\\s+")));
            for (int i = 0; i < inputArr.size(); i++) {
                inputArr.set(i, inputArr.get(i).replace(",", ""));
            }

            String command = inputArr.get(0);
            inputArr.remove(0);

            switch (command) {
                case "list":
                    if (!list.isEmpty()) {
                        printList();
                    } else {
                        System.out.println("Your cart is empty");
                    }
                    break;
                case "add":
                    addList(inputArr);
                    break;
                case "delete":
                    int index = Integer.valueOf(inputArr.get(0)).intValue();
                    list.remove(index);
                    break;
            }
        }
    }

    private static void addList(List<String> inputArr) {
        list.addAll(inputArr);
    }

    private static void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
    }
}
