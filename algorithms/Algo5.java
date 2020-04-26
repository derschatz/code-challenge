import javax.print.attribute.standard.Media;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Locale;

/**
 * 5. Remove duplicates on email thread:
 * 
 * When different email clients are used on a same thread, the discussion get messy
 * because old messages are included again and get duplicated. Given a email thread
 * (represented by a singly unsorted linked list of messages), write a function that
 * remove duplicated messages from it.
 * 
 * Solution time complexity
 * 
 */

import java.util.Random;

public final class Algo5 {
    
    public void removeDuplicates(LinkedList<String> list){
        /**
         * Logic to remove duplicates was implemented in the LinkedList.
         */
        list.removeDuplicates();
    }

    public static void main(String... args) {
        test(5);
        // test(15);
        // test(25);
        // test(50);
        // test(100);
    }

    private static void test(int n){
        final LinkedList<String> list = new LinkedList<String>();
        final Random random = new Random();
        System.out.println(String.format(Locale.getDefault(), "Test 1 (%d) items", n));
        for(int i = 1; i < n; i++){
            list.append(String.valueOf(random.nextInt(i)));            
        }

        System.out.println("Email thread with duplicates: ");
        list.printList();
        System.out.println("Email thread without duplicates: ");
        Algo5 algo5 = new Algo5();
        algo5.removeDuplicates(list);
        System.out.println("Email thread without duplicates: ");
        list.printList();
    }
}

// /**
//  * Class that represents a message
//  */
// class Message {
//     private int id;
//     private String text;

//     public void setId(int id){
//         this.id = id;
//     }

//     public int getId() {
//         return this.id;
//     }

//     public void setText(String text){
//         this.text = text;
//     }

//     public String getText() {
//         return this.text;
//     }
// }

// class MessageNode {
//     private Message data;
//     private MessageNode nextNode;

//     public MessageNode(){}

//     public MessageNode(Message data) {
//         this.data = data;
//     }

//     public void setData(Message data) {
//         this.data = data;
//     }

//     public Message getData() {
//         return this.data;
//     }

//     public void setNextNode(MessageNode nextNode){
//         this.nextNode = nextNode;
//     }

//     public MessageNode getNextNode() {
//         return this.nextNode;
//     }
// }

// class MessageLinkedList {
//     private MessageNode head;


//     public MessageNode getHead(){
//         return this.head;
//     }

//     public void append(Message data){
//         MessageNode newNode = new MessageNode(data);
//         if(this.head == null){
//             this.head = newNode;
//             return;
//         }

//         MessageNode aux = this.head;
//         while(aux.getNextNode()!=null) {
//             aux = aux.getNextNode();
//         }
//         aux.setNextNode(newNode);
//     }


//     public void delete(Node node) {
//         if (this.head == null) {
//             return;
//         }
//     }

//     public void printList() {
//         printList(this.head);
//     }

//     public static void printList(MessageNode head) {
//         MessageNode aux = head;
//         while(aux != null) {
//             System.out.println(aux.getData().getText());
//             aux = aux.getNextNode();
//         }
//     }

//     public void reverse(){
//         if (this.head == null) {
//             return;
//         }

//         MessageNode aux = this.head;
//         MessageNode newHead = null;
//         while(aux!=null) {
//             MessageNode nextAux = aux.getNextNode();
//             aux.setNextNode(newHead);
//             newHead = aux;
//             aux = nextAux;
//         }

//         this.head = newHead;
//     }
// }