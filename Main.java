import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

class Main {
  public static void printMenu() {
    System.out.println("Press 1 to add an item.");
    System.out.println("Press 2 to delete an item.");
    System.out.println("Press 3 to update an item.");
    System.out.println("Press 4 to show all the items.");
    System.out.println("Press 5 to quit the program.");
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Inventory inventory = new Inventory();
    int userInput;
    String name;
    String serialNumber;
    int value;
    printMenu();
    userInput = input.nextInt();
    input.nextLine();

    while (userInput != 5) {
      if (userInput == 1) { 
        System.out.println("Enter the name:");
        name = input.nextLine();
        
        System.out.println("Enter the serial number:");
        serialNumber = input.nextLine();
        
        System.out.println("Enter the value in dollars (whole number):");
        value = input.nextInt();
        input.nextLine();
        
        Item item = new Item(name, serialNumber, value);
        inventory.addItem(item);
      } else if (userInput == 2) { 
        System.out.println("Enter the serial number of the item to delete:");
        serialNumber = input.nextLine();
        
        inventory.removeItem(inventory.getItem(serialNumber));
      } else if (userInput == 3) { 
        System.out.println("Enter the serial number of the item to change:");
        serialNumber = input.nextLine();
        
        System.out.println("Enter the new name:");
        name = input.nextLine();
        
        System.out.println("Enter the new value in dollars (whole number):");
        value = input.nextInt();
        
        Item item = inventory.getItem(serialNumber);
        item.modifyItem(name, serialNumber, value);
      } else if (userInput == 4) {
        inventory.printInventory();
      } else {
        break;
      }
      printMenu();
      userInput = input.nextInt();
      input.nextLine();
    }
    input.close();
  }
}

class Inventory {
  public ArrayList<Item> inventory;

  public Inventory() {
    inventory = new ArrayList<>();
  }

  public void addItem(Item item) {
    inventory.add(item);
  }

  public void removeItem(Item item) {
    inventory.remove(item);
  }

  public Item getItem(String serialNumber) {
    for (Item item : inventory) {
      if (item.getSerialNumber().equals(serialNumber))
        return item;
    }
    throw new NoSuchElementException();
  }

  public void printInventory() {
    for (Item item : inventory) {
      System.out.println(item.toString());
    }
  }
}

class Item {
  private String name;
  private String serialNumber;
  private int value;

  public Item(String name, String serialNumber, int value) {
    this.name = name;
    this.serialNumber = serialNumber;
    this.value = value;
  }

  public void modifyItem(String name, String serialNumber, int value) {
    this.name = name;
    this.serialNumber = serialNumber;
    this.value = value;
  }

  public String toString() {
    return String.format("%s,%s,%d", name, serialNumber, value);
  }

  public String getSerialNumber() {
    return this.serialNumber;
  }
}