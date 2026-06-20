package taskmgmt;

public class SinglyLinkedList {
    private Node head;

    // Node helper class
    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
    }

    /**
     * Adds a task to the end of the linked list.
     * Time Complexity: O(n) as it traverses to the end. (Can be O(1) if we keep a tail pointer).
     * Let's implement simple O(n) traversal insertion.
     */
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
            System.out.println("Task added as Head: " + task.getTaskName());
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        System.out.println("Task added: " + task.getTaskName());
    }

    /**
     * Searches for a task by taskId.
     * Time Complexity: O(n) worst case.
     */
    public Task searchTask(String taskId) {
        Node temp = head;
        while (temp != null) {
            if (temp.task.getTaskId().equals(taskId)) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * Traverses and prints all tasks.
     * Time Complexity: O(n).
     */
    public void traverseTasks() {
        if (head == null) {
            System.out.println("Task List is empty.");
            return;
        }
        System.out.println("--- Task List ---");
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.task);
            temp = temp.next;
        }
        System.out.println("-----------------");
    }

    /**
     * Deletes a task by taskId.
     * Time Complexity: O(n) worst case.
     */
    public boolean deleteTask(String taskId) {
        if (head == null) {
            System.out.println("Error: Task List is empty.");
            return false;
        }

        // If head is the target node
        if (head.task.getTaskId().equals(taskId)) {
            System.out.println("Deleted Head Task: " + head.task.getTaskName());
            head = head.next;
            return true;
        }

        Node prev = head;
        Node curr = head.next;

        while (curr != null) {
            if (curr.task.getTaskId().equals(taskId)) {
                System.out.println("Deleted Task: " + curr.task.getTaskName());
                prev.next = curr.next;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }

        System.out.println("Error: Task with ID " + taskId + " not found.");
        return false;
    }
}
