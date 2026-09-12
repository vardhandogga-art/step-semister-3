class LibraryMember {

    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipPin, String branchCode,
                         double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class Problem1_MembershipFieldReach {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }

        } else if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }

        } else if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }

        } else if (fieldModifier.equals("public")) {

            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
            "private",
            "default",
            "protected",
            "public"
        };

        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result = classifyAccess(modifier, context);

            for (int j = 0; j < modifiers.length; j++) {

                if (modifier.equals(modifiers[j])) {

                    if (result.equals("ALLOWED")) {
                        allowed[j]++;
                    } else {
                        denied[j]++;
                    }

                    break;
                }
            }
        }

        String result = "";

        for (int i = 0; i < modifiers.length; i++) {

            if (i > 0) {
                result += " | ";
            }

            result += modifiers[i] + ": "
                    + allowed[i] + " allowed / "
                    + denied[i] + " denied";
        }

        return result;
    }

    public static void main(String[] args) {

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
            classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        System.out.println(
            summarizeByModifier(attempts)
        );
    }
}  

