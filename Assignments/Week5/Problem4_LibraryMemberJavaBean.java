class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;

    private int securityAnswerHash;

    private boolean membershipIdSet;

    public LibraryMember() {
        membershipIdSet = false;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        if (!membershipIdSet) {

            membershipId = id;
            membershipIdSet = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {

        if (answer != null) {
            securityAnswerHash = answer.hashCode();
        }
    }
}

public class Problem4_LibraryMemberJavaBean {

    public static void main(String[] args) {

        LibraryMember m = new LibraryMember();

        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println(
            "Membership ID: "
            + m.getMembershipId()
        );

        m.setMembershipId("FAKE-0000");

        System.out.println(
            "Membership ID after second set: "
            + m.getMembershipId()
        );

        System.out.println(
            "Premium Member: "
            + m.isPremiumMember()
        );

        m.setSecurityAnswer("BlueMountain");

        System.out.println(
            "Security answer stored successfully."
        );
    }
}
