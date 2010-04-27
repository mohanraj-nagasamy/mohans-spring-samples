package com.jsf2.test.common.web;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dimitar Makariev
 */
public class PageInfoTest {
  
    @Test
    public void testGetNumberOfPages_1() {
        int maxItemsOnPage = 5;
        int itemCount = 106;
        int currentPage = 4;
        PageInfo instance = new PageInfo(maxItemsOnPage, itemCount, currentPage);

        assertEquals("getNumberOfPages", 22, instance.getNumberOfPages());
        assertEquals("getFirstItemIndex", 15, instance.getFirstItemIndex());
        assertTrue("isNext()", instance.getHasNext());
        assertTrue("isPrevious()", instance.getHasPrevious());

    }

    @Test
    public void testGetNumberOfPages_2() {
        int maxItemsOnPage = 5;
        int itemCount = 11;
        int currentPage = 1;
        PageInfo instance = new PageInfo(maxItemsOnPage, itemCount, currentPage);

        assertEquals("getNumberOfPages", 3, instance.getNumberOfPages());
        assertEquals("getFirstItemIndex", 0, instance.getFirstItemIndex());
        assertTrue("isNext()", instance.getHasNext());
        assertFalse("isPrevious()", instance.getHasPrevious());
    }

    @Test
    public void testGetNumberOfPages_3() {
        int maxItemsOnPage = 5;
        int itemCount = 11;
        int currentPage = 5;
        PageInfo instance = new PageInfo(maxItemsOnPage, itemCount, currentPage);

        assertEquals("getNumberOfPages", 3, instance.getNumberOfPages());
        assertEquals("getFirstItemIndex", 10, instance.getFirstItemIndex());
        assertEquals("getCurrentPage", 3, instance.getCurrentPage());
        assertFalse("isNext()", instance.getHasNext());
        assertTrue("isPrevious()", instance.getHasPrevious());
    }

    @Test
    public void testGetNumberOfPages_4() {
        int maxItemsOnPage = 5;
        int itemCount = 0;
        int currentPage = 5;
        PageInfo instance = new PageInfo(maxItemsOnPage, itemCount, currentPage);

        assertEquals("getNumberOfPages", 0, instance.getNumberOfPages());
        //assertEquals("getFirstItemIndex",0, instance.getFirstItemIndex());
        assertEquals("getCurrentPage", 0, instance.getCurrentPage());
        assertFalse("isNext()", instance.getHasNext());
        assertFalse("isPrevious()", instance.getHasPrevious());
        assertFalse("hasItems()", instance.getHasItems());

    }

    @Test
    public void testGetNumberOfPages_Exception() {
        int maxItemsOnPage = 5;
        int itemCount = 11;
        int currentPage = 0;
        PageInfo instance = new PageInfo(maxItemsOnPage, itemCount, currentPage);
        assertEquals("getCurrentPage", 1, instance.getCurrentPage());
    }
}
