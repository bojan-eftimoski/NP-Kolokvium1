package kol1.zad16;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

enum TaxType {
    A, B, V
}

class Item {

    public static final double TAX_RETURN_RATE = 0.15;

    private int price;
    private TaxType taxType;

    public Item(int price, TaxType taxType) {
        this.price = price;
        this.taxType = taxType;
    }

    public int getPrice() {
        return price;
    }

    public double getTaxReturn() {
        if (taxType == TaxType.A) {
            return price * 0.18 * TAX_RETURN_RATE;
        } else if (taxType == TaxType.B) {
            return price * 0.05 * TAX_RETURN_RATE;
        } else {
            return 0;
        }
    }
}

class AmountNotAllowedException extends Exception {

    public AmountNotAllowedException(int amount) {
        super(String.format("Receipt with amount %d is not allowed to be scanned", amount));
    }
}

class Receipt {

    public static final int MAX_AMOUNT = 30000;

    private String id;
    private List<Item> items;

    public Receipt(String id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public static Receipt create(String line) throws AmountNotAllowedException {
        String[] parts = line.trim().split("\\s+");
        String id = parts[0];
        List<Item> items = new ArrayList<>();
        for (int i = 1; i + 1 < parts.length; i += 2) {
            int price = Integer.parseInt(parts[i]);
            TaxType taxType = TaxType.valueOf(parts[i + 1]);
            items.add(new Item(price, taxType));
        }
        Receipt receipt = new Receipt(id, items);
        if (receipt.getSumOfAmounts() > MAX_AMOUNT) {
            throw new AmountNotAllowedException(receipt.getSumOfAmounts());
        }
        return receipt;
    }

    public int getSumOfAmounts() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }

    public double getTaxReturn() {
        // a stream sum (it uses compensated summation) reproduces the expected rounding exactly;
        // a plain for-loop sum can differ in the last digit for some receipts
        return items.stream().mapToDouble(Item::getTaxReturn).sum();
    }

    @Override
    public String toString() {
        return String.format("%s %d %.2f", id, getSumOfAmounts(), getTaxReturn());
    }
}

class MojDDV {

    private List<Receipt> receipts;

    public MojDDV() {
        receipts = new ArrayList<>();
    }

    public void readRecords(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        br.lines()
                .filter(line -> !line.isBlank())
                .forEach(line -> {
                    try {
                        receipts.add(Receipt.create(line));
                    } catch (AmountNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    public void printTaxReturns(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        receipts.forEach(pw::println);
        pw.flush();
    }
}

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

    }
}
