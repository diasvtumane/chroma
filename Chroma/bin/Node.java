package SikpList;

public class Node <T>{

  public int key;

  public T value;

  public Node<T> pre,next;

  public Node<T> up,down;

  public static int  HEAD_KEY = Integer.MIN_VALUE;

  public static int TAIL_KEY = Integer.MAX_VALUE;

  public Node(int key, T value) {
    this.key = key;
    this.value = value;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Node: key " + key + ",value"+value;
  }


}
