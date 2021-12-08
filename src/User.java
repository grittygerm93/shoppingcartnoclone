import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<String> cartItems = new ArrayList<>();

    public User() {

    }
    
    public User(String name) {
        this.name = name;
    }

    public User(String name, List<String> cartItems) {
        this.name = name;
        this.cartItems = cartItems;
    }

    public List<String> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<String> cartItems) {
        cartItems.forEach(e -> System.out.println(e + " added to cart"));
        this.cartItems.addAll(cartItems);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
