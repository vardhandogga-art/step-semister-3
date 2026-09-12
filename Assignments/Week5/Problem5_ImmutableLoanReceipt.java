import java.util.Arrays;

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        String[] correctedBookIds =
                Arrays.copyOf(bookIds, bookIds.length);

        correctedBookIds[index] = newId;

        return new LoanReceipt(memberId, correctedBookIds);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

class CirculationLedger {

    static String branchCode;

    static {
        branchCode = "PT-CENTRAL";
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {

                nullSkipped++;

            } else {

                processed++;

                if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }
}

public class Problem5_ImmutableLoanReceipt {

    public static void main(String[] args) {

        LoanReceipt r = new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
        );

        // Test defensive copy from getter
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(
                "Original first book ID: "
                + r.getBookIds()[0]
        );

        // Test with-style correction
        LoanReceipt corrected =
                r.withCorrectedBookId(1, "BK-102");

        System.out.println(
                "Original receipt: "
                + Arrays.toString(r.getBookIds())
        );

        System.out.println(
                "Corrected receipt: "
                + Arrays.toString(corrected.getBookIds())
        );

        // Test nightly circulation
        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                    "LIB-001",
                    new String[]{"BK-200"},
                    "Reading Room 3"
            ),

            null,

            new LoanReceipt(
                    "LIB-002",
                    new String[]{"BK-201"}
            )
        };

        System.out.println(
                CirculationLedger.processNightlyCirculation(receipts)
        );
    }
}
