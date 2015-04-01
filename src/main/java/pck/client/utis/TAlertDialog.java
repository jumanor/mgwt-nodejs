package pck.client.utis;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialog;
import com.googlecode.mgwt.ui.client.widget.dialog.HasTitleText;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.PopinDialogOverlay;
import com.googlecode.mgwt.ui.client.widget.dialog.panel.DialogPanel;
import com.googlecode.mgwt.ui.client.widget.dialog.panel.DialogPanelAppearance;

public class TAlertDialog implements HasText, HasTitleText, HasTapHandlers, Dialog, HasHTML {

	  public HTML textLabel;
	  public PopinDialogOverlay popinDialog;
	  public DialogPanel dialogPanel;

	  /**
	   * Construct an alert dialog
	   *
	   * @param title - the title of the dialog
	   * @param text - the text of the dialog
	   */
	  public TAlertDialog(String title, String text) {
	    this(DialogPanel.DEFAULT_APPEARANCE, title, text);
	  }

	  /**
	   * Construct an alert dialog
	   *
	   * @param css - the css to use
	   * @param title - the title of the dialog
	   * @param text - the text of the dialog
	   */
	  public TAlertDialog(DialogPanelAppearance appearance, String title, String text) {
	    this(appearance, title, text, "Ok");
	  }

	  /**
	   * Construct an alert dialog
	   *
	   * @param css - the css to use
	   * @param title - the title of the dialog
	   * @param text - the text of the dialog
	   * @param okButtonText the text of the button of the dialog
	   */
	  public TAlertDialog(DialogPanelAppearance appearance, String title, String text, String okButtonText) {
	    popinDialog = new PopinDialogOverlay(appearance);
	    dialogPanel = new DialogPanel(appearance);
	    dialogPanel.showCancelButton(false);
	    dialogPanel.showOkButton(true);

	    textLabel = new HTML();
	    dialogPanel.getContent().add(textLabel);
	    popinDialog.add(dialogPanel);

	    dialogPanel.getOkButton().addTapHandler(new TapHandler() {

	      @Override
	      public void onTap(TapEvent event) {
	        //popinDialog.hide();
	      }
	    });

	    setText(text);
	    setTitleText(title);
	    dialogPanel.setOkButtonText(okButtonText);
	  }

	  @Override
	  public String getText() {
	    return textLabel.getText();
	  }

	  @Override
	  public void setText(String text) {
	    textLabel.setText(text);
	  }

	  @Override
	  public void setTitleText(String text) {
	    dialogPanel.getDialogTitle().setText(text);

	  }

	  @Override
	  public String getTitleText() {
	    return dialogPanel.getDialogTitle().getText();
	  }

	  public void show() {
	    popinDialog.center();
	  }

	  @Override
	  public HandlerRegistration addTapHandler(TapHandler handler) {
	    return dialogPanel.getOkButton().addTapHandler(handler);
	  }

	  @Override
	  public String getHTML() {
	    return textLabel.getHTML();
	  }

	  @Override
	  public void setHTML(String html) {
	    textLabel.setHTML(html);
	  }

	  @Override
	  public void hide() {
	    popinDialog.hide();
	  }

}
