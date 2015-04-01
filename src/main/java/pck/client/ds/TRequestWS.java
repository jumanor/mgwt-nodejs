package pck.client.ds;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;

public class TRequestWS {
	
	public interface RespuestaWS{
		
		void onRespuesta(JavaScriptObject respuesta);
	}

	public static void sendRequestObject(String nombreServicio,String parametros,final Callback<JavaScriptObject, TSMessageError> callback){
		newPedidoPhone_Socket(nombreServicio,parametros,new RespuestaWS() {
			
			@Override
			public void onRespuesta(JavaScriptObject respuesta) {
				// TODO Auto-generated method stub
				//Window.alert(respuesta);
				//TSMessageObject resultado=JsonUtils.<TSMessageObject>safeEval(respuesta);
				TSMessageObject resultado=respuesta.cast();
				
				if(resultado.getStatus() == 1){
				 	
				 	callback.onSuccess(resultado.getData());	
			 	}
			 	if(resultado.getStatus() == 0){
			 	
			 		callback.onFailure(resultado.getMessage());
			 	}
			}
		});
		//ScriptInjector
	}
	public static native int newPedidoPhone_Socket(String nombreServicio,String parametros,RespuestaWS respuestaWs)/*-{
	
		var param=JSON.parse(parametros);
		$wnd.socket.emit(nombreServicio,param,function(respuesta){
	  		console.log(JSON.stringify(respuesta));
			respuestaWs.@pck.client.ds.TRequestWS.RespuestaWS::onRespuesta(*)(respuesta);
		});
	
	}-*/;///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
