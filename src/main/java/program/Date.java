package program;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class Date {
	private Month month;
	private int dateOfMonth;
	private int year;

	public Date(String dateString) {
		String[] dateArray = dateString.split(" ");
		if (dateArray.length != 3) {
			throw new DateException("Invalid date length");
		}
		setDateOfMonth(dateArray);
		setMonthOfYear(dateArray);
		setYear(dateArray);
		isValidDate();
	}

	public String getNextDate() {
		String nextDate = "";

		if (dateOfMonth < month.getNumberOfDaysInMonth(year)) {
			nextDate = (dateOfMonth + 1) + " " + month.getMonth() + " " + year;
		} else {
			String nextMonth = month.getNextMonth();
			nextDate = 1 + " " + nextMonth + " ";
			if (nextMonth.equals("JANUARY")) {
				nextDate += (year + 1);
			} else {
				nextDate += year;
			}
		}

		return nextDate;
	}

	public String getPreviousDate() {
		String previousDate = "";

		if (dateOfMonth != 1) {
			previousDate = (dateOfMonth - 1) + " " + month.getMonth() + " " + year;
		} else {
			String previousMonth = month.getPreviousMonth();
			previousDate = new Month(previousMonth).getNumberOfDaysInMonth(year) + " " + previousMonth + " ";
			if (previousMonth.equals("DECEMBER")) {
				previousDate += (year - 1);
			} else {
				previousDate += year;
			}
		}

		return previousDate;
	}

	public boolean isValidDate() {
		if (dateOfMonth > 0 && dateOfMonth <= month.getNumberOfDaysInMonth(year)) {
			return true;
		} else {
			throw new DateException("The date is invalid : The month : " + month.getMonth()
					+ " can have days between 1 - " + month.getNumberOfDaysInMonth(year) + " in year " + year);
		}
	}

	private void setDateOfMonth(String[] dateArray) {
		try {
			this.dateOfMonth = Integer.parseInt(dateArray[0]);
		} catch (NumberFormatException e) {
			throw new DateException("Invalid date of month " + dateOfMonth, e);
		}
	}

	private void setMonthOfYear(String[] dateArray) {
		if (dateArray[1].matches("[0-9]+")) {
			try {
				this.month = new Month(Integer.parseInt(dateArray[1]));
			} catch (NumberFormatException e) {
				throw new DateException("Invalid month number '" + dateArray[1]
						+ "' in Date. The month either integers between 1- 12 or string names like JANURAY or JAN", e);
			}
		} else {
			this.month = new Month(dateArray[1]);
		}
	}

	private void setYear(String[] dateArray) {
		if (dateArray[2].length() == 4) {
			try {
				this.year = Integer.parseInt(dateArray[2]);
			} catch (NumberFormatException e) {
				throw new DateException(
						"Invalid year in Date '" + year + "'. The year should have only integers like 1994 ,2000", e);
			}
		} else {
			throw new DateException(
					"Invalid year in Date'" + year + "'. the year in date should have 4 digits like 1994, 2000");
		}
	}

	public static void main(String[] args) {
		Date date = new Date("31 12 1994");
		System.out.println(date.getNextDate());
		System.out.println(date.getPreviousDate());
		Date.findNumberOfDays("1 2 1994", "27 2 1994");
		Date.findNumberOfDays("1 3 1994", "27 2 1994");
		Date.findNumberOfDays("30 4 1994", "31 3 1994");
		Date.findNumberOfDays("20 6 1994", "15 3 1994");
		Date.findNumberOfDays("20 12 1994", "15 1 1994");
		Date.findNumberOfDays("20 12 1994", "28 2 1994");
	}

	public static void findNumberOfDays(String dateString1, String dateString2) {
		int numberOfDays = 0;
		int numberOfMonths = 0;
		int numberOfYears = 0;
		Date date1 = new Date(dateString1);
		Date date2 = new Date(dateString2);

		
		if (date1.year == date2.year) {
			if (date1.month.getMonthOfYear() == date2.month.getMonthOfYear()) {
				numberOfDays = Math.abs(date1.dateOfMonth - date2.dateOfMonth);
			} else {
				numberOfDays = Math.abs(date1.dateOfMonth - date2.dateOfMonth);
				numberOfMonths = Math.abs(date1.month.getMonthOfYear() - date2.month.getMonthOfYear());
			}
		}
		
//		if (date1.year > date2.year) {
//			date2 = date1;
//			date1 = new Date(dateString2);
//		}
//
//		if (date1.dateOfMonth > date2.dateOfMonth) {
//			
//		}
//
//		int years = date2.year - date1.year;
		System.out.println("numberOfDays : " + numberOfDays + " numberOfMonths : " + numberOfMonths + " numberOfYears :" + numberOfYears);
	}

	
//	public void getNumberOfDaysBetweenMonth
}
