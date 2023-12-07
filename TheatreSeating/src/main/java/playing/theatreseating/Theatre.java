package WithJava.TheatreSeating.src.main.java.playing.theatreseating;

import java.util.*;

public class Theatre {
    private String theatreName;
    private int howManySeatsInARow;
    private int totalNumberOfRows;
    private int totalNumberOfSeats;
    private NavigableSet<Seat> seats;
    private int numberOfReservedSeats;

    public static class Seat implements Comparable<Seat> {
        private final char rowNumber;
        private final int seatNumber;
        private boolean isReserved;

        public Seat(char rowNumber, int seatNumber) {
            this.rowNumber = rowNumber;
            this.seatNumber = seatNumber;
            this.isReserved = false;
        }

        @Override
        public boolean equals(Object o) {
            boolean valueToReturn;

            if (this == o) {
                valueToReturn = true;
            } else if (o == null || getClass() != o.getClass()) {
                valueToReturn = false;
            } else {
                Seat seat = (Seat) o;

                if (rowNumber != seat.rowNumber) {
                    valueToReturn = false;
                } else {
                    valueToReturn = (seatNumber == seat.seatNumber);
                }
            }

            return valueToReturn;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + seatNumber;

            return result;
        }

        @Override
        public int compareTo(Seat o) {
            int valueToUseForComparison = Character.compare(this.rowNumber, o.rowNumber);

            if (valueToUseForComparison == 0) {
                valueToUseForComparison = Integer.compare(this.seatNumber, o.seatNumber);
            }

            return valueToUseForComparison;
        }

        @Override
        public String toString() {
            return String.format("%c%03d : %s", rowNumber, seatNumber, (isReserved) ? " ".repeat(4) + "\033[1;31mreserved\033[0m" : "\033[1mnot reserved\033[0m");
        }
    }

    public Theatre(String theatreName, int totalNumberOfRows, int totalNumberOfSeats) {
        this.theatreName = theatreName;
        this.howManySeatsInARow = (totalNumberOfSeats / totalNumberOfRows);
        this.totalNumberOfRows = totalNumberOfRows;
        this.totalNumberOfSeats = totalNumberOfSeats;
        this.seats = new TreeSet<>();
        this.numberOfReservedSeats = 0;

        for (int i = 0; i < totalNumberOfRows; i++) {
            for (int j = 0; j < howManySeatsInARow; j++) {
                seats.add(new Seat((char) (65 + i), (j + 1)));
            }
        }
    }

    public boolean reserveASeat(char rowNumber, String seatNumber) {
        Seat seatToBeReserved;
        boolean valueToReturn = false;

        String errorMessage = String.format("%nPlease use a seat between %s and %s!",
                seats.first().toString().split(":")[0].trim(),
                seats.last().toString().split(":")[0].trim());

        try {
            seatToBeReserved = new Seat(rowNumber, Integer.parseInt(seatNumber));
            Seat req = seats.floor(seatToBeReserved);

            if (req == null || req.seatNumber != seatToBeReserved.seatNumber) {
                System.out.printf("%s", errorMessage);
            } else {
                if (!req.isReserved) {
                    req.isReserved = true;
                    numberOfReservedSeats++;
                    valueToReturn = true;
                } else {
                    System.out.printf("%n%s", "Seat's already reserved!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.printf("%s", errorMessage);
        }

        return valueToReturn;
    }

    public void multipleSeatsReservation(String howManySeatsToReserve, String rangeOfRowsToBeChosen, String rangeOfSeats) throws InterruptedException{
        char[] rangeForRows = new char[rangeOfRowsToBeChosen.toCharArray().length];
        int numberOfSeatsToReserve;
        String[] seatsRangeGiven = new String[rangeOfSeats.split("-").length];
        boolean ifError = true;

        try {
            numberOfSeatsToReserve = Integer.parseInt(howManySeatsToReserve);
            rangeForRows = rangeOfRowsToBeChosen.toCharArray();
            int subtractBfromA = rangeForRows[2] - rangeForRows[0];

            seatsRangeGiven = rangeOfSeats.split("-");
            int subtractForSeatsBFromA = Integer.parseInt(seatsRangeGiven[1]) - Integer.parseInt(seatsRangeGiven[0]);

            if ((numberOfSeatsToReserve <= 0) || (numberOfSeatsToReserve > (totalNumberOfSeats - numberOfReservedSeats))) {
                System.out.printf("%n%s", String.format("Number of seats to reserve can be between 1 and %d!", totalNumberOfSeats - numberOfReservedSeats));
            } else if (subtractBfromA < 0 || subtractBfromA > totalNumberOfRows - 1) {
                System.out.printf("%n%s", String.format("Please use a range for rows between %c and %c", (char)65, (char)(65 + totalNumberOfRows)));
            } else if (subtractForSeatsBFromA <= 0 || subtractForSeatsBFromA > howManySeatsInARow) {
                System.out.printf("%n%s", String.format("Range for seats in a row can be between 0 and %d", howManySeatsInARow));
            } else {
                ifError = false;
            }

            if (ifError) {
                Thread.sleep(2000);
                System.out.println("\033[H\033[2J");
                System.out.flush();
            }
        } catch (NumberFormatException e) {
            System.out.printf("%n%s", "Please use as arguments only strings. First argument should be how many seats to reserve, then a range of rows to be chosen (these rows separated by a -, Ex: A-C), and the last argument needs to be a range of seats (Ex: 002-010)");
            Thread.sleep(2000);
            System.out.println("\033[H\033[2J");
            System.out.flush();
        }

        for (int i = rangeForRows[0]; i <= rangeForRows[2]; i++) {
            for (int j = Integer.parseInt(seatsRangeGiven[0]); j <= Integer.parseInt(seatsRangeGiven[1]); j++) {
                reserveASeat((char)i, String.valueOf(j));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        String header = String.format("%nName Of The Theatre: %s%nTotalNumberOfSeats: %7d%nTotal Number Of Rows: %5d%nHow Many Seats In A Row: %d%n", theatreName, totalNumberOfSeats, totalNumberOfRows, howManySeatsInARow);
        toReturn.append(header).append("\n\t\t\t* = SEATS = * \n").append("-".repeat(40)).append("\n");

        int count = 0;
        Iterator<Seat> iterator = seats.iterator();
        int halfOfHowManySeatsInARow = howManySeatsInARow / 2;

        while (iterator.hasNext()) {
            Seat element = iterator.next();

            if ((count + 1) % halfOfHowManySeatsInARow == 0) {
                toReturn.append(element).append("\n");
            } else {
                toReturn.append(element).append(", ");
            }

            count++;
        }

        if (count == 0) {
            toReturn.append(String.format("%32s%n", "There is no reserved seat!"));
        }

        toReturn.append("-".repeat(40)).append("\n");
        return toReturn.toString().stripTrailing();
    }
}
