class GymMember {

    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember(
            String memberId,
            int monthlyFee) {

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {

        System.out.print(
                "Standard | Sessions: "
                        + sessionsAttended
        );
    }
}

class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo() {

        System.out.print(
                "Premium | Trainer: "
                        + trainerName
                        + " | Sessions: "
                        + getSessionsAttended()
        );
    }
}

public class Problem4_MonthlyAttendanceAnnouncer {

    static String batchPrint(
            GymMember[] members) {

        StringBuilder result =
                new StringBuilder();

        for (GymMember member : members) {

            member.displayInfo();

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) member;

                result.append(
                        premium.getTrainerName()
                );

                result.append(
                        " [Trainer via downcast: "
                                + premium.getTrainerName()
                                + "]"
                );

            } else {

                result.append(
                        "Standard membership"
                );
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember standard =
                new GymMember("MEM6", 1000);

        PremiumMember premium =
                new PremiumMember(
                        "MEM7",
                        2000,
                        "Coach Riya"
                );

        GymMember[] members = {
                standard,
                premium
        };

        System.out.println(
                batchPrint(members)
        );
    }
}