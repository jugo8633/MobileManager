package dev.jugo.trafficmodule;

import android.content.Context;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class TrafficModule
{
	TelephonyManager	tManager;

	public TrafficModule(Context context, Handler handler)
	{
		super();
		tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

		tManager.listen(new CustomPhoneStateListener(context, handler), PhoneStateListener.LISTEN_CALL_STATE
				| PhoneStateListener.LISTEN_CELL_INFO // Requires API 17
				| PhoneStateListener.LISTEN_CELL_LOCATION | PhoneStateListener.LISTEN_DATA_ACTIVITY
				| PhoneStateListener.LISTEN_DATA_CONNECTION_STATE | PhoneStateListener.LISTEN_SERVICE_STATE
				| PhoneStateListener.LISTEN_SIGNAL_STRENGTHS | PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR
				| PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR);
	}

}
