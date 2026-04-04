package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for IntegerSet.
 * Each method is tested with at least one normal case and one edge case.
 */
@DisplayName("Tests for IntegerSet")
public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;

    /** Creates fresh empty sets before each test. */
    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }

    // ============================= clear() ==============================

    @Test
    @DisplayName("Test clear() normal: removes all elements from a non-empty set")
    public void testClearNormal() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.clear();
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("Test clear() edge: calling clear() on an already-empty set does nothing")
    public void testClearEdgeAlreadyEmpty() {
        set1.clear();
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    // ============================= length() =============================

    @Test
    @DisplayName("Test length() normal: returns correct count after additions")
    public void testLengthNormal() {
        set1.add(10);
        set1.add(20);
        set1.add(30);
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("Test length() edge: empty set has length 0")
    public void testLengthEdgeEmpty() {
        assertEquals(0, set1.length());
    }

    // ============================= equals() =============================

    @Test
    @DisplayName("Test equals() normal: same elements in same order are equal")
    public void testEqualsNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2); set2.add(3);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals() edge: same elements in different order are equal")
    public void testEqualsEdgeDifferentOrder() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(3); set2.add(1); set2.add(2);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals() edge: sets with different elements are not equal")
    public void testEqualsEdgeMismatch() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2); set2.add(4);
        assertFalse(set1.equals(set2));
    }

    // ============================= contains() ===========================

    @Test
    @DisplayName("Test contains() normal: returns true for a present value")
    public void testContainsNormal() {
        set1.add(5);
        assertTrue(set1.contains(5));
    }

    @Test
    @DisplayName("Test contains() edge: returns false for a value not in the set")
    public void testContainsEdgeNotPresent() {
        set1.add(5);
        assertFalse(set1.contains(99));
    }

    // ============================= largest() ============================

    @Test
    @DisplayName("Test largest() normal: returns the maximum value")
    public void testLargestNormal() {
        set1.add(3); set1.add(1); set1.add(7); set1.add(2);
        assertEquals(7, set1.largest());
    }

    @Test
    @DisplayName("Test largest() edge: single-element set returns that element")
    public void testLargestEdgeSingleElement() {
        set1.add(42);
        assertEquals(42, set1.largest());
    }

    @Test
    @DisplayName("Test largest() edge: throws IntegerSetException on empty set")
    public void testLargestEdgeEmptyThrows() {
        assertThrows(IntegerSetException.class, () -> set1.largest());
    }

    // ============================= smallest() ===========================

    @Test
    @DisplayName("Test smallest() normal: returns the minimum value")
    public void testSmallestNormal() {
        set1.add(3); set1.add(1); set1.add(7); set1.add(2);
        assertEquals(1, set1.smallest());
    }

    @Test
    @DisplayName("Test smallest() edge: single-element set returns that element")
    public void testSmallestEdgeSingleElement() {
        set1.add(42);
        assertEquals(42, set1.smallest());
    }

    @Test
    @DisplayName("Test smallest() edge: throws IntegerSetException on empty set")
    public void testSmallestEdgeEmptyThrows() {
        assertThrows(IntegerSetException.class, () -> set1.smallest());
    }

    // ============================= add() ================================

    @Test
    @DisplayName("Test add() normal: adds a new element and increases length")
    public void testAddNormal() {
        set1.add(10);
        assertTrue(set1.contains(10));
        assertEquals(1, set1.length());
    }

    @Test
    @DisplayName("Test add() edge: duplicate value is not added twice")
    public void testAddEdgeDuplicate() {
        set1.add(10);
        set1.add(10);
        assertEquals(1, set1.length());
    }

    // ============================= remove() =============================

    @Test
    @DisplayName("Test remove() normal: removes an existing element")
    public void testRemoveNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set1.remove(2);
        assertFalse(set1.contains(2));
        assertEquals(2, set1.length());
    }

    @Test
    @DisplayName("Test remove() edge: removing a value not present does nothing")
    public void testRemoveEdgeNotPresent() {
        set1.add(1); set1.add(2);
        set1.remove(99);
        assertEquals(2, set1.length());
    }

    // ============================= union() ==============================

    @Test
    @DisplayName("Test union() normal: combines elements from both sets without duplicates")
    public void testUnionNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.union(set2);
        assertEquals(4, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
    }

    @Test
    @DisplayName("Test union() edge: union with empty set returns copy of original")
    public void testUnionEdgeWithEmpty() {
        set1.add(1); set1.add(2);
        IntegerSet result = set1.union(set2);
        assertEquals(2, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    // ============================= intersect() ==========================

    @Test
    @DisplayName("Test intersect() normal: returns only elements common to both sets")
    public void testIntersectNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertEquals(2, result.length());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertFalse(result.contains(1));
        assertFalse(result.contains(4));
    }

    @Test
    @DisplayName("Test intersect() edge: no common elements returns empty set")
    public void testIntersectEdgeNoOverlap() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.isEmpty());
    }

    // ============================= diff() ===============================

    @Test
    @DisplayName("Test diff() normal: returns elements in set1 but not in set2")
    public void testDiffNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertEquals(1, result.length());
        assertTrue(result.contains(1));
    }

    @Test
    @DisplayName("Test diff() edge: diff of identical sets returns empty set")
    public void testDiffEdgeIdenticalSets() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2); set2.add(3);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.isEmpty());
    }

    // ============================= complement() =========================

    @Test
    @DisplayName("Test complement() normal: returns elements in set2 but not in set1")
    public void testComplementNormal() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.complement(set2);
        assertEquals(1, result.length());
        assertTrue(result.contains(4));
    }

    @Test
    @DisplayName("Test complement() edge: disjoint sets returns all of set2")
    public void testComplementEdgeDisjoint() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.complement(set2);
        assertEquals(2, result.length());
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
    }

    // ============================= isEmpty() ============================

    @Test
    @DisplayName("Test isEmpty() normal: non-empty set returns false")
    public void testIsEmptyNormal() {
        set1.add(1);
        assertFalse(set1.isEmpty());
    }

    @Test
    @DisplayName("Test isEmpty() edge: new empty set returns true")
    public void testIsEmptyEdgeEmpty() {
        assertTrue(set1.isEmpty());
    }

    // ============================= toString() ===========================

    @Test
    @DisplayName("Test toString() normal: elements printed in ascending order")
    public void testToStringNormal() {
        set1.add(3); set1.add(1); set1.add(2);
        assertEquals("[1, 2, 3]", set1.toString());
    }

    @Test
    @DisplayName("Test toString() edge: empty set returns []")
    public void testToStringEdgeEmpty() {
        assertEquals("[]", set1.toString());
    }
}
