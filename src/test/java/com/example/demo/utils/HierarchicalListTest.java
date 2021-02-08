package com.example.demo.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;

class HierarchicalListTest {

    HierarchicalList<String> hierarchicalList;

    @BeforeEach
    void setUp() {
        hierarchicalList = new HierarchicalList<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testRoot() {
        //Test If fresh new hl contains any root data.
        assertFalse(hierarchicalList.getRoot().getData().isPresent(), "Root node should not have a value before its assigned by");
        hierarchicalList.setRootValue("This is root data");
        assertTrue(hierarchicalList.getRoot().getData().isPresent(), "Root node should have a value after its assigned by");
        assertEquals("This is root data", hierarchicalList.getRoot().getData().get(), "Root node data does not equal to its assigned data");
        assertFalse(hierarchicalList.getRoot().hasChildren(), "Root node should not have children");
    }


    @Test
    
    void testFirstLevel() {
        hierarchicalList
                .setRootValue("This is root data")
                .addChildren("this is child 01")
                .addChildren("this is child 02")
                .addChildren("this is child 03");
        assertEquals(3, hierarchicalList.getRoot().getChildrenCount(), "Children count does not equal to actual size");
        hierarchicalList.getRoot().removeChildren(0);
        assertEquals(2, hierarchicalList.getRoot().getChildrenCount(), "Children count does not equal to actual size");
    }
}