package com.xulihuazj.pms.impl.diy;

/**
 * 自定义HashMap
 *
 * @author xulihua
 * @see Object#hashCode()
 * @see Collection
 * @see Map
 * @see TreeMap
 * @see Hashtable
 * @since 1.2
 */
public interface DiyMap<K, V> {

    V put(K k, V v);

    K get(K k);

    interface Entry<K, V> {
        public K getKey();

        public V getValue();
    }

}
