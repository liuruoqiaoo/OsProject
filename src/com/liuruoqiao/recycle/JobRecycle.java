package com.liuruoqiao.recycle;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liuruoqiao.sort.Sort;

public class JobRecycle {
	private String text;

	public List<Map<String, String>> recycle(
			List<Map<String, String>> new_block_list, Map<String, String> map) {
		// TODO Auto-generated method stub
		int up=0;
		int down=0;
		int up_num = 0,down_num=0;
		int jsize=Integer.valueOf(map.get("jsize"));
		int jadr=Integer.valueOf(map.get("jadr"));
		for(int i=0;i<new_block_list.size();i++)
		{
			if((Integer.valueOf(new_block_list.get(i).get("badr"))+Integer.valueOf(new_block_list.get(i).get("bsize")))==jadr)
			{
				up=1;
				up_num=i;
			}
			else if(Integer.valueOf(new_block_list.get(i).get("badr"))==(jadr+jsize))
			{
				down=1;
				down_num=i;
			}
		}
		if(up==1 && down==0)//上有
		{
			int n=Integer.valueOf(new_block_list.get(up_num).get("bsize"))+jsize;
			new_block_list.get(up_num).put("bsize",Integer.toString(n));
			text="该回收区上有空闲区【"+new_block_list.get(up_num).get("bname")+"】，与其合并，大小增加为："+Integer.toString(n)+"KB";
			
		}else if(up==0 && down==1)//下有
		{
			int n=Integer.valueOf(new_block_list.get(down_num).get("bsize"))+jsize;
			new_block_list.get(down_num).put("bsize",Integer.toString(n));
			new_block_list.get(down_num).put("badr",map.get("jadr"));
			text="该回收区下有空闲区【"+new_block_list.get(down_num).get("bname")+"】，与其合并，大小增加为："+Integer.toString(n)+"KB，并修改首址为："+map.get("jadr")+"KB";


		}else if(up==1 && down==1)//上下都有
		{
			int n=Integer.valueOf(new_block_list.get(up_num).get("bsize"))+jsize+Integer.valueOf(new_block_list.get(down_num).get("bsize"));
			new_block_list.get(up_num).put("bsize",Integer.toString(n));
			text="该回收区上、下都有空闲区，将上空闲区【"+new_block_list.get(up_num).get("bname")+"】和下空闲区【"+new_block_list.get(down_num).get("bname")+"】和回收区本身三者合并，大小增加为："+Integer.toString(n)+"KB，并删除下空闲区";
			new_block_list.remove(down_num);
		}else//上下无
		{
			Map<String,String> map1= new HashMap<String,String>();
			map.put("bname", "*"+map.get("jname")+"*");
			map.put("badr", map.get("jadr"));
			map.put("bsize", map.get("jsize"));
			new_block_list.add(map);
			Collections.sort(new_block_list,new Sort("badr","asc") {                
	            });//list排序
			text="该回收区上、下都无有空闲区，因此将该回收区新增为一个空闲区，名称为："+"*"+map.get("jname")+"*"+"，首址为："+map.get("jadr")+"KB，大小为："+map.get("jsize")+"KB";

		}
		return new_block_list;
	}
	public String getText(){
		return text;
	}
	

}
