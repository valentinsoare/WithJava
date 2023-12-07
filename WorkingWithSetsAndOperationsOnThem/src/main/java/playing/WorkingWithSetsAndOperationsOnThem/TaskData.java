package playing.WorkingWithSetsAndOperationsOnThem;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

public class TaskData {
    private static String tasks;
    private static String annTasks;
    private static String bobTasks;
    private static String carolTasks;

    static {
        tasks = """
                Infrastructure, Logging, High
                Infrastructure, DB Access, Medium
                Infrastructure, Security, High
                Infrastructure, Password Policy, Medium
                Data Design, Task Table, Medium
                Data Design, Employee Table, Medium
                Data Design, Cross Reference Tables, High
                Data Design, Encryption Policy, High
                Data Access, Write Views, Low
                Data Access, Set Up Users, Low
                Data Access, Set Up Access Policy, Low
                """;

        annTasks = """
                Infrastructure, Security, High, In Progress
                Infrastructure, Password Policy, Medium, In Progress
                Research, Cloud Solutions, Medium, In Progress
                Data Design, Encryption Policy, High
                Data Design, Project Table, Medium
                Data Access, Write Views, Low, In Progress
                """;

        bobTasks = """
                Infrastructure, Security, High, In Progress
                Infrastructure, Password Policy, Medium
                Data Design, Encryption Policy, High
                Data Access, Write Views, Low, In Progress
                """;

        carolTasks = """
                Infrastructure, Logging, High, In Progress
                Infrastructure, DB Access, Medium
                Infrastructure, Password Policy, Medium
                Data Design, Task Table, High
                Data Access, Write Views, Low
                """;
    }

    public static Set<Task> getTasks(String owner) {
        Set<Task> tasksList = new HashSet<>();

        String user = ("ann,bob,carol".contains(owner.toLowerCase())) ? owner : null;

        String selectedList = switch (owner.toLowerCase()) {
            case "ann" -> annTasks;
            case "bob" -> bobTasks;
            case "carol" -> carolTasks;
            default -> tasks;
        };

        for (String e : selectedList.split("\n")) {
            String[] data = e.split(",");
            Arrays.asList(data).replaceAll(String::trim);

            String status = (data.length <= 3) ? "in progress" : String.valueOf(statuses.valueOf(data[3].toUpperCase().replace(" ", "_")));
            String priority = String.valueOf(priorities.valueOf(data[2].toUpperCase()));

            tasksList.add(new Task(data[0], data[1], priority, status, user));
        }

        return tasksList;
    }
}
