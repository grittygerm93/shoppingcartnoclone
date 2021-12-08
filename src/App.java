import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class App {
    public static final List<String> list = new ArrayList<>();
    public static final List<User> userList = new ArrayList<>();
    static User currUser;
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        String des = args.length==0? "db": args[0];

        System.out.printf("Welcome to your shopping cart, the available commands are: %n1. login <user> %n2. list %n3. add <items> %n4.delete <item> %n5.save %n6.users %nto exit input <0>");
        
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
                case "login":
                    if (!ShoppingCartDB.hasUser(inputArr.get(0))) {
                        currUser = acceptUserLogin(inputArr.get(0));
                    }
                    else {
                        currUser = ShoppingCartDB.load(inputArr.get(0));
                    }
                    break;
                case "list":
                    if (!currUser.getCartItems().isEmpty()) {
                        printList(currUser);
                    } else {
                        System.out.println("Your cart is empty");
                    }
                    break;
                case "add":
                    currUser.setCartItems(inputArr);
                    break;
                case "delete":
                    int index = Integer.parseInt(inputArr.get(0));
                    currUser.getCartItems().remove(index);
                    break;
                case "save":
                    System.out.println("Your cart has been saved");
                    try {
                        ShoppingCartDB.save(des, currUser);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "users":
                    System.out.println("The following users are registered");
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println(i + ". " + userList.get(i).getName());   
                    }
                    break;
                default:
                    System.out.println("input username first!");
            }
        }
    }

    private static User acceptUserLogin(String userName) {
        
        boolean newUser = true;
        User thisUser = null;   

        for (User users : userList) {
            if(users.getName().equals(userName)){
                thisUser = users;
                System.out.print(thisUser.getName() + ", your cart contains the following items\n");
                printList(thisUser);
                newUser = false;
            }
        }

        if(newUser) {
            User user = new User(userName);
            userList.add(user);
            thisUser = user;
            System.out.println(user.getName() + ", your cart is empty");
        }
        return thisUser;

    }

    private static void printList(User user) {
        for (int i = 0; i < user.getCartItems().size(); i++) {
            System.out.println(i+1 + ". " + user.getCartItems().get(i));
        }
    }
}