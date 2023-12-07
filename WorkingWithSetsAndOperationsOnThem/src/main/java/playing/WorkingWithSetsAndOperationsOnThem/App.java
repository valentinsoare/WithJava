package playing.WorkingWithSetsAndOperationsOnThem;

import java.util.*;

public class App {
    public static List<Set<Task>> addAllSets() {
        List<String> listWithEmployees = new ArrayList<>(Arrays.asList("ann", "bob", "carol"));

        List<Set<Task>> allSetOfTasks = new ArrayList<>(listWithEmployees.size());

        for (String listWithEmployee : listWithEmployees) {
            allSetOfTasks.add(TaskData.getTasks(listWithEmployee));
        }

        return allSetOfTasks;
    }

    public static Set<Task> getUnion(List<Set<Task>>allSetsWithTasks) {
        Set<Task> initialSetOfTasks = new HashSet<>();

        for (Set<Task> allSetsWithTask : allSetsWithTasks) {
            initialSetOfTasks.addAll(allSetsWithTask);
        }

        return initialSetOfTasks;
    }

    public static Set<Task> getIntersect(Set<Task> tasksA, Set<Task> tasksB) {
        Set<Task> setToBeReturn = new HashSet<>(tasksA);

        setToBeReturn.containsAll(tasksB);

        Set<Task> tasksForOneEmployee = new HashSet<>();

        for (Task e : setToBeReturn) {
            if (e.getAssignee() != null) {
                tasksForOneEmployee.add(e);
            }
        }

        return tasksForOneEmployee;
    }

    public static void main( String[] args ) {
        List<Set<Task>> allSetsToWorkWith = addAllSets();

        System.out.printf("%n%s%s%n%n", " ".repeat(3), "All tasks unsorted from manager and those assigned to employees: ");
        Set<Task> ourTasks = getUnion(allSetsToWorkWith);

        ourTasks.forEach(System.out::println);

    }
}
