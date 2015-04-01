package pck.client.ds;

import com.google.gwt.core.client.JavaScriptObject;

public class TSMessageObject extends JavaScriptObject {

	protected TSMessageObject(){}
	
	public final native int getStatus() /*-{ return this.status; }-*/;
	public final native JavaScriptObject getData() /*-{ return this.data; }-*/;
	public final native TSMessageError getMessage() /*-{ return this.message; }-*/;
}

