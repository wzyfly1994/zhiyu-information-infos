package com.zhiyu.common.map;

import java.util.Map;
import java.util.Objects;

/**
 * @author wengzhiyu
 * @date 2021/3/23
 */
public class Node<K, V> implements Map.Entry<K, V> {

    private int hash;

    private K key;

    private V value;

    private Node<K, V> next;

    Node(int hash, K key, V value, Node<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    @Override
    public String toString() {
        return "Node{" +
                "hash=" + hash +
                ", key=" + key +
                ", value=" + value +
                ", next=" + next +
                '}';
    }
}
