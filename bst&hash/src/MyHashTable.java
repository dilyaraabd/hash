public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    private int hash(K key) {
        return key.hashCode() % M;
    }

    public void put(K key, V value) {
        HashNode<K, V> newNode = new HashNode<>(key, value);
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        if (node != null) {
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        } else {
            chainArray[index] = newNode;
        }
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        if (node != null) {
            while (node != null) {
                if (node.key == key) return node.value;
                node = node.next;
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        V result = null;
        HashNode<K, V> node = chainArray[index];
        if (node != null) {
            if (node.key == key) {
                result = node.value;
                chainArray[index] = node.next;
            } else {
                while (node.next != null) {
                    if (node.next.key == key) {
                        result = node.next.value;
                        break;
                    }
                    node = node.next;
                }
                if (node.next != null) {
                    node.next = node.next.next;
                }
            }
        }
        return result;
    }

    public boolean contains(V value) {
        for (int i=0; i<M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                V nodeValue = get(node.key);
                if (nodeValue == value) return true;
                node = node.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i=0; i<M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                V nodeValue = get(node.key);
                if (nodeValue == value) return node.key;
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i=0; i<M; i++) {
            HashNode<K, V> node = chainArray[i];
            output += i;
            output += " -> ";
            while (node != null) {
                output += node.toString();
                output += "; ";
                node = node.next;
            }
            output += "\n";
        }
        return output;
    }
}
