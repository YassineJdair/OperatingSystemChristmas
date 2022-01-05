package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ClubList {

	List<Members> membersList;
	List<Club> clubList;

	String name;
	String message;

	// private LinkedList<Club> clublisting;

	public ClubList() {
		membersList = new ArrayList<Members>();
		clubList = new LinkedList<Club>();
		String line;
		// String item[] = new String[3];
		// Club temp;

		// int i;

		try {
			FileReader fR = new FileReader("members.csv");
			BufferedReader bR = new BufferedReader(fR);

			bR.readLine();

			while ((line = bR.readLine()) != null) {

				membersList.add(new Members(line));
				// clubList.add(temp);
			}

			fR = new FileReader("clubs.csv");
			bR = new BufferedReader(fR);

			bR.readLine();
			while ((line = bR.readLine()) != null) {
				clubList.add(new Club(line));
			}

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	// adding a club using clubname clubid and email
	public synchronized String addClub(String clubName, String clubID, String email) {
		log("Add club with name: " + clubName + ", ClubID: " + clubID + ", Email: " + email);
		Club c = getClubByID(clubID);

		if (c != null) {
			String error = "Can't use clubID as it is already in Use";
			log(error);
			return error;
		}

		c = getClubByName(clubName);
		if (c != null) {
			String error = "Can't use clubName as it is already in Use";
			log(error);
			return error;
		}

		Club newClub = new Club(clubID, clubName, email);
		clubList.add(newClub);
		addClubToFile(newClub);
		return "";
	}

	// adding members
	public String addMembers(String clubID, String memberName, int age, String visitedLast, double fee,
			int membershipType, int paymentStatus) {

		// make sure club id is valid
		Club c = getClubByID(clubID);
		if (c == null) {
			return "The clubID does not exist, please try again";
		}

		// membership type, visited date and payment status has to be valid
		if (!paymentStatusIsValid(paymentStatus)) {
			return "Invalid Payment Status";
		}
		if (!membershipTypeIsValid(membershipType)) {
			return "Invalid Membership Type";
		}
		if (!dateIsValid(visitedLast)) {
			return "Invalid Date format Entered for the date the member last visited the Club, Date Format Must be in the format dd/mm/yyyy";
		}

		int playerID = membersList.size() + 1;
		Members newMembers = new Members(clubID, "" + playerID, memberName, age, visitedLast, fee, membershipType,
				paymentStatus);
		membersList.add(newMembers);
		addMembersToFile(newMembers);
		return "";
	}

	// login
	public boolean login(String loginClubID, String loginClubName) {
		Iterator<Club> iter = clubList.iterator();
		Club c;

		while (iter.hasNext()) {
			c = iter.next();
			if (c.getClubName().equals(loginClubName) && c.getClubID().equals(loginClubID)) {

				return true;
			}
		}
		return false;
	}

	// adding a club to file unfinished
	private void addClubToFile(Club newClub) {
		try {
			FileWriter fR = new FileWriter("Clubs.csv", true);
			BufferedWriter bR = new BufferedWriter(fR);
			bR.append(newClub.toCsv() + "\n");
			bR.close();
			fR.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public String getClubsCsv() {
		Iterator<Club> iter = clubList.iterator();
		Club c;

		String result = "clubID, clubName, email\n";
		while (iter.hasNext()) {
			c = iter.next();
			result += c.toCsv() + "\n";
		}
		return result;
	}

	// not finished
	public String getMembersLast14Days(String clubID) {
		return clubID;

	}

	public String updateMembershipFee(String membersID, double newFee) {
		Members m = getMembersByID(membersID);
		if (m == null)
			return "No such member";

		m.setMembershipFee(newFee);
		saveMembers();
		return "";
	}

	// update memebrs payment status
	public String updatePaymentStatus(String membersID, int newPaymentStatus) {
		Members m = getMembersByID(membersID);
		if (m == null)
			return "No such member";

		if (newPaymentStatus < 1 || newPaymentStatus > 3) {
			return "Invalid payment status value provided";
		}

		m.setPaymentStatus(newPaymentStatus);
		saveMembers();
		return "";
	}

	public String updateMemberVisitedToday(String membersID) {
		Members m = getMembersByID(membersID);
		if (m == null)
			return "No such member";

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(0);
		m.setVisitedLast(formatter.format(date));
		saveMembers();
		return "";
	}

	public String moveMembers(String membersID, String newClubID) {
		Members m = getMembersByID(membersID);
		if (m == null)
			return "No such member";

		Club c = getClubByID(newClubID);
		if (c == null) {
			return "The club id does not exist";
		}

		m.setClubID(newClubID);
		saveMembers();
		return "";
	}

	// not finished
	// private boolean isWithin14Days(String visitedLast) {
	// try {
	// DateFormat sourceFormat = new SimpleDateFormat("dd/mm/yyyy");
	// Date date = (Date) sourceFormat.parse(visitedLast);
	// Date now = new Date(0);
	// long diff = now.getTime() - date.getTime();
	// float days = (diff / (86400000));
	// return days <= 14;
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return false;
	// }

	public String getMembersPaidFeeCsv(String clubID) {
		Iterator<Members> iter = membersList.iterator();
		Members m;

		String result = "clubid,playerid,name,age,visited,fee,type,status\n";

		while (iter.hasNext()) {
			m = iter.next();
			if (m.getClubID().equals(clubID) && m.getPaymentStatus() == 1) {
				result += m.toPrintedCsv() + "\n";
			}
		}
		return result;
	}

	public String getMembersCsv(String clubID) {
		Iterator<Members> iter = membersList.iterator();
		Members m;

		String result = "clubID,playerID,name,age,visited,fee,type,status\n";

		while (iter.hasNext()) {
			m = iter.next();
			if (m.getClubID().equals(clubID)) {
				result += m.toPrintedCsv() + "\n";
			}
		}
		return result;
	}

	// get clubs by name types
	public Club getClubByName(String clubName) {
		Iterator<Club> iter = clubList.iterator();
		Club c;

		while (iter.hasNext()) {
			c = iter.next();
			if (c.getClubName().equals(clubName)) {
				return c;
			}
		}
		return null;
	}

	// retrieving using members id
	public Members getMembersByID(String membersID) {
		Iterator<Members> iter = membersList.iterator();
		Members m;

		while (iter.hasNext()) {
			m = iter.next();
			if (m.getPlayerID().equals(membersID)) {
				return m;
			}
		}
		return null;
	}

	// retrieving club by ID
	public Club getClubByID(String clubID) {
		Iterator<Club> iter = clubList.iterator();
		Club c;

		while (iter.hasNext()) {
			//
			c = iter.next();
			if (c.getClubID().equals(clubID)) {
				return c;
			}
		}
		return null;
	}

	// remove memebrs
	public String removeMembers(String deleteMembersID) {
		Iterator<Members> iter = membersList.iterator();
		Members m;

		while (iter.hasNext()) {
			m = iter.next();
			if (m.getPlayerID().equals(deleteMembersID)) {
				iter.remove();
			}
		}

		saveMembers();
		return "";
	}

	// adding memebrs to file
	private void addMembersToFile(Members newMembers) {
		try {
			FileWriter fR = new FileWriter("members.csv", true);
			BufferedWriter bR = new BufferedWriter(fR);
			bR.append(newMembers.toCsv() + "\n");
			bR.close();
			fR.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	// check if date entered is valid format etc..
	private boolean dateIsValid(String dateStr) {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	// check if membership type is valid
	private boolean membershipTypeIsValid(int membershipType) {
		return membershipType >= 1 && membershipType <= 5;
	}

	// check if payment status is valid
	private boolean paymentStatusIsValid(int paymentStatus) {
		return paymentStatus >= 1 && paymentStatus <= 5;
	}

	// update memebrshiptype
	public String updateMembershipType(String membersID, int newMembershipType) {
		Members m = getMembersByID(membersID);
		if (m == null) {
			return "No such member";
		}

		if (newMembershipType < 1 || newMembershipType > 5) {
			return "Invalid membership type value provided";
		}

		m.setMembershipType(newMembershipType);
		saveMembers();
		return "";
	}

	public List<Members> getMemberList() {
		return membersList;
	}

	public void setMemberList(List<Members> membersList) {
		this.membersList = membersList;
	}

	public List<Club> getClubList() {
		return clubList;
	}

	public void setClubList(List<Club> clubList) {
		this.clubList = clubList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private void log(String messgae) {
		System.out.println(getName() + ": " + message);

	}

	private void saveMembers() {
		try {
			FileWriter fR = new FileWriter("members.csv");
			BufferedWriter bR = new BufferedWriter(fR);
			bR.append("clubid,playerid,name,age,visited,fee,type,status\n");
			Iterator<Members> iter = membersList.iterator();
			Members m;

			while (iter.hasNext()) {
				m = iter.next();
				bR.append(m.toCsv() + "\n");
			}

			bR.close();
			fR.close();

		} catch (IOException e1) {

			e1.printStackTrace();
		}
	}

}
