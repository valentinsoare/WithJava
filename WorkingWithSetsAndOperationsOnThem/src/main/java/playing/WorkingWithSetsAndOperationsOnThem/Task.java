package playing.WorkingWithSetsAndOperationsOnThem;

enum statuses { ASSIGNED, IN_PROGRESS, NOT_YET_ASSIGNED }
enum priorities { HIGH, MEDIUM, LOW }

public class Task implements Comparable<Task> {
    private String assignee;
    private String projectName;
    private String description;

    private String status;
    private String priority;

    public Task(String projectName, String description,
                String priority, String status, String assignee) {
        this.assignee = setAssignee(assignee, true);
        this.projectName = setProjectName(projectName, true);
        this.description = setDescription(description, true);
        this.status = setStatus(status, true);
        this.priority = setPriority(priority, true);
    }

    public String getAssignee() {
        return assignee;
    }

    public String setAssignee(String assignee, boolean forConstructor) {
        StringBuilder valueForUsAsAString = new StringBuilder();
        String elementToReturn = "";

        if (assignee == null || assignee.isEmpty() || assignee.equalsIgnoreCase(this.assignee)) {
            valueForUsAsAString.append(this.assignee);
        } else {
            String[] temp = assignee.split(" +");

            for (String element : temp) {
                String withoutWhiteSpaces = element.trim();
                valueForUsAsAString.append(withoutWhiteSpaces.toUpperCase().charAt(0)).append(withoutWhiteSpaces.substring(1)).append(" ");
            }
        }

        elementToReturn = valueForUsAsAString.toString().trim();

        if (!forConstructor) {
            this.assignee = elementToReturn;
        }

        return elementToReturn;
    }

    public String getProjectName() {
        return projectName;
    }

    public String setProjectName(String projectName, boolean forConstructor) {
        String toReturn = "";
        StringBuilder toEdit = new StringBuilder();

        if ((projectName == null || projectName.isEmpty()) ||
                (projectName.equalsIgnoreCase(this.projectName))) {
                toReturn = this.projectName;
        } else {
            String[] temp = projectName.toLowerCase().split(" +");

            for (String e : temp) {
                String word = e.trim();
                toEdit.append(word.toUpperCase().charAt(0)).append(word.substring(1)).append(" ");
            }

            toReturn = toEdit.toString().trim();
        }

        if (!forConstructor) {
            this.projectName = toReturn;
        }

        return toReturn;
    }

    public String getDescription() {
        return description;
    }

    public String setDescription(String description, boolean forConstructor) {
        StringBuilder toReturn = new StringBuilder();

        if (description == null || description.isEmpty() || description.equalsIgnoreCase(this.description)) {
            toReturn.append(this.description);
        } else {
            toReturn.append(description.trim());
        }

        if (!forConstructor) {
            this.description = toReturn.toString();
        }

        return toReturn.toString();
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String status, boolean forConstructor) {
        String valueToUse = "";

        if (status == null) {
            valueToUse = "not yet assigned";
        } else {
            try {
                valueToUse = String.valueOf(statuses.valueOf(status.toUpperCase())).replace("_", " ");
            } catch (IllegalArgumentException e) {
                valueToUse = "in progress";
            }
        }

        if (!forConstructor) {
            this.status = valueToUse.toUpperCase();
        }

        return valueToUse.toUpperCase();
    }

    public priorities getPriority() {
        return priorities.valueOf(priority);
    }

    public String setPriority(String priority, boolean forConstructor) {
        String valueToReturn = "";

        try {
            valueToReturn = String.valueOf(priorities.valueOf(priority.toUpperCase()));
        } catch (IllegalArgumentException e) {
            valueToReturn = "low";
        }

        if (!forConstructor) {
            this.priority = valueToReturn;
        }

        return valueToReturn;
    }

    @Override
    public boolean equals(Object o) {
        boolean valueToReturn;
        if (this == o)  {
            valueToReturn = true;
        } else if (o == null || getClass() != o.getClass()) {
            valueToReturn = false;
        } else {
            Task task = (Task) o;

            if (!getProjectName().equals(task.getProjectName())) {
                valueToReturn = false;
            } else {
                valueToReturn = getDescription().equals(task.getDescription());
            }
        }
        return valueToReturn;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getDescription().hashCode();
        return result;
    }

    @Override
    public int compareTo(Task o) {
        int toReturn = this.projectName.compareTo(o.projectName);

        if (toReturn != 0)
            return toReturn;
        else {
            return this.description.compareTo(o.description);
        }
    }

    @Override
    public String toString() {
        return String.format("Project Name: %s, Description: %s, Assignee: %s, Status: %s, Priority: %s", projectName, description, assignee, status, priority);
    }
}
