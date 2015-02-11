package com.liuruoqiao.method;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 总是分配分区中最大的那块
 * 
 * @author Ruoqiao
 * 
 */
public class WorstFit {

	private List<Map<String, String>> list;
	private String jsize;

	public WorstFit(List<Map<String, String>> list, String jsize) {
		this.list = list;
		this.jsize = jsize;
	}

	/**
	 * @return本来空区list就是按照大小由小到达排列，所以只需要看最大一个，也就是最大的一个
	 */
	public boolean getResult() {
		if (Integer.parseInt(list.get(0).get("bsize")) > Integer
				.parseInt(jsize)) {
			return true;
		}
		return false;
	}

	public List<Map<String, String>> changeData() {
		if (getResult()) {
			list.get(0).put(
					"bsize",
					Integer.toString(Integer.parseInt(list.get(0).get("bsize"))
							- Integer.parseInt(jsize)));
			list.get(0).put(
					"badr",
					Integer.toString(Integer.parseInt(list.get(0).get("badr"))
							+ Integer.parseInt(jsize)));
		}

		return list;
	}

}
