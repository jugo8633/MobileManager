package dev.jugo.trafficmodule;

import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

public class CustomPhoneStateListener extends PhoneStateListener
{
	public static String	LOG_TAG			= "CustomPhoneStateListener";
	Context					mContext		= null;
	Handler					mHandler		= null;
	EventHandler			mEventHandler	= null;

	public CustomPhoneStateListener(Context context, Handler handler)
	{
		mContext = context;
		mHandler = handler;
		mEventHandler = new EventHandler(handler);
	}

	@Override
	protected void finalize() throws Throwable
	{
		// TODO Auto-generated method stub
		mEventHandler = null;
		super.finalize();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	@Override
	public void onCellInfoChanged(List<CellInfo> cellInfo)
	{
		// TODO Auto-generated method stub
		super.onCellInfoChanged(cellInfo);
		Log.i(LOG_TAG, "onCellInfoChanged:" + cellInfo);
	}

	@Override
	public void onDataActivity(int direction)
	{
		// TODO Auto-generated method stub
		super.onDataActivity(direction);
		mEventHandler.sendMsg(PhoneEvent.EVENT_DATA_ACTIVITY, direction, 0, null);
		switch (direction)
		{
		case TelephonyManager.DATA_ACTIVITY_NONE:
			Log.i(LOG_TAG, "onDataActivity:DATA_ACTIVITY_NONE");
			break;
		case TelephonyManager.DATA_ACTIVITY_IN:
			Log.i(LOG_TAG, "onDataActivity:DATA_ACTIVITY_IN");
			break;
		case TelephonyManager.DATA_ACTIVITY_OUT:
			Log.i(LOG_TAG, "onDataActivity:DATA_ACTIVITY_OUT");
			break;
		case TelephonyManager.DATA_ACTIVITY_INOUT:
			Log.i(LOG_TAG, "onDataActivity:DATA_ACTIVITY_INOUT");
			break;
		case TelephonyManager.DATA_ACTIVITY_DORMANT:
			Log.i(LOG_TAG, "onDataActivity:DATA_ACTIVITY_DORMANT");
			break;
		default:
			Log.w(LOG_TAG, "onDataActivity:UNKNOW" + direction);
			break;
		}
	}

	@Override
	public void onServiceStateChanged(ServiceState serviceState)
	{
		// TODO Auto-generated method stub
		super.onServiceStateChanged(serviceState);
		Log.i(LOG_TAG, "onServiceStateChanged: " + serviceState.toString());
		Log.i(LOG_TAG, "onServiceStateChanged: getOperatorAlphaLong " + serviceState.getOperatorAlphaLong());
		Log.i(LOG_TAG, "onServiceStateChanged: getOperatorAlphaShort " + serviceState.getOperatorAlphaShort());
		Log.i(LOG_TAG, "onServiceStateChanged: getOperatorNumeric " + serviceState.getOperatorNumeric());
		Log.i(LOG_TAG, "onServiceStateChanged: getIsManualSelection " + serviceState.getIsManualSelection());
		Log.i(LOG_TAG, "onServiceStateChanged: getRoaming " + serviceState.getRoaming());

		mEventHandler.sendMsg(PhoneEvent.EVENT_SERVICE_STATE_CHANGED, serviceState.getState(), 0, null);

		switch (serviceState.getState())
		{
		case ServiceState.STATE_IN_SERVICE:
			Log.i(LOG_TAG, "onServiceStateChanged: STATE_IN_SERVICE");
			break;
		case ServiceState.STATE_OUT_OF_SERVICE:
			Log.i(LOG_TAG, "onServiceStateChanged: STATE_OUT_OF_SERVICE");
			break;
		case ServiceState.STATE_EMERGENCY_ONLY:
			Log.i(LOG_TAG, "onServiceStateChanged: STATE_EMERGENCY_ONLY");
			break;
		case ServiceState.STATE_POWER_OFF:
			Log.i(LOG_TAG, "onServiceStateChanged: STATE_POWER_OFF");
			break;
		}
	}

	@Override
	public void onCallStateChanged(int state, String incomingNumber)
	{
		// TODO Auto-generated method stub
		super.onCallStateChanged(state, incomingNumber);
		mEventHandler.sendMsg(PhoneEvent.EVENT_CALL_STATE_CHANGED, state, 0, null);
		switch (state)
		{
		case TelephonyManager.CALL_STATE_IDLE:
			Log.i(LOG_TAG, "onCallStateChanged: CALL_STATE_IDLE");
			break;
		case TelephonyManager.CALL_STATE_RINGING:
			Log.i(LOG_TAG, "onCallStateChanged: CALL_STATE_RINGING");
			break;
		case TelephonyManager.CALL_STATE_OFFHOOK:
			Log.i(LOG_TAG, "onCallStateChanged: CALL_STATE_OFFHOOK");
			break;
		default:
			Log.i(LOG_TAG, "UNKNOWN_STATE: " + state);
			break;
		}
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onCellLocationChanged(CellLocation location)
	{
		// TODO Auto-generated method stub
		super.onCellLocationChanged(location);

		if (location instanceof GsmCellLocation)
		{
			GsmCellLocation gcLoc = (GsmCellLocation) location;
			Log.i(LOG_TAG, "onCellLocationChanged: GsmCellLocation " + gcLoc.toString());
			Log.i(LOG_TAG, "onCellLocationChanged: GsmCellLocation getCid " + gcLoc.getCid());
			Log.i(LOG_TAG, "onCellLocationChanged: GsmCellLocation getLac " + gcLoc.getLac());
			Log.i(LOG_TAG, "onCellLocationChanged: GsmCellLocation getPsc" + gcLoc.getPsc()); // Requires min API 9
		}
		else if (location instanceof CdmaCellLocation)
		{
			CdmaCellLocation ccLoc = (CdmaCellLocation) location;
			Log.i(LOG_TAG, "onCellLocationChanged: CdmaCellLocation " + ccLoc.toString());
			Log.i(LOG_TAG, "onCellLocationChanged: CdmaCellLocation getBaseStationId " + ccLoc.getBaseStationId());
			Log.i(LOG_TAG,
					"onCellLocationChanged: CdmaCellLocation getBaseStationLatitude " + ccLoc.getBaseStationLatitude());
			Log.i(LOG_TAG,
					"onCellLocationChanged: CdmaCellLocation getBaseStationLongitude" + ccLoc.getBaseStationLongitude());
			Log.i(LOG_TAG, "onCellLocationChanged: CdmaCellLocation getNetworkId " + ccLoc.getNetworkId());
			Log.i(LOG_TAG, "onCellLocationChanged: CdmaCellLocation getSystemId " + ccLoc.getSystemId());
		}
		else
		{
			Log.i(LOG_TAG, "onCellLocationChanged: " + location.toString());
		}
	}

	@Override
	public void onCallForwardingIndicatorChanged(boolean cfi)
	{
		// TODO Auto-generated method stub
		super.onCallForwardingIndicatorChanged(cfi);
		Log.i(LOG_TAG, "onCallForwardingIndicatorChanged: " + cfi);
	}

	@Override
	public void onMessageWaitingIndicatorChanged(boolean mwi)
	{
		// TODO Auto-generated method stub
		super.onMessageWaitingIndicatorChanged(mwi);
		Log.i(LOG_TAG, "onMessageWaitingIndicatorChanged: " + mwi);
	}

}
