package com.example.osblock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liuruoqiao.adapter.BlockAdapter;
import com.liuruoqiao.adapter.JobAdapter;
import com.liuruoqiao.method.CircleFit;
import com.liuruoqiao.method.FirstOrBestFit;
import com.liuruoqiao.method.WorstFit;
import com.liuruoqiao.recycle.JobRecycle;
import com.liuruoqiao.sort.Sort;

import android.R.integer;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
	private Button button1, button2, button3;
	private ListView list_block;
	private TextView text,title;
	private List<Map<String, String>> new_block_list;
	private List<Map<String, String>> job_list;
	private String j_name, j_size;
	private BlockAdapter blockAdapter;
	private JobAdapter jobAdapter;
	private String flag,lastname="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getFlag();
		init();
		job_list = new ArrayList<Map<String, String>>();
		new_block_list = new ArrayList<Map<String, String>>();
		/****** 增加新分区的对话框 *****************/
		final View view1 = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.add_block, null);
		final EditText bname = (EditText) view1.findViewById(R.id.bname);
		final EditText badr = (EditText) view1.findViewById(R.id.badr);
		final EditText bsize = (EditText) view1.findViewById(R.id.bsize);
		final Button add_more_block = (Button) view1
				.findViewById(R.id.add_more_block);
		/****** 分配作业对话框 *****************/
		final View view2 = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.job_dis, null);
		final EditText jname = (EditText) view2.findViewById(R.id.jname);
		final EditText jsize = (EditText) view2.findViewById(R.id.jsize);
		/****** 分区list *****************/
		blockAdapter = new BlockAdapter(MainActivity.this, new_block_list);
		list_block.setAdapter(blockAdapter);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 弹出对话框
				final int i = new_block_list.size();
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("请输入空闲区的相关数据")
						.setView(view1)
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub
										Map<String, String> map = new HashMap<String, String>();
										map.put("bname", bname.getText()
												.toString());
										map.put("badr", badr.getText()
												.toString());
										map.put("bsize", bsize.getText()
												.toString());
										new_block_list.add(map);
										sort();
											switch(flag)
											{
											case "cf":
											case "ff":	
												text.setText("您增加了"
														+ Integer
																.toString(new_block_list
																		.size() - i)
														+ "个空闲分区，将按首址由小到大排序");
												break;
											case "wf":
												text.setText("您增加了"
														+ Integer
																.toString(new_block_list
																		.size() - i)
														+ "个空闲分区，将按大小由大到小排序");
												break;
											case "bf":
												text.setText("您增加了"
														+ Integer
																.toString(new_block_list
																		.size() - i)
														+ "个空闲分区，将按大小由小到大排序");
												break;
											}
										blockAdapter.notifyDataSetChanged();// list表动态表化
										bname.setText("");
										badr.setText("");
										bsize.setText("");
										ViewGroup group = (ViewGroup) view1
												.getParent();// 从父视图中去掉子视图
										group.removeView(view1);

									}

								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub
										ViewGroup group = (ViewGroup) view1
												.getParent();
										group.removeView(view1);
									}
								}).create().show();
				add_more_block.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Map<String, String> map = new HashMap<String, String>();
						map.put("bname", bname.getText().toString());
						map.put("badr", badr.getText().toString());
						map.put("bsize", bsize.getText().toString());
						new_block_list.add(map);
						sort();
							switch(flag)
							{
							case "cf":
							case "ff":	
								text.setText("您增加了"
										+ Integer
												.toString(new_block_list
														.size() - i)
										+ "个空闲分区，将按首址由小到大排序");
								break;
							case "wf":
								text.setText("您增加了"
										+ Integer
												.toString(new_block_list
														.size() - i)
										+ "个空闲分区，将按大小由大到小排序");
								break;
							case "bf":
								text.setText("您增加了"
										+ Integer
												.toString(new_block_list
														.size() - i)
										+ "个空闲分区，将按大小由小到大排序");
								break;
							}
						bname.setText("");
						badr.setText("");
						bsize.setText("");
					}
					
				});
				
			}
		});
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("请输入作业的相关数据")
						.setView(view2)
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub
										j_name = jname.getText().toString();
										j_size = jsize.getText().toString();
										match();
										jname.setText("");
										jsize.setText("");
										ViewGroup group = (ViewGroup) view2
												.getParent();
										group.removeView(view2);
									}

								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub
										ViewGroup group = (ViewGroup) view2
												.getParent();
										group.removeView(view2);
									}
								}).create().show();
			}
		});
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(MainActivity.this);
				dialog.setTitle("请选择现在要回收的作业");
				dialog.setContentView(R.layout.recycle_job);
				ListView list_recycle = (ListView) dialog
						.findViewById(R.id.list_recycle);
				Button cancle = (Button) dialog.findViewById(R.id.cancle_btn);
				/****** 已经分配作业list *****************/
				jobAdapter = new JobAdapter(MainActivity.this, job_list);
				list_recycle.setAdapter(jobAdapter);
				list_recycle.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						JobRecycle recycle = new JobRecycle();
						new_block_list = recycle.recycle(new_block_list,
								job_list.get(arg2));
						sort();
						job_list.remove(arg2);
						blockAdapter.notifyDataSetChanged();// list表动态表化
						text.setText(recycle.getText());
						dialog.dismiss();
					}

				});
				cancle.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						dialog.dismiss();

					}
				});
				dialog.show();

			}
		});

	}

	private void getFlag() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		flag = intent.getStringExtra("choice");
	}

	private void init() {
		// TODO Auto-generated method stub
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		list_block = (ListView) findViewById(R.id.list_block);
		text = (TextView) findViewById(R.id.text);
		title = (TextView) findViewById(R.id.title);

		switch (flag) {
		case "cf":title.setText("循环首次适应算法");
			break;
		case "ff":title.setText("首次适应算法");
			break;
		case "wf":title.setText("最坏适应算法");
			break;
		case "bf":title.setText("最佳适应算法");
			break;
		}
	}

	private void match() {
		// TODO Auto-generated method stub
		// 计算该作业是否能够被分配，若被分配，则讲作业加入listjob，并打印文字
		List<String> resultlist;
		switch (flag) {
		case "bf":
		case "ff":
			FirstOrBestFit ff = new FirstOrBestFit(new_block_list, j_size);
			resultlist = ff.getResult();
			if (resultlist.get(0).equals("")) {
				text.setText("空闲区不足，" + j_name + "作业无法被分配");
			} else {
				new_block_list = ff.changeData();
				sort();
				blockAdapter.notifyDataSetChanged();// list表动态表化
				// 将该作业添加到已分配作业list中
				Map<String, String> map = new HashMap<String, String>();
				map.put("jname", j_name);
				map.put("jsize", j_size);
				map.put("jadr", resultlist.get(2));
				map.put("j_bname", resultlist.get(1));
				job_list.add(map);
				text.setText(j_name + "作业被分配到【" + resultlist.get(1) + "】分区，首址为："
						+ resultlist.get(2));
			}
			break;
		case "wf":
			WorstFit wf = new WorstFit(new_block_list, j_size);
		if (wf.getResult()) {
			// 将该作业添加到已分配作业list中
						Map<String, String> map = new HashMap<String, String>();
						map.put("jname", j_name);
						map.put("jsize", j_size);
						map.put("jadr", new_block_list.get(0).get("badr"));
						map.put("j_bname", new_block_list.get(0).get("bname"));
						job_list.add(map);
						text.setText(j_name + "作业被分配到【" +new_block_list.get(0).get("bname") + "】分区，首址为："
								+ new_block_list.get(0).get("badr"));
						new_block_list = wf.changeData();
						sort();
						blockAdapter.notifyDataSetChanged();// list表动态表化
		} else {
			text.setText("空闲区不足，" + j_name + "作业无法被分配");
		}
			break;
		case "cf":CircleFit cf = new CircleFit(new_block_list, j_size,lastname);
		resultlist = cf.getResult();
		if (resultlist.get(0).equals("")) {
			text.setText("空闲区不足，" + j_name + "作业无法被分配");
		} else {
			new_block_list = cf.changeData();
			sort();
			blockAdapter.notifyDataSetChanged();// list表动态表化
			// 将该作业添加到已分配作业list中
			Map<String, String> map = new HashMap<String, String>();
			map.put("jname", j_name);
			map.put("jsize", j_size);
			map.put("jadr", resultlist.get(2));
			map.put("j_bname", resultlist.get(1));
			job_list.add(map);
			text.setText(j_name + "作业被分配到【" + resultlist.get(1) + "】分区，首址为："
					+ resultlist.get(2));
			lastname=resultlist.get(1);
		}
			break;
		}
	}

	private void sort() {
		switch(flag)
		{
		case "cf":
		case "ff":	
			Collections.sort(new_block_list,
					new Sort("badr", "asc") {
					});// list排序,按照首址大到小
			break;
		case "wf":
			Collections.sort(new_block_list,
					new Sort("bsize", "desc") {
					});// list排序,按照大小，由大到小
			break;
		case "bf":
			Collections.sort(new_block_list,
					new Sort("bsize", "asc") {
					});// list排序,按照大小，由小到大
			break;
		}
		
	}

}
