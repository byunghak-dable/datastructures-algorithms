package algo;

import algo.linkedlist.SinglyLinkedList;

public class App {

  public static void main(String[] args) {
    SinglyLinkedList<String> singlyList = new SinglyLinkedList<String>();
    singlyList.addLast("yes1");
    singlyList.addLast("yes2");
    singlyList.addLast("yes3");
    singlyList.addLast("yes4");
    singlyList.addFirst("first1");
    singlyList.addFirst("first2");
    singlyList.remove(2);
    System.out.println(singlyList.get(2));

  }
}
