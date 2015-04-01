package pck.client;


import pck.client.utis.TGlobal;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;


public class Principal implements EntryPoint{

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
			MGWT.applySettings(MGWTSettings.getAppSetting());
	       
	       final AnimationHelper animationHelper = new AnimationHelper();
	       
	       HandlerManager eventBus = new HandlerManager(null);
	       final AppController appViewer = new AppController(eventBus);
	       
	       
	       injectScriptNode(new Callback<Void, Void>() {
				
				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
				    //Window.alert("Server ok");
					GWT.log("WebSocket UP");
					appViewer.go(animationHelper,Animations.SLIDE,RootPanel.get());
				}
				
				@Override
				public void onFailure(Void reason) {
					// TODO Auto-generated method stub
					GWT.log("WebSocket CAIDO!!!!");
					Window.alert("No se pudo conectar con el Servidor !!!!");
				}
			});
	       
	}
	public native int connectSocketIO(String path)/*-{
	
	//$wnd.socket = io.connect('http://localhost:9093');
	$wnd.socket = io.connect(path);
	
	$wnd.socket.on('connect_error',function(data){
	
		alert("Se perdio conexion con el Servidor");
	});
	
	}-*/;///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void injectScriptNode(final Callback<Void,Void> callback){
		
		final String path=TGlobal.getRutaServerWebSocket();
		
		ScriptInjector.fromUrl(path+"/socket.io/socket.io.js").setCallback(new Callback<Void, Exception>() {
			@Override
			public void onSuccess(Void result) {
			// TODO Auto-generated method stub
				connectSocketIO(path);
				callback.onSuccess(null);
			}
			@Override
			public void onFailure(Exception reason) {
			// TODO Auto-generated method stub

				callback.onFailure(null);
			}
		}).inject();
			
	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
