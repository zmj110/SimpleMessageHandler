package org.ooopen.base;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class CustomMsg
{
	public Callback cb;
	public Object obj;
	
	public CustomMsg(Callback cb, Object obj) {
		super();
		this.cb = cb;
		this.obj = obj;
	}
}

public class SimpleMessageHandler {

	private Context context;

	private Handler handler;

	public SimpleMessageHandler(Context context) {
		super();
		this.context = context;
		handler = new Handler(new Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				CustomMsg customMsg = (CustomMsg) msg.obj;
				msg.obj = customMsg.obj;
				customMsg.cb.handleMessage(msg);
				customMsg.cb = null;
				customMsg.obj = null;
				customMsg=null;
				return false;
			}
		});
	}

	public boolean sendMessage(Callback callback, int what, Object obj)
	{
		handler.dispatchMessage(handler.obtainMessage(what, new CustomMsg(callback, obj)));
		return true;
	}
}
