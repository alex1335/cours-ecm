package fr.cmm.helper;

import org.junit.Test;

import static org.junit.Assert.*;


public class PaginationTest {
    @Test
    public void pageCountTestWhenWrong() {
        Pagination pagination = new Pagination();
        pagination.setCount(50);
        pagination.setPageSize(20);
        assertEquals(3, pagination.getPageCount());
    }

    @Test
    public void pageCountTestWhenTrue() {
        Pagination pagination = new Pagination();
        pagination.setCount(40);
        pagination.setPageSize(20);
        assertEquals(2, pagination.getPageCount());
    }

    @Test
    public void pageCountTestWhenPageSizeEmpty() {
        Pagination pagination = new Pagination();
        pagination.setCount(0);
        pagination.setPageSize(20);
        assertEquals(1, pagination.getPageCount());
    }
}