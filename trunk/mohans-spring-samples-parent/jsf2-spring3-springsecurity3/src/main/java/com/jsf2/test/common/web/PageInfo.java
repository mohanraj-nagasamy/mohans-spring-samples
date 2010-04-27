package com.jsf2.test.common.web;

import java.io.Serializable;

/**
 *
 * @author Dimitar Makariev
 */
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 5715138212389353221L;

    public PageInfo(int maxItemsOnPage, long itemCount, long currentPage) {
        this.maxItemsOnPage = maxItemsOnPage;
        this.itemCount = itemCount;
        this.numberOfPages = (long) Math.ceil((double) itemCount / (double) maxItemsOnPage);
        this.currentPage = Math.min(this.numberOfPages, (currentPage < 1) ? 1 : currentPage);
        this.firstItemIndex = (int) (this.currentPage - 1) * maxItemsOnPage;
        this.next = (this.currentPage < this.numberOfPages);
        this.previous = this.currentPage > 1;
    }
    private final int maxItemsOnPage; //on page -parameter
    private final long itemCount; //parameter
    private final long currentPage; //parameter
    private final long numberOfPages; //this is also last page..
    private final int firstItemIndex;
    private final boolean next;
    private final boolean previous;

    //calculated
    public long getNumberOfPages() {
        return numberOfPages;
    }

    public long getItemCount() {
        return itemCount;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public boolean getHasNext() {
        return next;
    }

    public boolean getHasPrevious() {
        return previous;
    }

    public boolean getHasItems() {
        return itemCount > 0;
    }

    public long getNextPage() {
        return this.getHasNext() ? this.getCurrentPage() + 1 : this.getCurrentPage();
    }

    public long getPreviousPage() {
        return this.getHasPrevious() ? this.getCurrentPage() - 1 : this.getCurrentPage();
    }

    public int getMaxItemsOnPage() {
        return maxItemsOnPage;
    }

    public int getFirstItemIndex() {
        return firstItemIndex;
    }
}
