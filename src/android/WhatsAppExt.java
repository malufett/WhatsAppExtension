package com.whatsapp.ext.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

public class WhatsAppExt extends CordovaPlugin {
    private enum Command {
		SEND, READ;
	};
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		JSONObject retval = new JSONObject();
		Command cmd = Command.valueOf(action);
    	switch(cmd){
    		case SEND:
    			retval.put("code", StatusCode.SUCCESS_MSG_SENT);
				break;
			case READ:
    			retval.put("code", StatusCode.SUCCESS_MSG_READ);
				break;
			default:		
				status = Status.ERROR;
				retval.put("message", "Invalid command! Please try again.");
				retval.put("code", StatusCode.INVALID_CMD);
		   		callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, retval));
		        return false;
    	}        
   		callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, retval));
        return true;
    }
        
    private Activity getActivity() {
        return this.cordova.getActivity();
    }
    	
	private ContentResolver getContentResolver(){
	    return getActivity().getContentResolver();
	}
}

class StatusCode{
	public static final byte INVALID_CMD = 0x0;
	public static final byte SUCCESS_MSG_SENT = 0x1;
	public static final byte SUCCESS_MSG_READ = 0x2;
	public static final byte FAILED_MSG_SENT = 0x3;
	public static final byte FAILED_MSG_READ = 0x4;
}