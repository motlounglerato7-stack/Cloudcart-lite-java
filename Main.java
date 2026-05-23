import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        ShoppingCart cart = new ShoppingCart();

        products.add(new Product(1, "Wireless Mouse", 249.99, 10));
        products.add(new Product(2, "USB-C Cable", 129.99, 15));
        products.add(new Product(3, "Laptop Stand", 399.99, 8));
        products.add(new Product(4, "Bluetooth Keyboard", 599.99, 5));
        products.add(new Product(5, "Power Bank", 699.99, 7));

        int choice;

        do {
            System.out.println("\n=== CloudCart Lite ===");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n=== Available Products ===");
                    for (Product product : products) {
                        product.displayProduct();
                    }
                    break;

                case 2:
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();

                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();

                    Product selectedProduct = null;

                    for (Product product : products) {
                        if (product.getId() == productId) {
                            selectedProduct = product;
                            break;
                        }
                    }

                    if (selectedProduct != null) {
                        cart.addItem(selectedProduct, quantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    cart.viewCart();
                    break;

                case 4:
                    if (cart.isEmpty()) {
                        System.out.println("Cannot checkout. Your cart is empty.");
                    } else {
                        cart.viewCart();

                        System.out.print("Enter discount code or type NONE: ");
                        String discountCode = scanner.next();

                        double finalTotal;

                        if (discountCode.equalsIgnoreCase("NONE")) {
                            finalTotal = cart.calculateTotal();
                        } else {
                            finalTotal = cart.applyDiscount(discountCode);
                        }

                        System.out.println("\n=== Order Summary ===");
                        System.out.println("Final amount to pay: R" + finalTotal);
                        System.out.println("Thank you for shopping with CloudCart Lite!");
                    }
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 5);

        scanner.close();
    }
}