package pck.client.ds;

import com.google.gwt.core.client.JavaScriptObject;

public class TSMessageInteger extends JavaScriptObject {

	protected TSMessageInteger(){}
	
	public final native int getStatus() /*-{ return this.status; }-*/;
	public final native int getData() /*-{ return this.data; }-*/;
	public final native TSMessageError getMessage() /*-{ return this.message; }-*/;
}

