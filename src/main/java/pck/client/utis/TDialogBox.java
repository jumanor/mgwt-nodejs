package pck.client.utis;

import com.google.gwt.core.client.Callback;


public class TDialogBox {
	
	//private static PopupPanel pop=new PopupPanel();
	
	public static native void alert(String texto)/*-{
			
			$wnd.bootbox.alert(texto);	
	
	
	}-*/;////////////////////////////////////////////////////////////////
	
	public static native void confirm(String texto,Callback<Void,Void> callback)/*-{
		
				$wnd.bootbox.confirm(texto, function(result) {
						
						if(result==true)
							callback.@com.google.gwt.core.client.Callback::onSuccess(*)(null);
						else
							callback.@com.google.gwt.core.client.Callback::onFailure(*)(null);

				}); 
				 
	}-*/;////////////////////////////////////////////////////////////////

}
