package com.tz.fileexplorer.adapter;

import java.util.HashMap;
import java.util.Map;
/**
 * ´ıÍêÉÆ 
 *
 */
public class FileRecorder<K, V> {
	Map<K, V> map;
	
	public FileRecorder() {
		map = new HashMap<K, V>();
	}

	public void put(K key, V value) {
		map.put(key, value);
	}

	public V get(K key) {
		return map.get(key);
	}
}
