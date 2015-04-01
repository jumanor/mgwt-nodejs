package pck.client.ds;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class TSMessageArray extends JavaScriptObject {

	protected TSMessageArray(){}
	
	public final native int getStatus() /*-{ return this.status; }-*/;
	public final native JsArray<JavaScriptObject> getData() /*-{ return this.data; }-*/;
	public final native TSMessageError getMessage() /*-{ return this.message; }-*/;
}

