package playing.employees;

import java.util.Comparator;

public class Employee {
    public static class EmployeeComparator <T extends Employee>
            implements Comparator<Employee> {

        private String sortType;

        public EmployeeComparator() {
            this("name");
        }

        public EmployeeComparator(String sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(Employee o1, Employee o2) {

            if ("yearStarted".equalsIgnoreCase(sortType)) {
                return o1.yearStarted - o2.yearStarted;
            } else if ("employeeID".equalsIgnoreCase(sortType)) {
                return o1.employeeID - o2.employeeID;
            }

            return o1.name.compareTo(o2.name);
        }
    }

    private String name;
    private Integer employeeID;
    private Integer yearStarted;

    public Employee(String name, Integer employeeID, Integer yearStarted) {
        this.name = name;
        this.employeeID = employeeID;
        this.yearStarted = yearStarted;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public Integer getYearStarted() {
        return yearStarted;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    @Override
    public String toString() {
        return String.format("%d %5s %1d", employeeID, name, yearStarted);
    }


    public static void main(String[] args) {
        Employee valentinSoare = new Employee("Valentin Soare", 1005, 2019);

        System.out.println(valentinSoare);
    }

}
