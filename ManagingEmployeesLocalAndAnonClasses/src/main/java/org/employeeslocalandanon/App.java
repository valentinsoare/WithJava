package org.employeeslocalandanon;

import java.util.List;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.ArrayList;

public class App {
    public static void actOnWorkers(List<Employee> workers, String sortField) {
        class ExperiencedEmployee {
            private Employee worker;
            private String fullName;
            private Integer yearsWorked;

            public ExperiencedEmployee(Employee employee) {
                this.worker = employee;
                this.fullName = worker.getFirstName() + " " + worker.getLastName();
                this.yearsWorked = LocalDate.now().getYear() - worker.getYearHired();
            }

            public Integer getYearsWorked() {
                return yearsWorked;
            }

            @Override
            public String toString() {
                return String.format("%-14s%s", worker.toString(), getYearsWorked());
            }
        }

        List<ExperiencedEmployee> calculatedYears = new ArrayList<>();

        for (Employee element : workers) {
            calculatedYears.add(new ExperiencedEmployee(element));
        }

        Comparator<ExperiencedEmployee> toSortEmployees = new Comparator<>() {
            @Override
            public int compare(ExperiencedEmployee o1, ExperiencedEmployee o2) {
                if ("yearsworked".equalsIgnoreCase(sortField)) {
                    return Integer.compare(o1.yearsWorked, o2.yearsWorked);
                }

                return o1.fullName.compareTo(o2.fullName);
            }
        };

        calculatedYears.sort(toSortEmployees);
        System.out.printf("%n%s%s", " Sorted by years worked: ", calculatedYears);
    }

    public static void main( String[] args ) {
        List<Employee> workers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            workers.add(new Employee());
        }

        actOnWorkers(workers, "yearsworked");
    }
}
