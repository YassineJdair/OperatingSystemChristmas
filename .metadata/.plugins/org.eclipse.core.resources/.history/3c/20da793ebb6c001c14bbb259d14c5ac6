package ie.gmit.sw;

import java.util.StringTokenizer;

public class Club {

	// Variables
	private String clubName;
	private String email;
	private String clubID;

	// constructor
	public Club(String n, String ID, String e) {

		// this.clubID = ID;
		// this.email = e;
		// this.clubName = n;

		clubID = ID;
		clubName = n;
		email = e;
	}

	public Club(String csvline) {

		StringTokenizer st = new StringTokenizer(csvline, ",");
		clubID = st.nextToken();
		clubName = st.nextToken();
		email = st.nextToken();

	}

	// Getters and Setters
	public String getClubName() {
		return clubName;
	}

	public void setClubName(String n) {
		this.clubName = n;
	}

	public String getClubID() {
		return clubID;
	}

	public void setClubID(String ID) {
		this.clubID = ID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String e) {
		this.email = e;
	}

	public String toCsv() {
		return getClubID() + "," + getClubName() + "," + getEmail();
	}

}
