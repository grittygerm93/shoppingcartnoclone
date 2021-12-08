import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartDB {
    public static User load(String userName){
        File file = new File(String.format("db/%s.txt", userName));
        List<String> cartInfo = null;
        User user = null;
        try(Scanner s = new Scanner(file)) {
            cartInfo = new ArrayList<>(Arrays.asList(s.nextLine().split(",")));
            user = new User(cartInfo.remove(0),cartInfo);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void save(String des, User user) throws IOException {

        File file = new File(String.format("%s/%s.txt", des, user.getName()));
        file.getParentFile().mkdirs();

        try(FileWriter writer = new FileWriter(file)) {
            writer.write(user.getName());
            user.getCartItems().forEach(e -> {
                try {
                    writer.write("," + e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
                        

        }

    }

    public static boolean hasUser(String userName) {
        File file = new File("db");
        if(!file.exists()) {
            return false;
        }
        return List.of(file.list()).contains(String.format("%s.txt", userName));
    }
    
}
