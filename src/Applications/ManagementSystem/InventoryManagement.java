import java.util.*;

class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void displayInfo() {
        System.out.printf("ID: %d | Name: %-15s | Price: $%-8.2f | Quantity: %d%n",
                id, name, price, quantity);
    }

    public void updateQuantity(int change) {
        this.quantity += change;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setPrice(double price) { this.price = price; }
}

public class InventoryManagement {
    private static List<Product> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        // Add some sample products
        inventory.add(new Product(nextId++, "Laptop", 999.99, 10));
        inventory.add(new Product(nextId++, "Mouse", 25.50, 50));
        inventory.add(new Product(nextId++, "Keyboard", 75.00, 30));

        while (true) {
            System.out.println("\n=== Inventory Management System ===");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product Quantity");
            System.out.println("4. Update Product Price");
            System.out.println("5. Search Product");
            System.out.println("6. Generate Low Stock Report");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addProduct(); break;
                case 2: viewAllProducts(); break;
                case 3: updateQuantity(); break;
                case 4: updatePrice(); break;
                case 5: searchProduct(); break;
                case 6: generateLowStockReport(); break;
                case 7: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter initial quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        inventory.add(new Product(nextId++, name, price, quantity));
        System.out.println("Product added successfully!");
    }

    private static void viewAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("No products in inventory!");
            return;
        }

        System.out.println("\n=== All Products ===");
        System.out.println("ID  Name            Price      Quantity");
        System.out.println("----------------------------------------");
        for (Product product : inventory) {
            product.displayInfo();
        }

        // Calculate total value
        double totalValue = 0;
        for (Product product : inventory) {
            totalValue += product.getPrice() * product.getQuantity();
        }
        System.out.printf("\nTotal Inventory Value: $%.2f%n", totalValue);
    }

    private static void updateQuantity() {
        Product product = getProduct();
        if (product != null) {
            System.out.print("Enter quantity change (positive for add, negative for remove): ");
            int change = scanner.nextInt();
            scanner.nextLine();

            product.updateQuantity(change);
            System.out.println("Quantity updated successfully!");
        }
    }

    private static void updatePrice() {
        Product product = getProduct();
        if (product != null) {
            System.out.print("Enter new price: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();

            product.setPrice(newPrice);
            System.out.println("Price updated successfully!");
        }
    }

    private static void searchProduct() {
        System.out.print("Enter product name or ID to search: ");
        String searchTerm = scanner.nextLine();

        boolean found = false;
        for (Product product : inventory) {
            if (product.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    String.valueOf(product.getId()).equals(searchTerm)) {
                product.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No products found!");
        }
    }

    private static void generateLowStockReport() {
        System.out.print("Enter low stock threshold: ");
        int threshold = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n=== Low Stock Report (Threshold: " + threshold + ") ===");
        boolean foundLowStock = false;

        for (Product product : inventory) {
            if (product.getQuantity() <= threshold) {
                product.displayInfo();
                foundLowStock = true;
            }
        }

        if (!foundLowStock) {
            System.out.println("No products below threshold!");
        }
    }

    private static Product getProduct() {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product product = findProduct(id);
        if (product == null) {
            System.out.println("Product not found!");
        }
        return product;
    }

    private static Product findProduct(int id) {
        for (Product product : inventory) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}