package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // Set a datetime pattern
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Enter with rent data");
        System.out.print("Car model: ");
        String carModel = sc.nextLine();
        System.out.print("Withdraw (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.print("Devolution (dd/MM/yyyy hh:mm): ");
        LocalDateTime end = LocalDateTime.parse(sc.nextLine(), dtf);


        CarRental carRental1 = new CarRental(start, end, new Vehicle(carModel));

        System.out.print("Price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Price per day: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
        rentalService.processInvoice(carRental1);
        System.out.println("DEBT");
        System.out.println("Basic payment: " + String.format("%.2f", carRental1.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f", carRental1.getInvoice().getTax()));
        System.out.println("Total payment: " + String.format("%.2f", carRental1.getInvoice().getTotalPayment()));

        sc.close();
    }
}
