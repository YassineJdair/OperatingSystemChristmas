package ie.gmit.sw;

import java.util.StringTokenizer;

public class Members {

	private String clubID;
	private String playerID;
	private String name;
	private int age;
	private String visitedLast;
	private double membershipFee;
	private int membershipType;
	private int paymentStatus;

	public Members(String cID, String pID, String n, int a, String v, double f, int memtype, int paymentStat) {
		clubID = cID;
		playerID = pID;
		name = n;
		age = a;
		visitedLast = v;
		membershipFee = f;
		membershipType = memtype;
		paymentStatus = paymentStat;
	}

	public Members(String csvline) {
		StringTokenizer st = new StringTokenizer(csvline, ",");

		clubID = st.nextToken();
		playerID = st.nextToken();
		name = st.nextToken();
		age = Integer.parseInt(st.nextToken());
		visitedLast = st.nextToken();
		membershipFee = Double.parseDouble(st.nextToken());
		membershipType = Integer.parseInt(st.nextToken());
		paymentStatus = Integer.parseInt(st.nextToken());

	}

	public String getClubID() {
		return clubID;
	}

	public void setClubID(String cID) {
		this.clubID = cID;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerId(String pID) {
		this.playerID = pID;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		this.name = n;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int a) {
		this.age = a;
	}

	public String getVisitedLast() {
		return visitedLast;
	}

	public void setVisitedLast(String visitedLast) {
		this.visitedLast = visitedLast;
	}

	public double getMembershipFee() {
		return membershipFee;
	}

	public void setMembershipFee(double membershipFee) {
		this.membershipFee = membershipFee;
	}

	public int getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(int membershipType) {
		this.membershipType = membershipType;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String toCsv() {
		return getClubID() + "," + getPlayerID() + "," + getName() + "," + getAge() + "," + getVisitedLast() + ","
				+ getMembershipFee() + "," + getMembershipType() + "," + getPaymentStatus();
	}

	public String toPrintedCsv() {
		return getClubID() + "," + getPlayerID() + "," + getName() + "," + getAge() + "," + getVisitedLast() + ","
				+ getMembershipFee() + "," + getMembershipTypeStr(getMembershipType()) + ","
				+ getPaymentStatusStr(getPaymentStatus());
	}

	public String getPaymentStatusStr(int paymentStatus) {
		switch (paymentStatus) {
		case 1:
			return "Paid";
		case 2:
			return "Part Paid";
		case 3:
			return "Not Paid";
		default:
			return "Unknown";
		}
	}

	public String getMembershipTypeStr(int paymentStatus) {
		switch (paymentStatus) {
		case 1:
			return "Adult";
		case 2:
			return "Senior";
		case 3:
			return "Junior";
		default:
			return "Unknown";
		}
	}

}