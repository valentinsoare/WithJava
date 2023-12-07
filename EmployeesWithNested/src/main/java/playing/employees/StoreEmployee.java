package WithJava.EmployeesWithNested.src.main.java.playing.employees;

import java.util.Comparator;

public class StoreEmployee extends Employee {
    private String store;

    public StoreEmployee() {
    }

    public StoreEmployee(String name, Integer employeeID, Integer yearStarted, String store) {
        super(name, employeeID, yearStarted);
        this.store = store;
    }

    public String getStore() {
        return store;
    }

    @Override
    public String toString() {
        return String.format("%s %s", store, super.toString());
    }

    public class StoreComparator <T extends StoreEmployee> implements Comparator<StoreEmployee> {

        @Override
        public int compare(StoreEmployee o1, StoreEmployee o2) {
            int result = o1.store.compareTo(o2.store);

            if (result == 0) {
                return new Employee.EmployeeComparator<>("yearStarted").compare(o1, o2);
            }

            return result;
        }
    }
}
