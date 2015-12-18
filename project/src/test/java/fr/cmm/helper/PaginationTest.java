package fr.cmm.helper;

import org.junit.Test;

import static java.util.Arrays.asList;
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

    @Test
    public void getPagesSizeBelowValueMax() {
        Pagination pagination = new Pagination();
        pagination.setCount(50);
        pagination.setPageSize(20);
        assertEquals(asList(1,2,3), pagination.getPages() );
    }

    @Test
    public void getPagesSizeGreaterThanValueMax() {
        Pagination pagination = new Pagination();
        pagination.setCount(250);
        pagination.setPageSize(20);
        assertEquals(asList(1,2,3,4,5,6,7,8,9,10), pagination.getPages() );
    }
}