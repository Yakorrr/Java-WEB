package servlet_tests;

import org.junit.jupiter.api.Test;
import lab2.controller.util.Pagination;

import java.util.ArrayList;
import java.util.List;

class PaginationTest {
    @Test
    void getNumberOfPagesTest() {
        Pagination<Integer> pag = new Pagination<>();
        Pagination.setEntriesPerPage(5);

        List<Integer> empty = new ArrayList<>();
        assert (pag.getNumberOfPages(empty) == 1);

        List<Integer> lessThanFive = new ArrayList<>();
        lessThanFive.add(1);
        lessThanFive.add(2);
        assert (pag.getNumberOfPages(lessThanFive) == 1);

        List<Integer> pages = new ArrayList<>();
        pages.add(1);
        pages.add(2);
        pages.add(1);
        pages.add(2);
        pages.add(1);
        assert (pag.getNumberOfPages(pages) == 1);

        List<Integer> more = new ArrayList<>();
        more.add(1);
        more.add(2);
        more.add(1);
        more.add(2);
        more.add(1);
        more.add(2);
        assert (pag.getNumberOfPages(more) == 2);
    }

    @Test
    void getEntriesTest() {
        Pagination<Integer> pag = new Pagination<>();
        Pagination.setEntriesPerPage(5);

        List<Integer> empty = new ArrayList<>();
        List<Integer> emptyCopy = new ArrayList<>();
        assert (pag.getEntries(empty, 1).equals(emptyCopy));
        assert (pag.getEntries(empty, 2).equals(emptyCopy));
        assert (pag.getEntries(empty, 3).equals(emptyCopy));

        List<Integer> lessThanFive = new ArrayList<>();
        List<Integer> lessThanFiveCopy = new ArrayList<>();
        lessThanFive.add(1);
        lessThanFive.add(2);

        lessThanFiveCopy.add(1);
        lessThanFiveCopy.add(2);
        assert (pag.getEntries(lessThanFive, 1).equals(lessThanFiveCopy));
        assert (pag.getEntries(lessThanFive, 2).equals(empty));
        assert (pag.getEntries(lessThanFive, 3).equals(empty));

        List<Integer> pages = new ArrayList<>();
        pages.add(1);
        pages.add(2);
        pages.add(1);
        pages.add(2);
        pages.add(1);

        List<Integer> pagesCopy = new ArrayList<>();
        pagesCopy.add(1);
        pagesCopy.add(2);
        pagesCopy.add(1);
        pagesCopy.add(2);
        pagesCopy.add(1);
        assert (pag.getEntries(pages, 1).equals(pagesCopy));
        assert (pag.getEntries(pages, 2).equals(empty));
        assert (pag.getEntries(pages, 3).equals(empty));
        assert (pag.getEntries(pages, 6).equals(empty));

        List<Integer> more = new ArrayList<>();
        more.add(1);
        more.add(2);
        more.add(1);
        more.add(2);
        more.add(1);
        more.add(2);
        List<Integer> morePage1 = new ArrayList<>();
        morePage1.add(1);
        morePage1.add(2);
        morePage1.add(1);
        morePage1.add(2);
        morePage1.add(1);
        List<Integer> morePage2 = new ArrayList<>();
        morePage2.add(2);
        assert (pag.getEntries(more, 1).equals(morePage1));
        assert (pag.getEntries(more, 2).equals(morePage2));
        assert (pag.getEntries(more, 3).equals(empty));
    }
}
