package WithJava.EmployeesWithNested.src.main.java.playing.employees;

import java.util.*;

public class App {
    public static List<Object> addPigLatinName(List<? extends StoreEmployee> givenList) {
        class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {
            private String pigLatinName;
            private StoreEmployee originalInstance;

            public DecoratedEmployee(String pigLatinName, StoreEmployee originalInstance) {
                this.pigLatinName = pigLatinName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                return String.format("%s %s %s %s", originalInstance.getStore(), originalInstance.getEmployeeID(), pigLatinName, originalInstance.getYearStarted());
            }

            @Override
            public int compareTo(DecoratedEmployee o) {
                return this.pigLatinName.compareTo(o.pigLatinName);
            }
        }

        List<DecoratedEmployee> listWithMods = new ArrayList<>(givenList.size());

        String pigLatin, name;

        for (StoreEmployee element : givenList) {
            name = element.getName().toLowerCase();
            pigLatin = name.substring(1) + name.charAt(0) + "ay";
            listWithMods.add(new DecoratedEmployee((pigLatin.toUpperCase().charAt(0) + pigLatin.substring(1)), element));
        }

        listWithMods.sort(null);

        return Collections.singletonList(listWithMods);
    }

    public static <T> void sortIt(List<T> givenList, Comparator<? super T> comparator) {
        givenList.sort(comparator);

        for (T element : givenList) {
            System.out.println(element);
        }
    }

    public static void main( String[] args ) {
        List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee("Valentin Soare", 1005, 2017),
                new Employee("Andreea Popescu", 1006, 2018), new Employee("Gabriela Manea", 1007, 2020)));

//        Comparator<Employee> toCompareEmployee = new EmployeeComparator<>();
//        employees.sort(toCompareEmployee);

        employees.sort(new Employee.EmployeeComparator<>("yearstarted"));

        System.out.println(employees);

        System.out.printf("%n%s%n", " Store Members");

        List<StoreEmployee> newEggEmployees = new ArrayList<>(List.of(
                new StoreEmployee("Valentin Soare", 1009, 2019, "Emag"),
                new StoreEmployee("Gigel de la Scularie", 1089, 2021, "PcGarage"),
                new StoreEmployee("Magnificul", 777, 2020, "EvoMag"),
                new StoreEmployee("PreaPretiosul", 999, 2001, "FashionDays")));

        Comparator<StoreEmployee> toCompareStore = new StoreEmployee().new StoreComparator<>();
        newEggEmployees.sort(toCompareStore);

        System.out.println(newEggEmployees);

        List<Object> employeesWithCoolNames = addPigLatinName(newEggEmployees);

        System.out.println(employeesWithCoolNames.get(0));

        Comparator <StoreEmployee> c4 = new Comparator<>() {

            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }
}
