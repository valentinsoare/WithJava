package WithJava.StudentEngagementStatistics.src.main.java.org.studentengagementstatistics;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class App {
    public static void main( String[] args ) {
        Course pymc = new Course("PYMC", "Python MasterClass", 40);
        Course jmc = new Course("JMC", "Java MasterClass", 64);
        Course cmc = new Course("CMC", "C MasterClass", 128);
        Course games = new Course("GIJ", "James In Java", 256);

        // how many male and female students are in the group ?

        long howManyFemales = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cmc))
                .limit(20)
                    .filter(e -> e.getGender().equals("Male"))
                        .count();

        long howManyMales = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cmc))
                        .limit(30)
                                .filter(e -> e.getGender().equals("Female"))
                                        .count();

        System.out.printf("%s", howManyMales);
        System.out.printf("%n%s", howManyFemales);

        //how many students fall into the three age ranges...

        long range30 = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cmc))
                .limit(40)
                    .filter(e -> e.getAge() >= 30 && e.getAge() <= 60)
                        .count();

        System.out.printf("%n%s", range30);

        //descriptive statistics on age

        var toUse = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cmc))
                .limit(30)
                    .mapToInt(Student::getAgeEnrolled)
                        .summaryStatistics();

        System.out.printf("%n%s", toUse);

        // what countries are the students from ?

        List<String> whereFrom = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cmc))
                .limit(50)
                    .map(Student::getCountryCode)
                        .distinct()
                            .sorted()
                                .toList();

        System.out.printf("%n%s", whereFrom);

        // are there students that are still active....more than 7 years

        long value = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cmc))
                .limit(30)
                    .filter(e -> e.getMonthsSinceActive() < 12 && e.getYearsSinceEnrolled() > 7)
                        .count();

        System.out.printf("%n%s", value);

        //---------------------------------------------------------------------------
        System.out.println();

        Random rnd = new Random();

        Stream.generate(() -> rnd.nextInt(501))
                .distinct()
                    .limit(50)
                        .sorted()
                            .forEach(e -> System.out.printf("%s ", e));

        //-----------------------------------------------------------------------------

        System.out.println("\n\n");

        var learners = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cmc))
                .limit(5)
                    .toArray(Student[]::new);

        System.out.printf("%n%s", Arrays.toString(learners));

        //------------------------------------------------------------------------------

        var learnersV2 = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cmc))
                .limit(100)
                        .toList();

        System.out.printf("%n%s", learnersV2);

        //-------------------------------------------------------------------------------
        System.out.println("\n");

        Set<Student> germanStudents = learnersV2.stream()
                .filter(e -> "DE".equalsIgnoreCase(e.getCountryCode()))
                    .collect(Collectors.toSet());

        Set<Student> underThirty = learnersV2.stream()
                        .filter(e -> e.getAge() < 30)
                                .collect(Collectors.toSet());

        System.out.printf("%n # number of german students in the set: %d%n" +
                        " # students under thirty years old: %d",
                germanStudents.size(), underThirty.size());

        Set<Student> youngGermans = new TreeSet<>(Comparator.comparing(Student::getStudentId));
        youngGermans.addAll(germanStudents);

        youngGermans.retainAll(underThirty);

        System.out.printf("%n%n%s", "Germans under thirty: ");
        youngGermans.forEach(e -> System.out.printf("%n%s", e));

        //-----------------------------------------------------------------------------

        System.out.printf("%n%n%s", "German under thirty only with streams: ");

        Set<Student> underThirtyGermansWithStreams = learnersV2.stream()
                .filter(e -> e.getAge() < 30 && "DE".equalsIgnoreCase(e.getCountryCode()))
                .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)),
                        TreeSet::add, TreeSet::addAll);

        underThirtyGermansWithStreams.forEach(e -> System.out.printf("%n%d %s", e.getStudentId(), e.getAge()));

        //----------------------------------------------------------------------

        String countryList = learnersV2.stream()
                .map(Student::getCountryCode)
                    .distinct()
                        .sorted()
                            .reduce("Countries:", (r, v) -> r + " " + v);

        System.out.printf("%n%s", countryList);

        //--------------------------------------------------------------------------

        List<Student> studentsToWorkWith = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cmc, games))
                .limit(5000)
                    .toList();

        var numberOfStudentsForPython = studentsToWorkWith.stream()
                .map(e -> e.getEngagementMap().get("PYMC").getPercentComplete())
                .reduce(0.0, Double::sum) / studentsToWorkWith.size();


        System.out.printf("%n%.2f%n%n", numberOfStudentsForPython);

        var almostCompleted = studentsToWorkWith.stream()
                .filter(e -> e.getEngagementMap().get("PYMC").getPercentComplete() > (int)(numberOfStudentsForPython * 1.25))
                .collect(Collectors.toSet());

