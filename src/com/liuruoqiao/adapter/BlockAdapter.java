package com.liuruoqiao.adapter;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import com.example.osblock.MainActivity;
import com.example.osblock.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BlockAdapter extends BaseAdapter{
	
	private Context context;
	private List<Map<String, String>> list;
	//private LayoutInflater inflater;
	
	public BlockAdapter(Context context, List<Map<String, String>> new_block_list){
		this.context=context;
		this.list = new_block_list;
		//inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	class ViewHolder{
		TextView text_bname;
		TextView text_badr;
		TextView text_bsize;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(arg1==null){
			arg1 = LayoutInflater.from(context).inflate(R.layout.show_block, null);
			holder = new ViewHolder();
			holder.text_badr = (TextView)arg1.findViewById(R.id.text_badr);
			holder.text_bname = (TextView)arg1.findViewById(R.id.text_bname);
			holder.text_bsize = (TextView)arg1.findViewById(R.id.text_bsize);
			arg1.setTag(holder);

		}
		else{
			holder = (ViewHolder)arg1.getTag();
		}
		holder.text_badr.setText("首址："+list.get(arg0).get("badr")+"KB");
		holder.text_bname.setText("空闲分区"+list.get(arg0).get("bname"));
		holder.text_bsize.setText("大小："+list.get(arg0).get("bsize")+"KB");

		return arg1;
	}

}
