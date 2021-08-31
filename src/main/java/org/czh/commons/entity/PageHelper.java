package org.czh.commons.entity;

import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-18
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class PageHelper {

    public static final int CURRENT_PAGE = 1;
    public static final int PAGE_SIZE = 20;
    private static final ThreadLocal<Page<?>> localPage = new ThreadLocal<>();

    public static <T> Page<T> enablePage() {
        return enablePage(CURRENT_PAGE, PAGE_SIZE);
    }

    public static <T> Page<T> enablePage(Integer currentPage, Integer pageSize) {
        EmptyAssert.allNotNull(currentPage, pageSize);
        Page<T> page = new Page<>(currentPage, pageSize, true);
        localPage.set(page);

        return page;
    }

    public static <T> Page<T> getZeroPage() {
        //noinspection unchecked
        return (Page<T>) localPage.get();
    }

    public static <T> Page<T> setAndReturn(List<T> list) {
        @SuppressWarnings("unchecked")
        Page<T> page = (Page<T>) localPage.get();
        page.setList(list);

        pageDisabled();
        removePage();

        return page;
    }

    public static boolean pageEnable() {
        Page<?> page = localPage.get();
        return EmptyValidate.isNotNull(page) && page.isPageEnable();
    }

    public static void pageDisabled() {
        Page<?> page = localPage.get();
        if (EmptyValidate.isNotNull(page)) {
            page.setPageEnable(false);
        }
    }

    private static void removePage() {
        localPage.remove();
    }
}
