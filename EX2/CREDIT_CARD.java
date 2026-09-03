import java.util.Scanner;

// Abstract class
abstract class CreditCard {
    protected String cardNumber;
    protected String cardHolderName;
    protected double maxCreditLimit;

    public CreditCard(String cardNumber, String cardHolderName, double maxCreditLimit) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.maxCreditLimit = maxCreditLimit;
    }

    public void displayDetails() {
        System.out.println("Card Holder Name : " + cardHolderName);
        System.out.println("Card Number      : " + cardNumber);
        System.out.println("Maximum Limit    : ₹" + maxCreditLimit);
    }

    public abstract void calculateBill(double purchaseAmount);
}

// SilverCard subclass
class SilverCard extends CreditCard {
    public SilverCard(String cardNumber, String cardHolderName) {
        super(cardNumber, cardHolderName, 200000);
    }

    @Override
    public void calculateBill(double purchaseAmount) {
        System.out.println("No discount available for Silver Card.");
        System.out.println("Final Bill Amount : ₹" + purchaseAmount);
    }
}

// GoldCard subclass
class GoldCard extends CreditCard {
    public GoldCard(String cardNumber, String cardHolderName) {
        super(cardNumber, cardHolderName, 500000);
    }

    @Override
    public void calculateBill(double purchaseAmount) {
        double discount = 0.05 * purchaseAmount;
        double finalAmount = purchaseAmount - discount;
        System.out.println("5% Discount Applied: ₹" + discount);
        System.out.println("Final Bill Amount   : ₹" + finalAmount);
    }
}

// PlatinumCard subclass
class PlatinumCard extends CreditCard {
    public PlatinumCard(String cardNumber, String cardHolderName) {
        super(cardNumber, cardHolderName, 1000000);
    }

    @Override
    public void calculateBill(double purchaseAmount) {
        double discount = 0.10 * purchaseAmount;
        double finalAmount = purchaseAmount - discount;
        System.out.println("10% Discount Applied: ₹" + discount);
        System.out.println("Final Bill Amount   : ₹" + finalAmount);
    }
}

// Main program
public class CreditCard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Card Holder Name:");
        String name = sc.nextLine();

        System.out.println("Enter Card Number:");
        String number = sc.nextLine();

        System.out.println("Choose Card Type:");
        System.out.println("1. Silver Card");
        System.out.println("2. Gold Card");
        System.out.println("3. Platinum Card");
        int choice = sc.nextInt();

        System.out.println("Enter Purchase Amount:");
        double amount = sc.nextDouble();

        CreditCard card = null;

        switch (choice) {
            case 1:
                card = new SilverCard(number, name);
                break;
            case 2:
                card = new GoldCard(number, name);
                break;
            case 3:
                card = new PlatinumCard(number, name);
                break;
            default:
                System.out.println("Invalid Choice!");
                System.exit(0);
        }

        System.out.println("\n=== Card Details ===");
        card.displayDetails();
        System.out.println("====================");
        card.calculateBill(amount);

        sc.close();
    }
}
