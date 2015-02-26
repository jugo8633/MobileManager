package dev.jugo.mobilemanager;

import dev.jugo.trafficmodule.TrafficModule;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TrafficFragment extends Fragment
{

	private MainActivity	mainActivity	= null;
	private TrafficModule	mTraffic		= null;
	private Handler			mHandler		= null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		Log.d("=====>", "TrafficFragment onCreateView");
		return inflater.inflate(R.layout.traffic, container, false);
	}

	@Override
	public void onAttach(Activity activity)
	{
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.d("=====>", "TrafficFragment onAttach");
		mainActivity = (MainActivity) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.d("=====>", "TrafficFragment onActivityCreated");
		/** Start our traffic tracking **/
		mHandler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				// TODO Auto-generated method stub
				
			}
		};
		mTraffic = new TrafficModule(mainActivity, mHandler);
	}
}
