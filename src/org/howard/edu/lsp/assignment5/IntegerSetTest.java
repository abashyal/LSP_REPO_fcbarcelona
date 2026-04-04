package org.howard.edu.lsp.assignment5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for IntegerSet.
 * Tests all public methods including edge cases.
 */
@DisplayName("Tests for IntegerSet")
public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;

    /** Initializes fresh sets before each test. */
    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }

    // ----------------------------- clear() -----------------------------

    @Test
    @DisplayName("Test clear() on non-empty set makes it empty")
    public void testClear() {
        set1.add(1);
        set1.add(2);
        set1.clear();
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("Test clear() on already-empty set does nothing")
    public void testClearEmpty() {
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    // ----------------------------- length() ----------------------------

    @Test
    @DisplayName("Test length() of empty set is 0")
    public void testLengthEmpty() {
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("Test length() after adding elements")
    public void testLengthNonEmpty() {
        set1.add(10);
        set1.add(20);
        set1.add(30);
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("Test length() does not count duplicates")
    public void testLengthNoDuplicates() {
        set1.add(5);
        set1.add(5);
        assertEquals(1, set1.length());
    }

    // ----------------------------- equals() ----------------------------

    @Test
    @DisplayName("Test equals() with same elements in same order")
    public void testEqualsSameOrder() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2); set2.add(3);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals() with same elements in different order")
    public void testEqualsDifferentOrder() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(3); set2.add(2); set2.add(1);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals() with different elements returns false")
    public void testEqualsNotEqual() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(3);
        assertFalse(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals() with different sizes returns false")
    public void testEqualsDifferentSize() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2);
        assertFalse(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals() two empty sets are equal")
    public void testEqualsBothEmpty() {
        assertTrue(set1.equals(set2));
    }

    // ----------------------------- contains() --------------------------

    @Test
    @DisplayName("Test contains() returns true for present element")
    public void testContainsTrue() {
        set1.add(7);
        assertTrue(set1.contains(7));
    }

    @Test
    @DisplayName("Test contains() returns false for absent element")
    public void testContainsFalse() {
        set1.add(7);
        assertFalse(set1.contains(99));
    }

    @Test
    @DisplayName("Test contains() on empty set returns false")
    public void testContainsEmpty() {
        assertFalse(set1.contains(1));
    }

    // ----------------------------- largest() ---------------------------

    @Test
    @DisplayName("Test largest() returns correct max value")
    public void testLargest() {
        set1.add(5); set1.add(1); set1.add(9); set1.add(3);
        assertEquals(9, set1.largest());
    }

    @Test
    @DisplayName("Test largest() on single-element set")
    public void testLargestSingleElement() {
        set1.add(42);
        assertEquals(42, set1.largest());
    }

    @Test
    @DisplayName("Test largest() throws exception on empty set")
    public void testLargestEmptyThrows() {
        assertThrows(IntegerSetException.class, () -> set1.largest());
    }

    // ----------------------------- smallest() --------------------------

    @Test
    @DisplayName("Test smallest() returns correct min value")
    public void testSmallest() {
        set1.add(5); set1.add(1); set1.add(9); set1.add(3);
        assertEquals(1, set1.smallest());
    }

    @Test
    @DisplayName("Test smallest() on single-element set")
    public void testSmallestSingleElement() {
        set1.add(42);
        assertEquals(42, set1.smallest());
    }

    @Test
    @DisplayName("Test smallest() throws exception on empty set")
    public void testSmallestEmptyThrows() {
        assertThrows(IntegerSetException.class, () -> set1.smallest());
    }

    // ----------------------------- add() -------------------------------

    @Test
    @DisplayName("Test add() increases length")
    public void testAdd() {
        set1.add(10);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(10));
    }

    @Test
    @DisplayName("Test add() does not add duplicate")
    public void testAddDuplicate() {
        set1.add(10);
        set1.add(10);
        assertEquals(1, set1.length());
    }

    @Test
    @DisplayName("Test add() with negative numbers")
    public void testAddNegative() {
        set1.add(-5);
        assertTrue(set1.contains(-5));
    }

    // ----------------------------- remove() ----------------------------

    @Test
    @DisplayName("Test remove() removes an existing element")
    public void testRemove() {
        set1.add(1); set1.add(2); set1.add(3);
        set1.remove(2);
        assertFalse(set1.contains(2));
        assertEquals(2, set1.length());
    }

    @Test
    @DisplayName("Test remove() on non-existent element does nothing")
    public void testRemoveNonExistent() {
        set1.add(1);
        set1.remove(99);
        assertEquals(1, set1.length());
    }

    @Test
    @DisplayName("Test remove() on empty set does nothing")
    public void testRemoveEmpty() {
        set1.remove(5);
        assertTrue(set1.isEmpty());
    }

    // ----------------------------- union() -----------------------------

    @Test
    @DisplayName("Test union() combines both sets without duplicates")
    public void testUnion() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.union(set2);
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(4, result.length());
    }

    @Test
    @DisplayName("Test union() does not modify original sets")
    public void testUnionDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        set1.union(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    @Test
    @DisplayName("Test union() with empty set returns copy of other")
    public void testUnionWithEmpty() {
        set1.add(1); set1.add(2);
        IntegerSet result = set1.union(set2);
        assertEquals(2, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    @Test
    @DisplayName("Test union() of two empty sets is empty")
    public void testUnionBothEmpty() {
        IntegerSet result = set1.union(set2);
        assertTrue(result.isEmpty());
    }

    // ----------------------------- intersect() -------------------------

    @Test
    @DisplayName("Test intersect() returns common elements")
    public void testIntersect() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertFalse(result.contains(1));
        assertFalse(result.contains(4));
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("Test intersect() with no common elements is empty")
    public void testIntersectNoCommon() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test intersect() does not modify original sets")
    public void testIntersectDoesNotModifyOriginals() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        set1.intersect(set2);
        assertEquals(3, set1.length());
        assertEquals(3, set2.length());
    }

    @Test
    @DisplayName("Test intersect() with empty set returns empty")
    public void testIntersectWithEmpty() {
        set1.add(1); set1.add(2);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.isEmpty());
    }

    // ----------------------------- diff() ------------------------------

    @Test
    @DisplayName("Test diff() returns elements in set1 but not set2")
    public void testDiff() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.contains(1));
        assertFalse(result.contains(2));
        assertFalse(result.contains(3));
        assertEquals(1, result.length());
    }

    @Test
    @DisplayName("Test diff() does not modify original sets")
    public void testDiffDoesNotModifyOriginals() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3);
        set1.diff(set2);
        assertEquals(3, set1.length());
        assertEquals(2, set2.length());
    }

    @Test
    @DisplayName("Test diff() with disjoint sets returns copy of set1")
    public void testDiffDisjoint() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertEquals(2, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    @Test
    @DisplayName("Test diff() where set2 contains all of set1 returns empty")
    public void testDiffAllRemoved() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(2); set2.add(3);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.isEmpty());
    }

    // ----------------------------- complement() ------------------------

    @Test
    @DisplayName("Test complement() returns elements in set2 but not set1")
    public void testComplement() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.complement(set2);
        assertTrue(result.contains(4));
        assertFalse(result.contains(2));
        assertFalse(result.contains(3));
        assertEquals(1, result.length());
    }

    @Test
    @DisplayName("Test complement() does not modify original sets")
    public void testComplementDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3); set2.add(4);
        set1.complement(set2);
        assertEquals(2, set1.length());
        assertEquals(3, set2.length());
    }

    @Test
    @DisplayName("Test complement() with identical sets returns empty")
    public void testComplementIdenticalSets() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(2);
        IntegerSet result = set1.complement(set2);
        assertTrue(result.isEmpty());
    }

    // ----------------------------- isEmpty() ---------------------------

    @Test
    @DisplayName("Test isEmpty() on new set returns true")
    public void testIsEmptyTrue() {
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test isEmpty() after adding element returns false")
    public void testIsEmptyFalse() {
        set1.add(1);
        assertFalse(set1.isEmpty());
    }

    @Test
    @DisplayName("Test isEmpty() after clear returns true")
    public void testIsEmptyAfterClear() {
        set1.add(1); set1.add(2);
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    // ----------------------------- toString() --------------------------

    @Test
    @DisplayName("Test toString() on empty set returns []")
    public void testToStringEmpty() {
        assertEquals("[]", set1.toString());
    }

    @Test
    @DisplayName("Test toString() returns elements in ascending order")
    public void testToStringAscendingOrder() {
        set1.add(3); set1.add(1); set1.add(2);
        assertEquals("[1, 2, 3]", set1.toString());
    }

    @Test
    @DisplayName("Test toString() on single element")
    public void testToStringSingleElement() {
        set1.add(5);
        assertEquals("[5]", set1.toString());
    }

    @Test
    @DisplayName("Test toString() with negative numbers")
    public void testToStringWithNegatives() {
        set1.add(0); set1.add(-2); set1.add(3);
        assertEquals("[-2, 0, 3]", set1.toString());
    }
}
