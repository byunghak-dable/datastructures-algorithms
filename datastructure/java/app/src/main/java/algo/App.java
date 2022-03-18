package algo;

import algo.linkedlist.SinglyLinkedList;
import algo.stack.ListStack;

public class App {
  public static void main(String[] args) {
    // SinglyLinkedList<String> singlyList = new SinglyLinkedList<String>();
    // singlyList.addLast("yes1");
    // singlyList.addLast("yes2");
    // singlyList.addLast("yes3");
    // singlyList.addLast("yes4");
    // singlyList.addFirst("first1");
    // singlyList.addFirst("first2");
    ListStack<String> stack = new ListStack<String>();
    System.out.println(stack.isEmpty());
    stack.push("yes");
    stack.push("yes1");
    stack.push("yes2");
    for (String string : stack) {
      System.out.println(string);

    }
  }

}
