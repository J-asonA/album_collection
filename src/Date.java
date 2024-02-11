import java.util.Calendar;

public class Date implements Comparable<Date> {
    private final int year;
    private final int month;
    private final int day;

    // Constants for leap year calculation
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Getter for year
    public int getYear() {
        return year;
    }

    // Getter for month
    public int getMonth() {
        return month;
    }

    // Getter for day
    public int getDay() {
        return day;
    }

    // Implementing isValid() method to check if the date is a valid calendar date
    public boolean isValid() {
        // Check if the month is valid

        if (month < 1 || month > 12) {
            return false;
        }

        // Check if the day is valid for the given month
        if (day < 1 || day > daysInMonth(year, month)) {
            return false;
        }

        if(year < 1900){
            return false;
        }

        // Check if the year is in the future
        Calendar current = Calendar.getInstance();
        if (year > current.get(Calendar.YEAR)) {
            return false;
        } else if (year == current.get(Calendar.YEAR)) {
            // If the year is the same as the current year, check the month
            if (month > current.get(Calendar.MONTH) + 1) {
                return false;
            } else if (month == current.get(Calendar.MONTH) + 1) {
                // If the month is the same as the current month, check the day
                if (day > current.get(Calendar.DAY_OF_MONTH)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Helper method to determine the number of days in a month
    private int daysInMonth(int year, int month) {

        switch (month -1) {
            case Calendar.FEBRUARY:
                return isLeapYear(year) ? 29 : 28;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return 30;
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST :
            case Calendar.OCTOBER :
            case Calendar.DECEMBER :
                return 31;
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    // Helper method to check if the year is a leap year
    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            }
            return true;
        }
        return false;
    }

    // Implementing the compareTo() method from Comparable interface

    //Integer.compare returns 0 if (x==y), if (x<y) <0, if (x>y)>0
    @Override
    public int compareTo(Date other) {
        if (year != other.year) {
            return Integer.compare(year, other.year);
        } else if (month != other.month) {
            return Integer.compare(month, other.month);
        } else {
            return Integer.compare(day, other.day);
        }
    }

    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }


    // Testbed main method to test the isValid() method
    public static void main(String[] args) {
        Date date1 = new Date(2024, 2, 9);
        Date date2 = new Date(2024, 2, 29);
        Date date3 = new Date(2023, 2, 29);
        Date date4 = new Date(2024, 4, 31);
        Date date5 = new Date(2022, 13, 1);

        System.out.println("Date 1 is valid: " + date1.isValid()); // Should be true
        System.out.println("Date 2 is valid: " + date2.isValid()); // Should be true
        System.out.println("Date 3 is valid: " + date3.isValid()); // Should be false (2023 is not a leap year)
        System.out.println("Date 4 is valid: " + date4.isValid()); // Should be false (April doesn't have 31 days)
        System.out.println("Date 5 is valid: " + date5.isValid()); // Should be false (Invalid month)
    }
}