package com.byemisys.cordova;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import android.R;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import java.io.IOException;
import android.util.Log;
import android.os.Bundle;
 
public class FamocoLaser extends CordovaPlugin {
 
  private static final int REQUEST_CODE = 1;
  private CallbackContext callback = null;
  private final String LOG_TAG = "FamocoLaser";
  public CordovaInterface cordova           = null;
  public CallbackContext tryConnectCallback = null;

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		this.cordova = cordova;
	}
	
  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if("scan".equals(action)){
      try {			
	  
			callback = callbackContext;
			this.cordova.setActivityResultCallback(this);
			
			Intent intent = new Intent("com.famoco.intent.action.SCAN_BARCODE");
			this.cordova.startActivityForResult(this, intent, REQUEST_CODE);
			return true;
		} catch (Exception e) {
			Log.e(LOG_TAG, e.getMessage(), e);
			callbackContext.error(e.getMessage());
		}
    } else {
      callbackContext.error("FamocoLaserPlugin."+action+" not found !");
      return false;
    }
	return true;
  }

	
	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (REQUEST_CODE == requestCode && data!=null) {
			PluginResult result = new PluginResult(PluginResult.Status.OK, data.getStringExtra("barcode"));
			result.setKeepCallback(true);
			callback.sendPluginResult(result);
		}
		else
		{
			PluginResult result = new PluginResult(PluginResult.Status.ERROR, "no params returned successfully" );
			result.setKeepCallback(true);
			callback.sendPluginResult(result);
		}
	}
	
	public Bundle onSaveInstanceState() 
	{
		Bundle state = new Bundle();
		return state;
	}

	public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext) 
	{
		this.callback = callbackContext;
	}

}