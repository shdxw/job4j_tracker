package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        for (T t : out) {
            out.pop();
        }
        int temp = 0;
        T rsl = null;
        for (T t : in) {
            if (temp == 0) {
                rsl = t;
                temp++;
                continue;
            } else {
                out.push(t);  
            }
            in.pop();
        }
        
        for (T t : out) {
            in.push(t);
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
    }
}
