package com.whatsapp.ext.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.content.Context;
import android.telephony.TelephonyManager;

public class WhatsAppExt extends CordovaPlugin {

   
    private enum Command {
		SEND, READ, ERROR;
	};
	
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		JSONObject retval = new JSONObject();
		Command cmd = Command.ERROR;
		
    	try{
    		cmd = Command.valueOf(action);
    	}catch(IllegalArgumentException iae){}
    	
    	switch(cmd){
    		case SEND:
    			retval.put("code", StatusCode.SUCCESS_MSG_SENT);
				break;
			case READ:
    			retval.put("code", StatusCode.SUCCESS_MSG_READ);
				break;
			default:		
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