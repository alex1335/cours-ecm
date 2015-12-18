package fr.cmm.helper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Pagination {
    // 1 based page index
    public static final int PAGINATION_SIZE = 10;

    private int pageIndex;

    private int pageSize;

    private long count;

    public int getPreviousPageIndex() {
        return isFirstPage() ? pageIndex : pageIndex - 1;
    }

    public int getNextPageIndex() {
        return isLastPage() ? pageIndex : pageIndex + 1;
    }

    public boolean isFirstPage() {
        return pageIndex == 1;
    }

    public boolean isLastPage() {
        return pageIndex * pageSize >= count;
    }

    public int getPageCount() {
        if (count == 0){
            return 1;
        } else if (count % pageSize != 0){
            return (int) count / pageSize + 1;
        }else {
            return (int) count / pageSize;
        }
    }

    public List<Integer> getPages() {
        List<Integer> listPages = new ArrayList<>();
        int pageCount = getPageCount();
        for (int i = 1; i <= pageCount; i++) {
            listPages.add(i);
        }
        if (pageCount > PAGINATION_SIZE) {
            return listPages.subList(0,PAGINATION_SIZE);
        }else return listPages;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
