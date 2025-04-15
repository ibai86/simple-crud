package homework.utility;

import java.util.Arrays;
import java.util.Objects;

public class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] buckets;
    private int size;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public CustomHashMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0");
        }
        this.buckets = new Entry[initialCapacity];
        this.size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);

        for (Entry<K, V> entry = buckets[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        Entry<K, V> newEntry = new Entry<>(key, value, buckets[index]);
        buckets[index] = newEntry;
        size++;

        if (size > LOAD_FACTOR * buckets.length) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);

        for (Entry<K, V> entry = buckets[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);

        Entry<K, V> previous = null;
        for (Entry<K, V> entry = buckets[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                if (previous == null) {
                    buckets[index] = entry.next;
                } else {
                    previous.next = entry.next;
                }
                size--;
                return entry.value;
            }
            previous = entry;
        }
        return null;
    }

    public void clear() {
        Arrays.fill(buckets, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(K key) {
        int hash = key.hashCode();
        return Math.abs(hash) % buckets.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        int newCapacity = buckets.length * 2;
        buckets = new Entry[newCapacity];
        size = 0;

        for (Entry<K, V> head : oldBuckets) {
            for (Entry<K, V> entry = head; entry != null; entry = entry.next) {
                put(entry.key, entry.value);
            }
        }
    }

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            int result = Objects.hashCode(key);
            result = 31 * result + Objects.hashCode(value);
            return result;
        }
    }
}
