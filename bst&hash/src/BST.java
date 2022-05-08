import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BST<K extends Comparable<K>, V> {
    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root = null;

    public void put(K key, V value) {
        Node node = new Node(key, value);
        if (root == null) {
            root = node;
        } else {
            put(root, node);
        }
    }

    private void put(Node tempRoot, Node node) {
        if (tempRoot == null) return;
        if (tempRoot.key.compareTo(node.key) == 0) {
            tempRoot.value = node.value;
        } else if (tempRoot.key.compareTo(node.key) > 0) {
            if (tempRoot.left == null) {
                tempRoot.left = node;
            } else {
                put(tempRoot.left, node);
            }
        } else {
            if (tempRoot.right == null) {
                tempRoot.right = node;
            } else {
                put(tempRoot.right, node);
            }
        }
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node tempRoot, K key) {
        if (tempRoot == null) return null;
        if (tempRoot.key.compareTo(key) == 0) {
            return tempRoot.value;
        } else if (tempRoot.key.compareTo(key) > 0) {
            return get(tempRoot.left, key);
        } else {
            return get(tempRoot.right, key);
        }
    }

    public void delete(K key) {
        delete(root, key);
    }

    private Node delete(Node tempRoot, K key) {
        if (tempRoot == null)
            return null;
        if (tempRoot.key.compareTo(key) > 0) {
            tempRoot.left = delete(tempRoot.left, key);
            return tempRoot;
        } else if (tempRoot.key.compareTo(key) < 0) {
            tempRoot.right = delete(tempRoot.right, key);
            return tempRoot;
        }

        if (tempRoot.left == null) {
            return tempRoot.right;
        } else if (tempRoot.right == null) {
            return tempRoot.left;
        } else {
            Node parent = tempRoot;
            Node node = tempRoot.right;

            while (node.left != null) {
                parent = node;
                node = node.left;
            }
            if (parent != tempRoot)
                parent.left = node.right;
            else
                parent.right = node.right;
            tempRoot.key = node.key;
            return tempRoot;
        }
    }

    private Node parent(Node tempRoot, Node child) {
        if (child == root || tempRoot == null) return null;
        if (tempRoot.left != null && tempRoot.left.key.compareTo(child.key) == 0) {
            return tempRoot;
        } else if (tempRoot.right != null && tempRoot.right.key.compareTo(child.key) == 0) {
            return tempRoot;
        } else if (tempRoot.key.compareTo(child.key) > 0) {
            return parent(tempRoot.left, child);
        } else if (tempRoot.key.compareTo(child.key) < 0) {
            return parent(tempRoot.right, child);
        }
        return null;
    }

    private class BSTIterable implements Iterable<K> {
        private class BSTIterator implements Iterator<K> {
            private Node next;

            public BSTIterator() {
                next = root;
                if (next == null)
                    return;

                while (next.left != null) {
                    next = next.left;
                }
            }

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public K next() {
                if (!hasNext()) throw new NoSuchElementException();
                Node r = next;
                if (next.right != null) {
                    next = next.right;
                    while (next.left != null)
                        next = next.left;
                    return r.key;
                }

                while (true) {
                    if (parent(root, next) == null) {
                        next = null;
                        return r.key;
                    }
                    if (Objects.requireNonNull(parent(root, next)).left == next) {
                        next = parent(root, next);
                        return r.key;
                    }
                    next = parent(root, next);
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        public Iterator<K> iterator() {
            BSTIterator iterator = new BSTIterator();
            return iterator;
        }
    }

    public Iterable<K> iterator() {
        return new BSTIterable();
    }
}
