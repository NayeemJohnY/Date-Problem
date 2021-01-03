package program;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Month {

	private enum MONTH {
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
	}

	private MONTH month;
	List<String> listOfMonthNames;

	public Month(String monthName) {
		this.listOfMonthNames = Stream.of(MONTH.values()).map(Enum::toString).collect(Collectors.toList());
		setMonth(monthName);
	}

	public Month(int monthNo) {
		this.listOfMonthNames = Stream.of(MONTH.values()).map(Enum::toString).collect(Collectors.toList());
		setMonth(monthNo);
	}

	public void setMonth(String monthName) {
		try {
			month = MONTH.valueOf(monthName.toUpperCase());
		} catch (IllegalArgumentException e) {
			Optional<String> optionalMatchedMonth = listOfMonthNames.stream()
					.filter(name -> name.startsWith(monthName.toUpperCase())).findAny();
			if (optionalMatchedMonth.isPresent()) {
				month = MONTH.valueOf(optionalMatchedMonth.get());
			} else {
				throw new DateException("Invalid Month Name : " + monthName, e);
			}
		}
	}

	public void setMonth(int monthNo) {
		if (monthNo - 1 < listOfMonthNames.size()) {
			setMonth(listOfMonthNames.get(monthNo - 1));
		} else {
			throw new DateException("Invalid Month Number " + monthNo);
		}

	}

	public String getMonth() {
		return month.toString();
	}

	public int getMonthOfYear() {
		return month.ordinal() + 1;
	}

	public int getNumberOfDaysInMonth(int year) {
		int numberOfDays = 0;
		switch (month) {
		case FEBRUARY:
			numberOfDays = isLeapyear(year) ? 29 : 28;
			break;
		case JANUARY:
		case MARCH:
		case MAY:
		case JULY:
		case AUGUST:
		case OCTOBER:
		case DECEMBER:
			numberOfDays = 31;
			break;
		default:
			numberOfDays = 30;
			break;
		}

		return numberOfDays;
	}

	public String getNextMonth() {
		return getMonthOfYear() == 12 ? listOfMonthNames.get(0) : listOfMonthNames.get(getMonthOfYear());
	}

	public String getPreviousMonth() {
		return getMonthOfYear() == 1 ? listOfMonthNames.get(listOfMonthNames.size() - 1)
				: listOfMonthNames.get(getMonthOfYear() - 2);
	}

	private boolean isLeapyear(int year) {
		boolean leapyear = false;

		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					leapyear = true;
				}
			} else {
				leapyear = true;
			}
		}
		return leapyear;
	}
}
