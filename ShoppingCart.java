import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        if (quantity > product.getStock()) {
            System.out.println("Not enough stock available.");
            return;
        }

        items.add(new CartItem(product, quantity));
        product.reduceStock(quantity);

        System.out.println(product.getName() + " added to cart.");
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n=== Shopping Cart ===");
        for (CartItem item : items) {
            item.displayCartItem();
        }

        System.out.println("Total: R" + calculateTotal());
    }

    public double calculateTotal() {
        double total = 0;

        for (CartItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public double applyDiscount(String code) {
        double total = calculateTotal();

        if (code.equalsIgnoreCase("AMAZON10")) {
            return total * 0.90;
        } else if (code.equalsIgnoreCase("STUDENT15")) {
            return total * 0.85;
        } else {
            System.out.println("Invalid discount code.");
            return total;
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}