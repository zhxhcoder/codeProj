/**
 * The MIT License
 * Copyright (c) 2014-2016
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.zhxh.codeproj.multithread.doublechecked.locking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Inventory
 */
public class Inventory {
    private final int inventorySize;
    private final List<Item> items;
    private final Lock lock;

    /**
     * Constructor
     */
    public Inventory(int inventorySize) {
        this.inventorySize = inventorySize;
        this.items = new ArrayList<>(inventorySize);
        this.lock = new ReentrantLock();
    }

    /**
     * Add item
     */
    public boolean addItem(Item item) {
        if (items.size() < inventorySize) {
            lock.lock();
            try {
                if (items.size() < inventorySize) {
                    items.add(item);
                    System.out.println(items.size() + "{}: items.size()={}" + Thread.currentThread() + ", inventorySize=" + inventorySize);
                    return true;
                }
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    /**
     * Get all the items in the inventory
     *
     * @return All the items of the inventory, as an unmodifiable list
     */
    public final List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
}
