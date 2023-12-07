package playing.university;

import java.util.*;

public class App {
    enum WeekDay {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();

//    private static void addPurchase(String studentName, Course course, double price) {
//        Student existingStudent = students.get(studentName);
//        if (existingStudent == null) {
//            existingStudent = new Student(studentName, course);
//            students.put(studentName, existingStudent);
//        } else {
//            existingStudent.addCourse(course);
//        }
//
//        int day = new Random().nextInt(1, 15);
//        String key = course.courseId() + "_" + existingStudent.getId();
//        int year = LocalDate.now().getYear();
//
//        Purchase purchase = new Purchase(course.courseId(), existingStudent.getId(), price,
//                year, day);
//
//        purchases.put(key, purchase);
//    }

    public static void main( String[] args ) {
//        Course pymc = new Course("py101", "Python Master Class", "Python");
//        Course jmc = new Course("jmc101", "Java Master Class", "Java");
//
//        addPurchase("John Smith", pymc, 441.23);
//        addPurchase("Carol Snow", jmc, 399.00);
//        addPurchase("Eduard Snowden", pymc, 312.44);
//        addPurchase("Eduard Maya", jmc, 250.00);
//        addPurchase("Stan Man", jmc, 423.11);
//        addPurchase("Eduard Snowden", jmc, 1250.00);
//        addPurchase("Carol Snow", pymc, 44);
//        addPurchase("Stan Man", pymc, 120);
//
//        students.forEach((k, v) -> System.out.printf("%n%s: %s", k, v));
//        System.out.println("\n\n");
//        purchases.forEach((k, v) -> System.out.printf("%n%s", v));
//
//        NavigableMap<LocalDate, List<Purchase>> purchasesOnSpecificDate = new TreeMap<>();
//
//        for (Purchase p : purchases.values()) {
//            purchasesOnSpecificDate.compute(p.purchaseDate(),
//                    (pdate, plist) -> {
//                                        List<Purchase> list =
//                                            (plist == null) ? new ArrayList<>() : plist;
//                                        list.add(p);
//                                       return list;
//                                    });
//        }
//
//        System.out.printf("%n%s", "-".repeat(90));
//        purchasesOnSpecificDate.forEach((k, v) -> System.out.printf("%n%s: %s", k, v));
//
//
//        int currentYear = LocalDate.now().getYear();
//        LocalDate firstDay = LocalDate.ofYearDay(currentYear, 1);
//
//        LocalDate week1 = firstDay.plusDays(4);
//
//        Map<LocalDate, List<Purchase>> fourDaysPurchases = purchasesOnSpecificDate.headMap(week1);
//
//        System.out.printf("%n%s%n", "-".repeat(90));
//        System.out.println(fourDaysPurchases);
//
//        System.out.printf("%n%s%n", "-".repeat(90));
//
//        LocalDate lowerLimit = LocalDate.ofYearDay(currentYear, 8);
//        LocalDate UpperLimit = lowerLimit.plusDays(7);
//
//        Map<LocalDate, List<Purchase>> secondWeekPurchases = purchasesOnSpecificDate.tailMap(lowerLimit, true);
//        System.out.println(secondWeekPurchases);
//
//        System.out.println(purchasesOnSpecificDate.lastEntry());
//        System.out.println(purchasesOnSpecificDate.firstEntry());
//
////        System.out.println(purchasesOnSpecificDate.pollLastEntry());
////        System.out.println(purchasesOnSpecificDate.pollFirstEntry());
//
//        var reversed = purchasesOnSpecificDate.descendingMap();
//
//        System.out.println(reversed);
//
//        //------------------------------
//        List<WeekDay> annWorkDays = new ArrayList<>(Arrays.asList(WeekDay.MONDAY, WeekDay.TUESDAY, WeekDay.THURSDAY, WeekDay.FRIDAY));
//
//        EnumSet<WeekDay> annDaysSet = EnumSet.copyOf(annWorkDays);
//        var allDaysSet = EnumSet.allOf(WeekDay.class);
//
//        System.out.println(annDaysSet);
//        System.out.println(allDaysSet);
//
//        Set<WeekDay> newPersonDays = EnumSet.complementOf(annDaysSet);
//        System.out.println(newPersonDays);
//
//        Set<WeekDay> businessDays = EnumSet.range(WeekDay.MONDAY, WeekDay.FRIDAY);
//        System.out.println(businessDays);
//
//
//        //----------------------------------
//        Map<WeekDay, String[]> employeeMap = new EnumMap<>(WeekDay.class);
//
//        employeeMap.put(WeekDay.MONDAY, new String[]{"Ann", "Smith", "Bob"});
//        //-----------------------------------
//
//        List<Integer> list = new ArrayList<>(Arrays.asList(4, 10, 2, 3, 4, 22));
//
//        System.out.println(list);
//
//        Collections.reverse(list);
//
//        System.out.println(list);
//
//        LinkedList<Integer> numbers = new LinkedList<>();
//
//        numbers.addFirst(23);
//
//        System.out.println(numbers);
//
//        numbers.addLast(44);
//        numbers.addLast(101);
//        numbers.addLast(11);
//
//        numbers.addFirst(4_000);
//
//        numbers.removeFirstOccurrence(44);
//        numbers.removeFirst();
//
//        Iterator<Integer> iterator = numbers.descendingIterator();
//
//        while (iterator.hasNext()) {
//            System.out.printf("%n%s", iterator.next());
//        }

        //-------------------------------------

//        ArrayDeque<String> firstNames = new ArrayDeque<>(); //stack not sync
//        Queue<String> surNames = new ArrayDeque<>(); //queue
//
//        firstNames.push("Valentin");
//        firstNames.push("Tudorina");
//        firstNames.push("Stelian");
//
//        String first = firstNames.pop();
//
//        System.out.println(first);
//
//        Iterator<String> iteratorForNames = firstNames.iterator();
//
//        while (!firstNames.isEmpty()) {
//            System.out.println(firstNames.pop());
//        }
        //---------------------------------------------------
//        Queue<String> surNames = new LinkedList<>();
//
//        surNames.offer("Soare");
//        surNames.offer("Xulescu");
//        surNames.add("Aiurea");
//
//        System.out.println(surNames);
//
//        Iterator<String> iterator = surNames.iterator();
//
//        while (iterator.hasNext()) {
//            String item = iterator.next();
//            System.out.printf("%n%s", item);
//        }
        //-------------------------------------------------------
        Queue<Student> listOfStudents = new PriorityQueue<>();

        listOfStudents.offer(new Student("Valentin", new Course("java101", "Java 101", "Java")));
        listOfStudents.offer(new Student("Andreea", new Course("python501", "Python Advanced", "Python")));
        listOfStudents.offer(new Student("Adrian", new Course("cpp201", "C++ fundamentals", "C++")));

        System.out.println(listOfStudents);

        while (listOfStudents.peek() != null) {
            System.out.println(listOfStudents.poll());
        }

        //-------------------------------------------------------
        Deque<Integer> numbers = new ArrayDeque<>();
        numbers.offer(23);
        numbers.offerLast(41);
        numbers.offerFirst(10);

        System.out.println(numbers);

    }
}
