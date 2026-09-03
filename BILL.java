import java.util.Scanner;

// Base Payment class
class Payment {
    protected static int nextId = 1;   // auto-increment id
    protected int id;
    protected String payeeName;
    protected double billAmount;
    protected String date;

    public Payment(String payeeName, double billAmount, String date) {
        this.id = nextId++;
        this.payeeName = payeeName;
        this.billAmount = billAmount;
        this.date = date;
    }

    public double calculateFinalAmount() {
        return billAmount;
    }

    public void printReceipt(double finalAmount, String mode) {
        System.out.println("\n------ BILL RECEIPT ------");
        System.out.println("Payment ID   : " + id);
        System.out.println("Payee Name   : " + payeeName);
        System.out.println("Bill Amount  : " + billAmount);
        System.out.println("Date         : " + date);
        System.out.println("Mode         : " + mode);
        System.out.println("Final Amount : " + finalAmount);
        System.out.println("---------------------------\n");
    }
}

// Cash Payment
class Cash extends Payment {
    public Cash(String payeeName, double billAmount, String date) {
        super(payeeName, billAmount, date);
    }

    @Override
    public double calculateFinalAmount() {
        double gst = 0.12 * billAmount;
        return billAmount + gst;
    }
}

// Card Payment (abstract because DebitCard/CreditCard will extend)
abstract class Card extends Payment {
    protected String cardNo;
    protected int pin;

    public Card(String payeeName, double billAmount, String date, String cardNo, int pin) {
        super(payeeName, billAmount, date);
        this.cardNo = cardNo;
        this.pin = pin;
    }
}

// Debit Card
class DebitCard extends Card {
    public DebitCard(String payeeName, double billAmount, String date, String cardNo, int pin) {
        super(payeeName, billAmount, date, cardNo, pin);
    }

    @Override
    public double calculateFinalAmount() {
        double gst = 0.12 * billAmount;
        double charges = 0.10 * billAmount;
        return billAmount + gst + charges;
    }
}

// Credit Card
class CreditCard extends Card {
    public CreditCard(String payeeName, double billAmount, String date, String cardNo, int pin) {
        super(payeeName, billAmount, date, cardNo, pin);
    }

    @Override
    public double calculateFinalAmount() {
        double gst = 0.12 * billAmount;
        double charges = 0.05 * billAmount;
        double discount = 50.0;
        return billAmount + gst + charges - discount;
    }
}

// Main Bill Class
public class Bill {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get bill details
        System.out.print("Enter Payee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Bill Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.print("Enter Date (dd/mm/yyyy): ");
        String date = sc.nextLine();

        // Choose payment mode
        System.out.print("Enter Mode of Payment (1. Cash  2. Card): ");
        int choice = sc.nextInt();

        Payment payment = null;
        double finalAmount = 0;

        if (choice == 1) {
            payment = new Cash(name, amount, date);
            finalAmount = payment.calculateFinalAmount();
            payment.printReceipt(finalAmount, "Cash");

        } else if (choice == 2) {
            System.out.print("Enter Card No: ");
            String cardNo = sc.next();

            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            System.out.print("Enter Card Type (1. Debit  2. Credit): ");
            int cardType = sc.nextInt();

            if (cardType == 1) {
                payment = new DebitCard(name, amount, date, cardNo, pin);
                finalAmount = payment.calculateFinalAmount();
                payment.printReceipt(finalAmount, "Debit Card");

            } else if (cardType == 2) {
                payment = new CreditCard(name, amount, date, cardNo, pin);
                finalAmount = payment.calculateFinalAmount();
                payment.printReceipt(finalAmount, "Credit Card");
            } else {
                System.out.println("Invalid Card Type!");
            }
        } else {
            System.out.println("Invalid Payment Mode!");
        }

        sc.close();
    }
