package SikpList;

import java.util.Random;

public class SkipList <T>{

  public Node<T> head,tail;

  public int size;

  public int level;

  public Random random;

  public static final double PROBABILITY = 0.5;

  public SkipList(){
    head = new Node<>(Node.HEAD_KEY,null);
    tail = new Node<>(Node.TAIL_KEY,null);
    head.next = tail;
    tail.pre = head;

    size = 0;
    level = 0;
    random = new Random();
  }

  public Node<T> get(int key){
    Node<T> curr = findNearNode(key);
    if(curr.key == key){
      return curr;
    }
    return null;
  }

  public Node<T> findNearNode(int Key){
    Node<T> curr = head;
    while(true){
     if(curr.next != null && curr.next.key <=Key){
       curr = curr.next;
       continue;
     }
    Node<T>down = curr.down;
     if(down != null){
       curr = down;
       continue;
     }
      break;
    }
    return curr;
  }

  public T getValueByKey(int key){
    return findNearNode(key).value;
  }

  public T remove (int key){
    Node<T> find = get(key);
    T result = null;
    while(find != null){
      result = find.value;
      find.pre.next = find.next;
      find.next.pre = find.pre;
      find = find.up;
    }
    size--;
    return result;
  }

  public T put(int key, T value) {
    Node<T> curr = findNearNode(key);
    if (curr.key == key) {
      T result = curr.value;
      curr.value = value;
      return result;
    }
    Node<T> newNode = new Node<>(key, value);
    insertNodeHorizontally(curr,newNode);

    int currLevel = 0;
    while(random.nextDouble() < PROBABILITY){
      if(currLevel >= level){
        addLevel();
      }

      while(curr.up == null){
          curr = curr.pre;
      }
          curr = curr.up;

      Node<T> copyNew = new Node<T>(key,null);
      insertNodeHorizontally(curr,copyNew);

      copyNew.down = newNode;
      newNode.up = copyNew;

      newNode = copyNew;
      currLevel++;
    }
    size++;
    return null;
  }

  private void addLevel(){
    Node<T> h1 = new Node<T>(Node.HEAD_KEY,null);
    Node<T> t1 = new Node<T>(Node.TAIL_KEY,null);

    h1.next = t1;
    t1.pre = h1;

    h1.down = head;
    t1.down = tail;

    head.up = h1;
    tail.up = t1;

    head = h1;
    tail = t1;
    level++;
  }

  private void insertNodeHorizontally (Node<T> pre, Node<T> curr){
    curr.next = pre.next;
    curr.pre = pre;

    pre.next.pre = curr;
    pre.next = curr;
  }

  public String toString(){
    if(isEmpty()){
      return "跳表为空";
    }
    StringBuilder sb = new StringBuilder();
    Node<T> curr = head;
    while(curr.down != null){
      curr = curr.down;
    }
    while(curr.pre != null){
      curr = curr.pre;
    }
    if(curr.next != null){
      curr = curr.next;
    }

    while(curr.next != null){
      sb.append(curr.value);
      sb.append("\t");
      curr = curr.next;
    }
    return sb.toString();
  }

  private boolean isEmpty(){
    return size == 0;
  }

  public static void main(String[] args) {
    SkipList<String> list = new SkipList<>();
    list.put(2,"A");
    list.put(1,"B");
    list.put(3,"C");
    list.put(4,"D");
    list.put(5,"E");
    list.put(6,"F");
    list.put(7,"G");
    list.put(8,"AA");
    list.put(9,"AF");
    list.put(10,"AE");
    System.out.println(list.put(1, "DD"));
    System.out.println(list);
    System.out.println(list.remove(10));
    System.out.println(list.getValueByKey(9));
  }
}

