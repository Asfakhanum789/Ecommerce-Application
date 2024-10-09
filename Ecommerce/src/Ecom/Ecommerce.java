package Ecom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ecommerce {
    private static String[][] categories = {
        {"Fashion", "Men", "Women","kid"},
        {"Groceries", "Beverages", "Dairy", "Meat", "Produce"},
        {"Electronics", "TVs", "Laptops", "Tablets", "Smartphones"},
        {"Home Decor", "Furniture", "Lighting", "Textiles", "Wall Decor"}
    };

    private static String[][] fashion = {
    		{"Men", "Shirts (1000)", "Pants (200)", "Shoes (300)"},
    	    {"Women", "Dresses (1500)", "Tops (1200)", "Skirts (1800)"},
    	    {"Kid", "Frocks (560)", "Tops (1300)"}
    };

    private static String[][] groceries = {
        {"Beverages", "Juice (20)", "Soda (25)", "Water (10)", "Coffee (5)"},
        {"Dairy", "Milk (20)", "Cheese (50)", "Eggs (10)", "Butter (30)"},
        {"Meat", "Beef (100)", "Chicken (80)", "Pork (90)", "Fish (102)"},
        {"Produce", "Apples (100)", "Bananas (50)", "Carrots (50)", "Broccoli (50)"}
    };

    private static String[][] electronics = {
        {"TVs", "LED TVs (5000)", "OLED TVs (1000)", "Smart TVs (8000)", "4K TVs (12000)"},
        {"Laptops", "Gaming Laptops (15000)", "Budget Laptops (30000)", "Chromebooks (20000)", "MacBooks (20000)"},
        {"Tablets", "iPads (5000)", "Android Tablets (20000)", "Windows Tablets (30000)", "eReaders (10000)"},
        {"Smartphones", "iPhones (1000)", "Samsung Phones (8000)", "Google Pixels (60000)", "OnePlus Phones (5000)"}
    };

    private static String[][] homeDecor = {
        {"Furniture", "Sofas (5000)", "Chairs (1000)", "Tables (2000)", "Desks (300)"},
        {"Lighting", "Lam11ps (2000)", "Chandeliers (1000)", "Sconces (50)", "String Lights (300)"},
        {"Textiles", "Rugs (5000)", "Curtains (2000)", "Pillows (1000)", "Blankets (1005)"},
        {"Wall Decor", "Paintings (5000)", "Posters (1000)", "Mirrors (20)", "Shelves (3000)"}
    };

    private static List<String> cart = new ArrayList<>();
    private static double totalCost = 0.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("                           Welcome to Ecommerce Website                ");

        while (true) {
            displayCategories();

            System.out.print("Enter the number of your chosen category (0 to exit, 5 to view cart, 6 to checkout): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (choice == 0) {
                System.out.println("Thank you for shopping!");
                break;
            } else if (choice == 5) {
                displayCart();
            } else if (choice == 6) {
                checkout();
                break;
            }

            String chosenCategory = getChosenCategory(choice);
            if (chosenCategory == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            String[][] subCategories = getSubCategories(chosenCategory);
            if (subCategories == null) {
                System.out.println("Invalid category. Please try again.");
                continue;
            }

            displaySubCategories(subCategories);

            System.out.print("Enter the number of your chosen sub-category (0 to go back): ");
            int subChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (subChoice == 0) {
                continue;
            }

            String chosenSubCategory = getChosenSubCategory(subChoice, subCategories);
            if (chosenSubCategory == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            String[] products = getProducts(chosenSubCategory);
            if (products == null) {
                System.out.println("Invalid sub-category. Please try again.");
                continue;
            }

            displayProducts(products);
            System.out.print("Enter the number of your chosen product (0 to go back): ");
            int productChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (productChoice == 0) {
                continue;
            }

            String chosenProduct = getChosenProduct(productChoice, products);
            if (chosenProduct == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            double productPrice = getProductPrice(chosenProduct);

            System.out.print("Do you want to add this product to your cart? (yes/no): ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                addProductToCart(chosenProduct);
                System.out.println("Product added to cart successfully!");
                System.out.println("Total cost: " + totalCost);
            } else {
                System.out.println("Product not added to cart.");
            }
        }
    }

    private static void displayCategories() {
        System.out.println("Categories:");
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i][0]);
        }
    }

    private static String getChosenCategory(int choice) {
        if (choice > 0 && choice <= categories.length) {
            return categories[choice - 1][0];
        }
        return null;
    }

    private static String[][] getSubCategories(String category) {
        if (category.equals("Fashion")) {
            return fashion;
        } else if (category.equals("Groceries")) {
            return groceries;
        } else if (category.equals("Electronics")) {
            return electronics;
        } else if (category.equals("Home Decor")) {
            return homeDecor;
        }
        return null;
    }

    private static void displaySubCategories(String[][] subCategories) {
        System.out.println("Sub-categories:");
        for (int i = 0; i < subCategories.length; i++) {
            System.out.println((i + 1) + ". " + subCategories[i][0]);
        }
    }

    private static String getChosenSubCategory(int choice, String[][] subCategories) {
        if (choice > 0 && choice <= subCategories.length) {
            return subCategories[choice - 1][0];
        }
        return null;
    }

    private static String[] getProducts(String subCategory) {
    	 
    	    if (subCategory.equals("Men")) {
    	        return fashion[0];
    	    } else if (subCategory.equals("Women")) {
    	        return fashion[1];
    	    } else if (subCategory.equals("Kid")) {
    	        return fashion[2];  // Ensure this returns the correct data for "Kid"
    	    }
    	    // Remaining categories
    	  
    	

        else if (subCategory.equals("Beverages")) {
            return groceries[0];
        } else if (subCategory.equals("Dairy")) {
            return groceries[1];
        } else if (subCategory.equals("Meat")) {
            return groceries[2];
        } else if (subCategory.equals("Produce")) {
            return groceries[3];
        } else if (subCategory.equals("TVs")) {
            return electronics[0];
        } else if (subCategory.equals("Laptops")) {
            return electronics[1];
        } else if (subCategory.equals("Tablets")) {
            return electronics[2];
        } else if (subCategory.equals("Smartphones")) {
            return electronics[3];
        } else if (subCategory.equals("Furniture")) {
            return homeDecor[0];
        } else if (subCategory.equals("Lighting")) {
            return homeDecor[1];
        } else if (subCategory.equals("Textiles")) {
            return homeDecor[2];
        } else if (subCategory.equals("Wall Decor")) {
            return homeDecor[3];
        }
        return null;
    }

    private static void displayProducts(String[] products) {
        System.out.println("Products:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i]);
        }
    }

    private static String getChosenProduct(int choice, String[] products) {
        if (choice > 0 && choice <= products.length) {
            return products[choice - 1];
        }
        return null;
    }

    private static double getProductPrice(String product) {
        String[] parts = product.split(" \\(");  // Split using space and opening parenthesis
        if (parts.length > 1) {
            try {
                return Double.parseDouble(parts[1].replace(")", ""));  // Remove the closing parenthesis
            } catch (NumberFormatException e) {
                System.out.println("Error parsing price for product: " + product);
                return 0.0;
            }
        }
        return 0.0;
    }



    private static void addProductToCart(String product) {
        cart.add(product);
        double price = getProductPrice(product);
        totalCost += price;
    }
    private static void displayCart() {
        System.out.println("Cart:");
        for (String product : cart) {
            System.out.println(product);
        }
        System.out.println("Total cost: " + totalCost);
    }

    private static void checkout() {
        System.out.println("Checkout:");
        displayCart();
        System.out.println("Thank you for shopping!");
    }
}