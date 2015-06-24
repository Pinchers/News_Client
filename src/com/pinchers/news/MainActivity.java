package com.pinchers.news;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pinchers.adapter.CustomSimpleAdapter;
import com.pinchers.news_client.R;

public class MainActivity extends Activity {
	private static final int COLUMN_WIDTH = 65;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取新闻分类
		String[] categoryArray = this.getResources().getStringArray(
				R.array.categories);

		// 将新闻分类文本加入到List中
		List<HashMap<String, String>> categories = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < categoryArray.length; i++) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("category_title", categoryArray[i]);
			categories.add(hashMap);
		}

		// 创建类别标题栏的适配器
		CustomSimpleAdapter categoryAdapter = new CustomSimpleAdapter(this,
				categories, R.layout.category_title,
				new String[] { "category_title" },
				new int[] { R.id.category_title });

		// 创建GridView的实例
		GridView category = new GridView(this);
		category.setColumnWidth(COLUMN_WIDTH);
		//设置列的数量为自动填充
		category.setNumColumns(GridView.AUTO_FIT);
		//设置背景为透明
		category.setSelector(new ColorDrawable(Color.TRANSPARENT));
		//计算宽度为每一列的宽度*列数
		int width = COLUMN_WIDTH * categories.size();
		LayoutParams params = new LayoutParams(width, LayoutParams.WRAP_CONTENT);
		category.setLayoutParams(params);
		category.setGravity(Gravity.CENTER);

		// 为GridView设置适配器
		category.setAdapter(categoryAdapter);

		// 动态加载GridView
		LinearLayout categoryList = (LinearLayout) findViewById(R.id.category_layout);
		categoryList.addView(category);

		// 对GridView添加单击事件
		category.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView categoryTitle;
				for (int i = 0; i < parent.getChildCount(); i++) {
					categoryTitle = (TextView) parent.getChildAt(i);
					categoryTitle.setBackgroundDrawable(null);
					categoryTitle.setTextColor(0xffadb2ad);
				}

				categoryTitle = (TextView) parent.getChildAt(position);
				categoryTitle
						.setBackgroundResource(R.drawable.categorybar_item_background);
				categoryTitle.setTextColor(0xffffffff);

				Toast.makeText(MainActivity.this, categoryTitle.getText(),
						Toast.LENGTH_SHORT).show();
				
			}
		});

		// 右侧滚动按钮的点击
		final HorizontalScrollView categoryScrollView = (HorizontalScrollView) findViewById(R.id.categorybar_scrollview);
		Button categoryArrowRight = (Button) findViewById(R.id.category_arrow_right);
		categoryArrowRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				categoryScrollView.fling(800);
			}
		});
	}
}
