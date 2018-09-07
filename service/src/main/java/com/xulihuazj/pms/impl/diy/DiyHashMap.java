package com.xulihuazj.pms.impl.diy;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * DiyHashMap.java 1.0.0 2018/08/17  14:20
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/08/17  14:20 created by xulihua
 */
public class DiyHashMap<K, V> implements DiyMap<K, V> {

    // 初始容量 16
    private int defaultLength = 16;

    // 负载因子，扩容标准
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    // 使用数组位置的总数
    private double useSize;

    // 定义Map 骨架之一数组
    private DiyEntry<K, V>[] tables;

    /**
     * 构造器
     *
     * @param defaultLength
     */
    public DiyHashMap(int defaultLength) {
        if (defaultLength > 0) {
            this.defaultLength = defaultLength;
        }
        this.tables = new DiyEntry[this.defaultLength];
    }

    // 快速存取hash算法
    @Override
    public V put(K k, V v) {
        if (this.useSize > this.defaultLength * this.defaultLength) {
            // 进行扩容
            this.up2Size();
        }
        // 通过key来计算出 存储的位置
        int index = this.getIndex(k, tables.length);
        DiyEntry<K, V> entry = this.tables[index];
        DiyEntry<K, V> newEntry = new DiyEntry<>(k, v, null);
        if (null == entry) {
            this.tables[index] = newEntry;
            this.useSize++;
        } else {
            // 维护数组相同位置队列
            DiyEntry<K, V> tmp;
            while (null != (tmp = this.tables[index])) {
                tmp = tmp.next;
            }
            tmp.next = newEntry;
        }
        return newEntry.getValue();
    }

    private int getIndex(K k, int length) {
        // 通常是hashCode 取膜法
        int m = length - 1;
        int index = diyHash(k.hashCode()) & m;
        return index >= 0 ? index : -index;
    }

    // 创建自己的hash算法，保证计算出的位置，在数组中均匀分布
    private int diyHash(int hashCode) {
        hashCode = hashCode ^ ((hashCode >>> 20) ^ (hashCode >>> 12));
        return hashCode ^ ((hashCode >>> 7) ^ (hashCode >>> 4));
    }

    // 数组容量 扩容
    private void up2Size() {
        DiyEntry<K, V>[] newTable = new DiyEntry[this.defaultLength * 2];
        // 将原table中的entry重新散列到新的table中
        againHash(newTable);
    }

    private void againHash(DiyEntry<K, V>[] newTables) {
        // 数组里面的对象封装到List中，包括同一位置有列表结构的都解析出来
//        List<DiyEntry<K, V>> entryList = new ArrayList<>();
//        for (int i = 0; i < this.tables.length; i++) {
//            if(null == this.tables[i]){
//                continue;
//            }
//            findEntryByNext(this.tables[i],newTables);
//        }
//        if (CollectionUtils.isNotEmpty(entryList)) {
//            this.useSize = 0;
//            this.defaultLength = this.defaultLength * 2;
//            this.tables = newTables;
//            for(DiyEntry<K,V> entry:entryList){
//                if(null != entry.next){
//                    entry = entry.next;
//                }
//                put(entry.getKey(),entry.getValue());
//            }
//        }
    }

    private void findEntryByNext(Entry<K,V> entry,List<Entry<K,V>> entryList){

    }

    @Override
    public K get(K k) {
        return k;
    }


    class DiyEntry<K, V> implements DiyHashMap.Entry<K, V> {

        private K k;

        private V v;

        DiyEntry<K, V> next;

        public DiyEntry(K k, V v, DiyEntry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }


        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }


}
