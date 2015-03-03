package dev.jugo.mobilemanager;

import dev.jugo.trafficmodule.TrafficModule;
import dev.jugo.trafficmodule.PhoneEvent;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
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
				switch (msg.what)
				{
				case PhoneEvent.EVENT_DATA_ACTIVITY:
					onDataActivity(msg.arg1);
					break;
				case PhoneEvent.EVENT_SERVICE_STATE_CHANGED:
					onServiceStateChanged(msg.arg1);
					break;
				case PhoneEvent.EVENT_CALL_STATE_CHANGED:
					onCallStateChanged(msg.arg1);
					break;
				}
			}
		};
		mTraffic = new TrafficModule(mainActivity, mHandler);
	}

	private void onDataActivity(int nEvent)
	{
		switch (nEvent)
		{
		case TelephonyManager.DATA_ACTIVITY_NONE:
			Logs.showTrace("onDataActivity:DATA_ACTIVITY_NONE");
			break;
		case TelephonyManager.DATA_ACTIVITY_IN:
			Logs.showTrace("onDataActivity:DATA_ACTIVITY_IN");
			break;
		case TelephonyManager.DATA_ACTIVITY_OUT:
			Logs.showTrace("onDataActivity:DATA_ACTIVITY_OUT");
			break;
		case TelephonyManager.DATA_ACTIVITY_INOUT:
			Logs.showTrace("onDataActivity:DATA_ACTIVITY_INOUT");
			break;
		case TelephonyManager.DATA_ACTIVITY_DORMANT:
			Logs.showTrace("onDataActivity:DATA_ACTIVITY_DORMANT");
			break;
		default:
			Logs.showTrace("onDataActivity:UNKNOW: " + nEvent);
			break;
		}
	}

	private void onServiceStateChanged(int nEvent)
	{
		switch (nEvent)
		{
		case ServiceState.STATE_IN_SERVICE:
			Logs.showTrace("onServiceStateChanged: STATE_IN_SERVICE");
			break;
		case ServiceState.STATE_OUT_OF_SERVICE:
			Logs.showTrace("onServiceStateChanged: STATE_OUT_OF_SERVICE");
			break;
		case ServiceState.STATE_EMERGENCY_ONLY:
			Logs.showTrace("onServiceStateChanged: STATE_EMERGENCY_ONLY");
			break;
		case ServiceState.STATE_POWER_OFF:
			Logs.showTrace("onServiceStateChanged: STATE_POWER_OFF");
			break;
		}
	}

	private void onCallStateChanged(int nEvent)
	{
		switch (nEvent)
		{
		case TelephonyManager.CALL_STATE_IDLE:
			Logs.showTrace( "onCallStateChanged: CALL_STATE_IDLE");
			break;
		case TelephonyManager.CALL_STATE_RINGING:
			Logs.showTrace( "onCallStateChanged: CALL_STATE_RINGING");
			break;
		case TelephonyManager.CALL_STATE_OFFHOOK:
			Logs.showTrace( "onCallStateChanged: CALL_STATE_OFFHOOK");
			break;
		default:
			Logs.showTrace( "UNKNOWN_STATE: " + nEvent);
			break;
		}
	}
}
