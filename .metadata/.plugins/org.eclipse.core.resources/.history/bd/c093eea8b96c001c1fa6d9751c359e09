package ie.gmit.sw;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class serverThread extends Thread {

	private Socket incomingConnection;
	ObjectOutputStream out;
	ObjectInputStream in;
	String resultMessage;
	String message;

	String clubName, clubID, email;
	int option;
	int suboption;
	String loginClubName, loginClubID;
	boolean loggedIn = false;
	boolean registered;
	// int login = 0;
	boolean result;
	// SharedExample example;
	ClubList list;

	// ArrayList<String> ClubId = new ArrayList<String>();
	// ArrayList<String> ClubName = new ArrayList<String>();
	// ArrayList<String> LoginClubName = new ArrayList<String>();
	// ArrayList<String> LoginClubId = new ArrayList<String>();
	// ArrayList<Club> club = new ArrayList<Club>();

	public serverThread(Socket s, ClubList listobj) {
		incomingConnection = s;
		list = listobj;
		list.setName(getName());
	}

	public void run() {

		System.out.println("New Thread Started");

		try {
			out = new ObjectOutputStream(incomingConnection.getOutputStream());
			in = new ObjectInputStream(incomingConnection.getInputStream());
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}

		// conversation with client
		try {
			do {
				sendMessage("Please enter 1 to Register or 2 to Sign In");

				message = (String) in.readObject();

				if (message.equals("1")) {
					// Register conversation.....
					sendMessage("Please enter the ClubName");
					clubName = (String) in.readObject();

					sendMessage("Please enter the ClubID");
					clubID = (String) in.readObject();

					sendMessage("Please enter the Email");
					email = (String) in.readObject();

					String resultStr = list.addClub(clubName, clubID, email);

					if (resultStr != "") {
						resultMessage = "Sorry unable to Register the club, error = " + resultStr + "\n";
					} else {
						resultMessage = "The club has been registered successfully\n";
					}
				}

				else if (message.equals("2")) {
					// Sign In Conversation, existing club
					sendMessage("Please enter the Club Name");
					loginClubName = (String) in.readObject();

					sendMessage("Please enter the Club ID");
					loginClubID = (String) in.readObject();

					/*
					 * if(loginEmail.compareTo(email)==0 && loginPassword.compareTo(password)==0) {
					 * login=1; }
					 */

					result = list.login(loginClubName, loginClubID);

					if (result == true) {
						sendMessage("TRUE");
						loggedIn = true;
					}

					else {
						sendMessage("FALSE");
					}
				}

			} while (message.equals("1") || result == false);

			// The user is sign in ....

			do {
				printOptions();
				String optionStr = (String) in.readObject();
				option = Integer.parseInt(optionStr);

				if (hasSubOptions(option)) {
					printSubOptions(option);
					String suboptionStr = (String) in.readObject();
					suboption = Integer.parseInt(suboptionStr);
				}

				switch (option) {
				case 1:

					// adding a new memeber
					sendMessage("Please enter the name of the member you wish to add");
					String memberName = (String) in.readObject();

					sendMessage("Please enter the Age of the member");
					message = (String) in.readObject();
					int age = Integer.parseInt(message);

					sendMessage("Please enter the ID of the Player");
					message = (String) in.readObject();
					String pID = (String) in.readObject();

					sendMessage(
							"Please enter the date the member last visited the club, must be in the format dd/mm/yyyy");
					String visitedLast = (String) in.readObject();

					sendMessage("Please enter the membership fee that applies");
					message = (String) in.readObject();
					double fee = Double.parseDouble(message);

					sendMessage(
							"Please enter the number that applies for the new membrship type you want to use picking from the list below\n"
									+ "1 => Adult\n2 => Senior\n3 => Junior\n");
					message = (String) in.readObject();
					int membershipType = Integer.parseInt(message);

					sendMessage(
							"Please enter the new payment status to set for this member choose from the list below\n"
									+ "1 => Paid\n2 => Part Paid\n3 => Not Paid\n");
					message = (String) in.readObject();
					int paymentStatus = Integer.parseInt(message);

					String res = list.addMembers(loginClubID, memberName, age, visitedLast, fee, membershipType,
							paymentStatus);
					if (res == "") {
						sendMessage("Member Successfully Added");
					} else {
						sendMessage("Cannot add member due to an error: " + res);
					}
					break;

				case 2:
					// updating a member
					sendMessage("Please enter the Membership ID of the Member you Wish to update");
					String memberID = (String) in.readObject();

					switch (suboption) {
					case 1:

						// update membership type
						sendMessage(
								"Please Enter the number that applies for membrship type you want to use, choosing from the list below\n"
										+ "1 => Adult\n2 => Senior\n3 => Junior\n");
						message = (String) in.readObject();
						int newMembershipType = Integer.parseInt(message);
						res = list.updateMembershipType(memberID, newMembershipType);
						if (res == "") {
							sendMessage("New Membership type Set");
						} else {
							sendMessage("Unable to update membership type due to an error: " + res);
						}
						break;

					case 2:
						// update membership fee
						sendMessage("Please enter the new membership fee that you want to assign");
						message = (String) in.readObject();
						double newFee = Double.parseDouble(message);
						res = list.updateMembershipFee(memberID, newFee);
						if (res == "") {
							sendMessage("New Member fee set");
						} else {
							sendMessage("Unable to set membership fee due to an error: " + res);
						}
						break;

					case 3:
						// update members payment status
						sendMessage("Please enter the new payment status to Set , Choose from the list below\n"
								+ "1 => Paid\n2 => Partialy Paid\n3 => Not Paid\n");
						message = (String) in.readObject();
						int newPaymentStatus = Integer.parseInt(message);
						res = list.updatePaymentStatus(memberID, newPaymentStatus);
						if (res == "") {
							sendMessage("New Member payment Stat Set");
						} else {
							sendMessage("Unable to set payment status due to error: " + res);
						}
						break;

					case 4:
						// update members visited details
						res = list.updateMemberVisitedToday(memberID);
						if (res == "") {
							sendMessage("Member updated");
						} else {
							sendMessage("Unable to update member due to error: " + res);
						}
						break;

					case 5:
						// move member to new club
						sendMessage("Please enter the ID of the club you wish to move the member to: ");
						String newClubID = (String) in.readObject();
						res = list.moveMembers(memberID, newClubID);
						if (res == "") {
							sendMessage("Member moved to new club");
						} else {
							sendMessage("Unable to move member due to error: " + res);
						}
						break;

					default:
						sendMessage("Sorry do not recognise selected option");
					}
					break;

				case 3:
					// search club members list
					if (suboption == 1) {
						// seacrh all members visited in last 14 days
						// sendMessage(list.getMembersVisit14Csv(loginClubID));
					} else if (suboption == 2) {
						// list all paid members
						sendMessage(list.getMembersPaidFeeCsv(loginClubID));
					} else if (suboption == 3) {
						// list all members
						sendMessage(list.getMembersCsv(loginClubID));
					} else {
						sendMessage("Sorry, selected option is not recognized");
					}
					break;

				case 4:
					// delete a member
					sendMessage("Please enter the membership id of the member you want to delete");
					String deleteMemberID = (String) in.readObject();
					res = list.removeMembers(deleteMemberID);
					if (res == "") {
						sendMessage("Member successfully moved to new club");
					} else {
						sendMessage("Unable to move member due to an error: " + res);
					}
					break;

				case 5:
					// Search all registered clubs
					sendMessage(list.getClubsCsv());
					break;

				case 6:
					// logout
					loggedIn = false;
					System.out.println("Logging out");
					break;
				}

			} while (loggedIn);

			sendMessage("User logged out");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				in.close();
				out.close();
				incomingConnection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// suboptions for menu
	private void printSubOptions(int option2) {
		switch (option) {
		case 2:
			sendMessage("1 = Update Membership Type for Member\n2 = Update Membership Fee for Member\n"
					+ "3 = Update Members Payment Status\n4 = Update Member To Indicate that they have visited club today\n"
					+ "5 = Move Member To New Club\n");
			break;
		case 3:
			sendMessage(
					"1 = Search for all members who visited in the last 14 days\n2 = Search all members who paid their membership fee\n"
							+ "3 = List all members in your club\n");

			break;
		default:
			break;
		}

	}

	// setting hassuboptions
	private boolean hasSubOptions(int option2) {
		return option2 == 2 || option2 == 3;
	}

	// printing options
	private void printOptions() {
		sendMessage("Please choose from the following options:\n 1 = Add New Member\n 2 = Update Member\n"
				+ "3 = Search Club Members List\n4 = Delete A Member\n5 = Search All Registered Clubs\n6 = LogOut\n");
	}

	private void sendMessage(String message) {
		try {
			log("Sending message: " + message);
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void log(String string) {
		// TODO Auto-generated method stub

	}
}