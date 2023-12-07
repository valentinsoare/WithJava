package playing.theatreseating;

public class App {
    public static void main( String[] args ) throws InterruptedException {
        Theatre nationalTheatre = new Theatre("National Theatre Of Bucharest", 12, 120);

        System.out.printf("%s", nationalTheatre);

//        nationalTheatre.reserveASeat('E', "008");
//
//        nationalTheatre.multipleSeatsReservation("9", "A-C", "001-003");
        System.out.println(nationalTheatre);
    }
}
