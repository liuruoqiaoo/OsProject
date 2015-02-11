package com.liuruoqiao.adapter;

import java.util.List;
import java.util.Map;

import com.example.osblock.R;
import com.liuruoqiao.adapter.BlockAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class JobAdapter extends BaseAdapter{
	private Context context;
	private List<Map<String, String>> list;
	
	public JobAdapter(Context context, List<Map<String, String>> job_list){
		this.context=context;
		this.list = job_list;
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
		TextView text_jname;
		TextView text_jadr;
		TextView text_jsize;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(arg1==null){
			arg1 = LayoutInflater.from(context).inflate(R.layout.job_show, null);
			holder = new ViewHolder();
			holder.text_jadr = (TextView)arg1.findViewById(R.id.text_jadr);
			holder.text_jname = (TextView)arg1.findViewById(R.id.text_jname);
			holder.text_jsize = (TextView)arg1.findViewById(R.id.text_jsize);
			arg1.setTag(holder);

		}
		else{
			holder = (ViewHolder)arg1.getTag();
		}
		holder.text_jadr.setText("首址："+list.get(arg0).get("jadr")+"KB");
		holder.text_jname.setText("作业"+list.get(arg0).get("jname"));
		holder.text_jsize.setText("大小："+list.get(arg0).get("jsize")+"KB");

		return arg1;
	}

}
