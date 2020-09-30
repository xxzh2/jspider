package com.ginkgo.jspider.crawl;

import java.util.ArrayList;

public class FixedList<T> extends ArrayList<T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5619134466752217726L;
    private int fixedsize = 0;

    public FixedList(int fixed){
        this.fixedsize = fixed;
    }

    @Override
    public boolean add(T e) {
        if (this.size() < this.fixedsize) {
            super.add(e);
            return true;
        } else {
            return false;
        }
    }

}
