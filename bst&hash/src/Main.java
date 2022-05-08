public class Main {
    public static void main(String[] args) {
        MyHashTable<String, Integer> hashTable = new MyHashTable<>();
        hashTable.put("text", 0);
        hashTable.put("text", 1);
        hashTable.put("text", 2);
        hashTable.put("hello", 3);
        hashTable.remove("text");
        System.out.println(hashTable.remove("text"));
        System.out.println(hashTable.get("text"));
        System.out.println(hashTable.getKey(3));
        System.out.println(hashTable);

        BST<Integer, Integer> bst = new BST<>();
        bst.put(20, 0);
        bst.put(8, 0);
        bst.put(40, 0);
        bst.put(5, 0);
        bst.put(17, 0);
        bst.put(19, 0);
        bst.put(13, 0);
        bst.put(10, 0);
        bst.put(16, 0);
        bst.delete(17);
        for (Integer key : bst.iterator()) {
            System.out.println(key);
        }
    }
}
