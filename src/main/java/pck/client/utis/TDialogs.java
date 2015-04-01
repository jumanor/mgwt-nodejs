package pck.client.utis;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialog;


public class TDialogs {
	
	
	public interface AlertCallback {
	    /**
	     * called when the ok button is pressed
	     */
	    public void onButtonPressed();
	  }
	
	public static Dialog alert(String title, SafeHtml txt,final boolean autoHide,final AlertCallback callback) {
	    final TAlertDialog alertDialog = new TAlertDialog("","");
	    alertDialog.setTitleText(title);
	    alertDialog.textLabel.setHTML(txt);
	    
	    alertDialog.textLabel.getElement().getStyle().setColor("black");
	    alertDialog.dialogPanel.title.getElement().getStyle().setColor("black");
		alertDialog.dialogPanel.okButton.getElement().getStyle().setHeight(40, Unit.PX);
		
		//alertDialog.popinDialog.
		
	    alertDialog.addTapHandler(new TapHandler() {

	      @Override
	      public void onTap(TapEvent event) {
	        if (callback != null) {
	          
	          if(autoHide)
	        	  alertDialog.popinDialog.hide();
	          
	          callback.onButtonPressed();
	        }

	      }
	    });

	    alertDialog.show();
	    
	    return alertDialog;
	  }
	 
}
