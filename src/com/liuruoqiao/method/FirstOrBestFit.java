package com.liuruoqiao.method;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirstOrBestFit {
	private List<Map<String, String>> list;
	private String jsize;
	private int j;

	public FirstOrBestFit(List<Map<String, String>> list, String jsize) {
		this.list = list;
		this.jsize = jsize;
	}

	/**
	 * 改变list变数据
	 * 
	 * @return
	 */
	public List<Map<String, String>> changeData() {
				list.get(j).put(
						"bsize",
						Integer.toString(Integer.parseInt(list.get(j).get(
								"bsize"))
								- Integer.parseInt(jsize)));
				list.get(j).put(
						"badr",
						Integer.toString(Integer.parseInt(list.get(j).get(
								"badr"))
								+ Integer.parseInt(jsize)));
		
		return list;
	}
	
	/**
	 * list表由是否成功匹配，匹配后所属空闲区名，匹配后作业首址组成
	 * @return
	 */
	public List<String> getResult(){
		List<String> resultlist = new ArrayList<String>();
		resultlist.add("");
		resultlist.add("");
		resultlist.add("");
		for (int i = 0; i < list.size(); i++) {
			if (Integer.parseInt(list.get(i).get("bsize")) >= Integer
					.parseInt(jsize)) {//最佳算法中，找到的第一个一定是最小的，因为大小以及按顺序排好了
				resultlist.set(0, "ok");
				resultlist.set(1,list.get(i).get("bname"));
				resultlist.set(2,list.get(i).get("badr"));
				j=i;
				break;
			}
		}
		return resultlist;
	}

}
