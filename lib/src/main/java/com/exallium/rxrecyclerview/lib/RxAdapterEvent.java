/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alex Hart
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.exallium.rxrecyclerview.lib;

import rx.functions.Func1;

/**
 * Event for adapter change.
 *
 * If you would like to extend this object, the recommended way is to define a subclass with TYPE UNKNOWN.  You can then
 * filter the incoming observable within your adapter for this subclass.  Since you are required to define your types
 * when you declare your adapter, K and V will be known, so a non-parameterized Subclass is an option here.  If you
 * really need parameterized subclasses otherwise, my recommendation is to do something along the same lines as I did
 * here with Type, OR figure out the proper generics typing for the RX Java Methods to like your subscribers.
 *
 * You can listen to a custom subclass in your adapter constructor with observer.ofType(), or you can create a Filter
 * as I have done in this class an event's subtype against a type passed by constructor. (See RxRecyclerView.java for
 * an example, and RxAdapterEvent.TypeFilter for an example)
 *
 * @param <K> The Key for this object (such as an ID number)
 * @param <V> The Value for this object (The object itself)
 */
public class RxAdapterEvent<K, V> {

    public static final class TypeFilter<K, V> implements Func1<RxAdapterEvent<K, V>, Boolean> {

        private final TYPE type;

        public TypeFilter(TYPE type) {
            this.type = type;
        }

        @Override
        public Boolean call(RxAdapterEvent<K, V> rxAdapterEvent) {
            return rxAdapterEvent.getType() == type;
        }
    }


    public enum TYPE {
        ADD,
        REMOVE,
        UNKNOWN
    }

    private final TYPE type;
    private final K key;
    private final V value;

    public RxAdapterEvent(TYPE type, K key, V value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public TYPE getType() {
        return type;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

}