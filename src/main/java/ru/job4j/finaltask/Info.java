package ru.job4j.finaltask;

public class Info {
    private int added = 0;
    private int changed = 0;
    private int deleted = 0;
    private int stable = 0;

    public void addAdded() {
        added++;
    }

    public void addChanged() {
        changed++;
    }

    public void addDeleted() {
        deleted++;
    }

    public void addStable() {
        stable++;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getStable() {
        return stable;
    }

    public int[] results() {
        int[] rsl = new int[3];
        rsl[0] = added;
        rsl[1] = changed;
        rsl[2] = deleted;
        return rsl;
    }
}
