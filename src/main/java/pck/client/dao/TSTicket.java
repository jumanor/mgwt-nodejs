package pck.client.dao;

import com.google.gwt.core.client.JavaScriptObject;

public class TSTicket extends JavaScriptObject{
	
	protected TSTicket(){};
	
	//public final native int getId() /*-{ return this.id; }-*/;
	//public final native String getNombres() /*-{ return this.nombres; }-*/;
	//public final native String getApPaterno() /*-{ return this.apPaterno; }-*/;
	public final native String getOperacion() /*-{ return this.operacion; }-*/;	
	
}
