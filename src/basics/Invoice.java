package basics;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class Invoice {
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private String clientName;
    private String clientAddress;
    private String clientEmail;
    private String description;
    private int quantity;
    private double price;

    public Invoice(String invoiceNumber, LocalDate invoiceDate, LocalDate dueDate, String clientName, String clientAddress, String clientEmail, String description, int quantity, double price) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotal() {
        return quantity * price;
    }

    public String getFormattedTotal() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(getTotal());
    }

    public String generateInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice Number: ").append(invoiceNumber).append("\n");
        sb.append("Invoice Date: ").append(invoiceDate).append("\n");
        sb.append("Due Date: ").append(dueDate).append("\n");
        sb.append("Client Name: ").append(clientName).append("\n");
        sb.append("Client Address: ").append(clientAddress).append("\n");
        sb.append("Client Email: ").append(clientEmail).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Quantity: ").append(quantity).append("\n");
        sb.append("Price: $").append(price).append("\n");
        sb.append("Total: $").append(getFormattedTotal()).append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        // Example usage
        Invoice invoice = new Invoice(
            "INV-123", 
            LocalDate.now(), 
            LocalDate.now().plusDays(30), 
            "John Doe", 
            "123 Main St", 
            "johndoe@example.com", 
            "Web Design Services", 
            10, 
            500.00);
            
        System.out.println(invoice.generateInvoice());
    }

}
