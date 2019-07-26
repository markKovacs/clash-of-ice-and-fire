package com.coinf.util;

import java.util.Collection;

public class CollectionUtils {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean hasAtLeast3(Collection<?> collection) {
        return collection != null && collection.size() >= 3;
    }

}
