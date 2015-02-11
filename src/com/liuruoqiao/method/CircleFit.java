package com.liuruoqiao.method;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CircleFit {

	private List<Map<String, String>> list;
	private String jsize;
	private String lastname;
	private int j;

	public CircleFit(List<Map<String, String>> list, String jsize, String lastname) {
		this.list = list;
		this.jsize = jsize;
		this.lastname=lastname;
		System.out.println(lastname);
	}

	/**
	 * 比较list中大小和作业大小，选择合适的空闲区，并修改后返回
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
	 * 
	 * @return
	 */
	public List<String> getResult() {
		List<String> resultlist = new ArrayList<String>();
		resultlist.add("");
		resultlist.add("");
		resultlist.add("");
		int now = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("bname").equals(lastname)) {
				now = (i+1)%list.size();
				break;
			}
			}
		System.out.println("找到now"+now);
			if (now == -1) {//如果找不到上次的分区，就是分区在回收的时候被合并了，那就从第一个开始找
				for (int k = 0; k < list.size(); k++) {
					if (Integer.parseInt(list.get(k).get(
							"bsize")) >= Integer.parseInt(jsize)) {
						resultlist.set(0, "ok");
						resultlist.set(1,
								list.get(k).get("bname"));
						resultlist.set(2,
								list.get(k).get("badr"));
						j = k;
						System.out.println("开头找");

						break;
					}
				}
				return resultlist;
			} else {
				for (int k = 0; k < list.size(); k++, now++) {
					if (Integer.parseInt(list.get(now % list.size()).get(
							"bsize")) >= Integer.parseInt(jsize)) {
						resultlist.set(0, "ok");
						resultlist.set(1,
								list.get(now % list.size()).get("bname"));
						resultlist.set(2,
								list.get(now % list.size()).get("badr"));
						j = now % list.size();
						System.out.println("找到这个"+j);

						break;
					}
				}
				return resultlist;
			}
	}

}