//        System.out.printf("%n# Average percentage completed for Python MasterClass: %.2f%%",
//                (numberOfStudentsForPython.stream()
//                        .mapToDouble(i -> i).sum()) / numberOfStudentsForPython.size());

        System.out.println("\n\n\n");
        almostCompleted.forEach(e -> System.out.printf("%n%s", e));

        //--------------------------------------------------------------------------------

        List<Student> myStudents = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cmc, games))
                .limit(1000)
                .collect(Collectors.toList());

        Optional<Student> o1 = getStudent(myStudents, "third");

        System.out.printf("%n%s %s", o1.isEmpty(), o1.isPresent());

        o1.ifPresent(student -> System.out.printf("%n%s", student));

        o1.orElseGet(() -> Student.getRandomStudent(pymc, jmc, cmc, games));

        //--------------------------------------------------------------------------------------

        List<Student> working = Stream.generate(() -> Student.getRandomStudent(jmc, pymc, cmc, games))
                .limit(50)
                .toList();

//        int minAge = working.stream()
//                .mapToInt(Student::getAge)
//                .min().getAsInt();
//
//        System.out.println("\n");
//        System.out.printf("%n%s", minAge);

        //-------------------------------------------

        int mingAge = 21;

        working.stream()
                .filter(e -> e.getAge() <= mingAge)
                    .findAny()
                        .ifPresentOrElse(e -> System.out.printf("%n Student ID: %s", e.getStudentId()),
                                () -> System.out.printf("%n%s", "None! No records found!"));

        //-------------------------------------------

        working.stream()
                .filter(e -> e.getAge() <= mingAge)
                    .map(Student::getCountryCode)
                        .distinct()
                            .reduce((a, b) -> String.join(", ", a, b))
                                    .ifPresentOrElse(e -> System.out.printf("%n%s", e),
                                            () -> System.out.printf("%n%s", "None"));

        //-------------------------------------------

        working.stream()
                .map(e -> e.getCountryCode())
                    .distinct()
                        .map(e -> String.join(", ", e))
                            .filter(l -> l.contains("GB"))
                                .findAny()
                                    .ifPresentOrElse(e -> System.out.printf("%n%s", e),
                                            () -> System.out.printf("%n%s", "None Again!"));

        //-------------------------------------------

        var mappedStudents = working.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode));

        mappedStudents.forEach((k, v) -> System.out.printf("%n%s = %d", k, v.size()));

        //-------------------------------------------

         int minAge = 25;

         var youngerSet = working.stream()
                 .collect(Collectors.groupingBy(Student::getCountryCode,
                         Collectors.filtering(e -> e.getAge() < 25, Collectors.toList())));

        youngerSet.forEach((k, v) -> System.out.printf("%n%s -> %s", k, v));

        //--------------------------------------------

        var experienced = working.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() > 25));

        experienced.forEach((k, v) -> System.out.printf("%n%s -> %s", k, v));

        //--------------------------------------------

        var experiencedCount = working.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() > 25, Collectors.counting()));

        experiencedCount.forEach((k, v) -> System.out.printf("%n%s -> %s", k, v));

        //--------------------------------------------

        var multiLevel = working.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode,
                        Collectors.groupingBy(Student::getGender)));

        multiLevel.forEach((k, v) -> System.out.printf("%n%s %s", k, v));

        //--------------------------------------------

        int activeUnderThreeMNonths = working.stream()
                .collect(Collectors.partitioningBy(e -> e.getMonthsSinceActive() < 3))
                .get(true).size();

        System.out.printf("%n%s", activeUnderThreeMNonths);

        int numberOfStudents = experienced.values().stream()
                        .mapToInt(e -> e.size())
                                .sum();

        System.out.printf("%n%s", numberOfStudents);

        //---------------------------------------------

        long count = experienced.values().stream()
                .flatMap(l -> l.stream())
                    .filter(e -> e.getMonthsSinceActive() < 3)
                        .count();

        System.out.printf("%n -> counting: %s", count);

        //-------------------------------------------

        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream())
                .flatMap(e -> e.stream())
                .filter(e -> e.getAge() < 26)
                .count();

        System.out.printf("%n%s", count);




    }

    private static Optional<Student> getStudent(List<Student> listWithStudents, String type) {
        if (listWithStudents == null || listWithStudents.isEmpty()) return Optional.empty();

        if ("first".equalsIgnoreCase(type)) {
            return Optional.ofNullable(listWithStudents.get(0));
        } else if ("last".equalsIgnoreCase(type)) {
            return Optional.ofNullable(listWithStudents.get(listWithStudents.size() - 1));
        }

        return Optional.ofNullable(listWithStudents.get(new Random().nextInt(listWithStudents.size())));
    }
}
