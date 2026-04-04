package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a mathematical set of integers.
 * A set cannot contain duplicate values.
 * Supports standard set operations: union, intersection, difference, and complement.
 */
public class IntegerSet {

    /** Internal storage for the set elements. */
    private ArrayList<Integer> set = new ArrayList<>();

    /** Constructs an empty IntegerSet. */
    public IntegerSet() {}

    /**
     * Constructs an IntegerSet initialized with the given list.
     *
     * @param set the initial list of integers
     */
    public IntegerSet(ArrayList<Integer> set) {
        this.set = new ArrayList<>(set);
    }

    /**
     * Removes all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the number of elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if this set and the given set contain exactly the same elements,
     * regardless of order.
     *
     * @param b the other IntegerSet to compare with
     * @return true if both sets are equal
     */
    public boolean equals(IntegerSet b) {
        ArrayList<Integer> sortedThis = new ArrayList<>(this.set);
        ArrayList<Integer> sortedB = new ArrayList<>(b.set);
        Collections.sort(sortedThis);
        Collections.sort(sortedB);
        return sortedThis.equals(sortedB);
    }

    /**
     * Returns true if the set contains the given value.
     *
     * @param value the integer to search for
     * @return true if value is in the set
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in the set.
     *
     * @return the largest integer in the set
     * @throws IntegerSetException if the set is empty
     */
    public int largest() throws IntegerSetException {
        if (set.isEmpty()) {
            throw new IntegerSetException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in the set.
     *
     * @return the smallest integer in the set
     * @throws IntegerSetException if the set is empty
     */
    public int smallest() throws IntegerSetException {
        if (set.isEmpty()) {
            throw new IntegerSetException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an integer to the set. Duplicates are not added.
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes the specified integer from the set if it exists.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new set containing all elements from both this set and set b.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet that is the union of both sets
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet(this.set);
        for (int item : intSetb.set) {
            result.add(item);
        }
        return result;
    }

    /**
     * Returns a new set containing only elements common to both this set and set b.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet that is the intersection of both sets
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet(this.set);
        result.set.retainAll(intSetb.set);
        return result;
    }

    /**
     * Returns a new set containing elements in this set but not in set b.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet that is the difference (this - b)
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet(this.set);
        result.set.removeAll(intSetb.set);
        return result;
    }

    /**
     * Returns a new set containing elements in set b but not in this set.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet that is the complement (b - this)
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet(intSetb.set);
        result.set.removeAll(this.set);
        return result;
    }

    /**
     * Returns true if the set contains no elements.
     *
     * @return true if the set is empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set in ascending order.
     * Format: [1, 2, 3] or [] for an empty set.
     *
     * @return string representation of the set
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}
