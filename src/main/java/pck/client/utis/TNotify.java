package pck.client.utis;

public class TNotify {

	public static native int Info(String title,String texto)/*-{
			
		    new $wnd.PNotify({
    			title: title,
		    	text: texto,
		    	type: 'info',
		    	delay: 2000
		    });

	}-*/;////////////////////////////////////////////////////////////////
	
	public static native int Success(String title,String texto)/*-{
		new $wnd.PNotify({
			title: title,
			text: texto,
			type: 'success',
			delay: 2000
		});
    
	}-*/;////////////////////////////////////////////////////////////////
	public static native int Success(String title,String texto,int delay)/*-{
	new $wnd.PNotify({
		title: title,
		text: texto,
		type: 'success',
		delay: delay
	});
	}-*/;////////////////////////////////////////////////////////////////

	public static native int Error(String title,String texto)/*-{
	new $wnd.PNotify({
		title: title,
		text: texto,
		type: 'error',
		delay: 2000
	});

	}-*/;////////////////////////////////////////////////////////////////
	public static native int Error(String title,String texto,int delay)/*-{
	new $wnd.PNotify({
		title: title,
		text: texto,
		type: 'error',
		delay: delay
	});

	}-*/;////////////////////////////////////////////////////////////////
}



