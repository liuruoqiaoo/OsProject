package com.liuruoqiao.sort;

import java.util.Comparator;
import java.util.Map;

public abstract class Sort implements Comparator<Map<String, String>> {
	 
    private String key;
 
    private String order;
 
    public Sort(String key,String order) {
        this.key = key;
        this.order = order;
    }
 
    /*
     * (non-Javadoc)
     *
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Map<String, String> o1, Map<String, String> o2){
        if (order.equals("asc")) {
            return  Integer.valueOf(o1.get(key).toString())-Integer.valueOf(o2.get(key).toString()) ;
        }else {
            return  Integer.valueOf(o2.get(key).toString())-Integer.valueOf(o1.get(key).toString()) ;
        }
    }
 
}