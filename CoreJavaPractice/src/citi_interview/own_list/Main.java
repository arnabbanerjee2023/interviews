package citi_interview.own_list;

public class Main {
    public static void main(String[] args) {
        CustomList<Integer> list = new CustomList<>();
        
        // Add elements
        list.add(5);
        list.add(3);
        list.add(8);
        list.add(1);
        
        System.out.println("Initial list size: " + list.size());
        
        // Print elements using iterator
        System.out.println("List elements:");
        for (Integer num : list) {
            System.out.println(num);
        }
        
        // Remove an element
        Integer removed = list.remove(1);
        System.out.println("\nRemoved element: " + removed);
        System.out.println("List after removal:");
        for (Integer num : list) {
            System.out.println(num);
        }
        
        // Clear the list
        list.clear();
        System.out.println("\nList size after clear: " + list.size());
    }
}
