package pck.client.ds;

import com.google.gwt.core.client.JavaScriptObject;

public class TSMessageError extends JavaScriptObject {

	protected TSMessageError(){}
	
	public final native int getCodigo() /*-{ return this.codigo; }-*/;
	public final native String getMessage() /*-{ return this.message; }-*/;
}

