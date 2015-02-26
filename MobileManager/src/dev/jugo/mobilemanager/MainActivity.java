package dev.jugo.mobilemanager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends FragmentActivity
{
	Class<?>[]				frames	= { TrafficFragment.class, TrafficFragment.class, TrafficFragment.class,
			TrafficFragment.class	};
	private final String[]	titles	= { "Traffic", "Bug", "Setting", "None" };
	private final int[]		images	= { R.drawable.ic_local_phone_black_48dp, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initWidget();
	}

	private void initWidget()
	{
		// TODO Auto-generated method stub
		FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.fragment_content);
		for (int i = 0; i < titles.length; i++)
		{
			TabSpec tabSpec = tabHost.newTabSpec(titles[i]).setIndicator(getTabItemView(i));
			tabHost.addTab(tabSpec, frames[i], null);
			tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.rectangle);
			tabHost.getTabWidget().getChildAt(i).setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
			//		v.setBackgroundColor(android.R.color.holo_blue_bright);
				}});
		}
	}

	private View getTabItemView(int index)
	{
		View view = LayoutInflater.from(this).inflate(R.layout.tab_item_view, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageViewTabImage);
		imageView.setImageResource(images[index]);
		TextView textView = (TextView) view.findViewById(R.id.textViewTabText);
		textView.setText(titles[index]);
		return view;
	}
}
