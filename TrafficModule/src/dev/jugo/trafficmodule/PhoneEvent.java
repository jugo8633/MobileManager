package dev.jugo.trafficmodule;

public abstract class PhoneEvent
{
	public final static int	EVENT_DATA_ACTIVITY			= 0;
	public final static int	EVENT_SERVICE_STATE_CHANGED	= 1;
	public final static int	EVENT_CALL_STATE_CHANGED	= 2;

	public final static int	DATA_ACTIVITY_NONE			= 0;
	public final static int	DATA_ACTIVITY_IN			= 1;
	public final static int	DATA_ACTIVITY_OUT			= 2;
	public final static int	DATA_ACTIVITY_INOUT			= 3;
	public final static int	DATA_ACTIVITY_DORMANT		= 4;

	public final static int	STATE_IN_SERVICE			= 0;
	public final static int	STATE_OUT_OF_SERVICE		= 1;
	public final static int	STATE_EMERGENCY_ONLY		= 2;
	public final static int	STATE_POWER_OFF				= 3;

	public final static int	CALL_STATE_IDLE				= 0;
	public final static int	CALL_STATE_RINGING			= 1;
	public final static int	CALL_STATE_OFFHOOK			= 2;
}
