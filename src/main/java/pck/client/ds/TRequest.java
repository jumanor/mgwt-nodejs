package pck.client.ds;


import pck.client.utis.TfrmCargandoRequest;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

public class TRequest {
	
	
	public static void sendRequestArray(String nombreRPC,String parametros,final Callback<JsArray<JavaScriptObject>,TSMessageError> callback){
		String param=null;
		
		if(parametros != null){
			
			param="data="+parametros;
			
		}
		
		final TfrmCargandoRequest cargando=new TfrmCargandoRequest();
		cargando.Show();
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, nombreRPC);
		builder.setHeader("Content-type", "application/x-www-form-urlencoded");
		
		try {
		      Request request = builder.sendRequest(param, new RequestCallback() {
		        
				@Override
				public void onResponseReceived(Request request, Response response) {
					// TODO Auto-generated method stub
					 cargando.Hide();
					 if (200 == response.getStatusCode()) {
				           
						 	TSMessageArray resultado=JsonUtils.<TSMessageArray>safeEval(response.getText());
						 	
						 	if(resultado.getStatus() == 1){
						 	
						 		//JsArray<TSClienteA> data=resultado.getData().cast();
							 	callback.onSuccess(resultado.getData());	
						 	}
						 	if(resultado.getStatus() == 0){
						 	
						 		callback.onFailure(resultado.getMessage());
						 	}
						 	
						 	
				          } 
					 else {
				            
				        	  //Window.alert("<3> codigo:"+response.getStatusCode()+" mensaje:"+response.getStatusText());
				        	  Window.alert("No hay comunicación con el Servidor !");
				          }
				}
				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					cargando.Hide();
					Window.alert("<1>Couldn't retrieve JSON "+exception.getMessage());
				}
		      });
		    } catch (RequestException e) {
		    	cargando.Hide();
		    	Window.alert("<2>Couldn't retrieve JSON "+e.getMessage());
		    }
	}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void sendRequestObject(String nombreRPC,String parametros,final Callback<JavaScriptObject,TSMessageError> callback){
		sendRequestObject(nombreRPC,parametros,callback,true);
	}
	public static void sendRequestObject(String nombreRPC,String parametros,final Callback<JavaScriptObject,TSMessageError> callback,final boolean showCargando){
		String param=null;
		
		if(parametros != null){
			
			param="data="+parametros;
			
		}
		final TfrmCargandoRequest cargando=new TfrmCargandoRequest();
		if(showCargando){
			cargando.Show();
		}
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, nombreRPC);
		builder.setHeader("Content-type", "application/x-www-form-urlencoded");
		
		try {
		      Request request = builder.sendRequest(param, new RequestCallback() {
		        
				@Override
				public void onResponseReceived(Request request, Response response) {
					// TODO Auto-generated method stub
					if(showCargando)
						cargando.Hide();
					
					 if (200 == response.getStatusCode()) {
				           
						 	TSMessageObject resultado=JsonUtils.<TSMessageObject>safeEval(response.getText());
						 	
						 	if(resultado.getStatus() == 1){
						 	
						 		//JsArray<TSClienteA> data=resultado.getData().cast();
							 	callback.onSuccess(resultado.getData());	
						 	}
						 	if(resultado.getStatus() == 0){
						 	
						 		callback.onFailure(resultado.getMessage());
						 	}
						 	
						 	
				          } 
					 else {
				            
						 		//Window.alert("<3> codigo:"+response.getStatusCode()+" mensaje:"+response.getStatusText());
						 		Window.alert("No hay comunicación con el Servidor !");
				          }
				}
				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					if(showCargando)
						cargando.Hide();
					Window.alert("<1>Couldn't retrieve JSON "+exception.getMessage());
				}
		      });
		    } catch (RequestException e) {
		      //displayError("Couldn't retrieve JSON");
		    	if(showCargando)
		    		cargando.Hide();
		    	Window.alert("<2>Couldn't retrieve JSON "+e.getMessage());
		    }
	}

	public static void sendRequestInteger(String nombreRPC,String parametros,final Callback<Integer,TSMessageError> callback){
		
		String param=null;
		
		if(parametros != null){
			
			param="data="+parametros;
			
		}
		final TfrmCargandoRequest cargando=new TfrmCargandoRequest();
		cargando.Show();
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, nombreRPC);
		builder.setHeader("Content-type", "application/x-www-form-urlencoded");
		
		try {
		      Request request = builder.sendRequest(param, new RequestCallback() {
		        
				@Override
				public void onResponseReceived(Request request, Response response) {
					// TODO Auto-generated method stub
					cargando.Hide();
					 if (200 == response.getStatusCode()) {
				           
						 	TSMessageInteger resultado=JsonUtils.<TSMessageInteger>safeEval(response.getText());
						 	
						 	if(resultado.getStatus() == 1){
						 	
							 	callback.onSuccess(resultado.getData());	
						 	}
						 	if(resultado.getStatus() == 0){
						 	
						 		callback.onFailure(resultado.getMessage());
						 	}
						 	
						 	
				          } 
					 else {
						 		Window.alert("No hay comunicación con el Servidor !");
						 		//Window.alert("<3> codigo:"+response.getStatusCode()+" mensaje:"+response.getStatusText());
				          }
				}
				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					cargando.Hide();
					Window.alert("<1>Couldn't retrieve JSON "+exception.getMessage());
				}
		      });
		    } catch (RequestException e) {
		      //displayError("Couldn't retrieve JSON");
		    	cargando.Hide();
		    	Window.alert("<2>Couldn't retrieve JSON "+e.getMessage());
		    }
	}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
