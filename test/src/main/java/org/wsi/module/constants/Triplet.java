package org.wsi.module.constants;

public class Triplet<T, U, V> {

    private final T element;
    private final U attribute;
    private final V value;

    public Triplet(T element, U attribute, V value) {
        this.element = element;
        this.attribute = attribute;
        this.value = value;
    }

    public T getElement() { return element; }
    public U getAttribute() { return attribute; }
    public V getValue() { return value; }
    
    public static <T, U, V> Triplet <T, U, V> create(T t, U u, V v) {
        return new Triplet<T, U, V>(t, u, v);
    }
}
