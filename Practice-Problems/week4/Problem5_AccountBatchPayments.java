class FeeAccount {

    String accountNumber;

    public FeeAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

class HostelFeeAccount extends FeeAccount {

    public HostelFeeAccount(String accountNumber) {
        super(accountNumber);
    }
}

class PaymentProcessor {

    int hostelCount = 0;
    int dayScholarCount = 0;

    public void processPayment(FeeAccount account, double amount) {

        if (account instanceof HostelFeeAccount) {

            System.out.println(
                    "Paid in two installments (hostel account)"
            );

            hostelCount++;

        } else {

            System.out.println(
                    "Paid in one go (day-scholar account)"
            );

            dayScholarCount++;
        }
    }

    public void printCounts() {

        System.out.println(
                "Hostel accounts processed: " + hostelCount +
                " | Day-scholar accounts processed: " +
                dayScholarCount
        );
    }
}

public class Problem5_AccountBatchPayments {

    public static void main(String[] args) {

        FeeAccount[] accounts = {
                new HostelFeeAccount("H-101"),
                new HostelFeeAccount("H-102"),
                new FeeAccount("D-101"),
                new FeeAccount("D-102")
        };

        double amount = 60000;

        PaymentProcessor processor =
                new PaymentProcessor();

        for (FeeAccount account : accounts) {

            processor.processPayment(account, amount);
        }

        processor.printCounts();
    }
}
