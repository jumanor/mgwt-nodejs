package pck.client.view.login;

import static com.google.gwt.query.client.GQuery.$;
import pck.client.presenter.login.TLoginPresenter;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.form.Form;
import com.googlecode.mgwt.ui.client.widget.form.FormEntry;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;


public class TLoginView implements TLoginPresenter.Display{
	
	private RootFlexPanel rootFlexPanel = new RootFlexPanel();
	private Button btn=new Button();
	
	public TLoginView(){
		
		btn.setText("Ingresar");
		btn.setImportant(true);
		btn.setWidth(Window.getClientWidth()-10+"px");
		
		final HeaderPanel headerPanel = new HeaderPanel();
		 
	       HeaderTitle title = new HeaderTitle();
	       title.setText("INGRESO");
	    
	       headerPanel.add(new FlexSpacer());
	       headerPanel.add(title);
	       headerPanel.add(new FlexSpacer());
	       
		ScrollPanel scrollPanel=new ScrollPanel();
		
		FlowPanel container = new FlowPanel();
		container.getElement().setId("login-container");
		
		Form widgetList = new Form();
		
		MTextBox txtUsuario=new MTextBox();
		MPasswordTextBox txtContrasenia=new MPasswordTextBox();
		
		txtUsuario.setText("jumanor");
		txtContrasenia.setText("jumanor");
		widgetList.add(new FormEntry("Usuario", txtUsuario));
		widgetList.add(new FormEntry("Contraseña", txtContrasenia));
		
		
		container.add(headerPanel);
		container.add(widgetList);
		container.add(btn);

		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setWidget(container);
		scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());
		
		rootFlexPanel.add(scrollPanel);
		
		rootFlexPanel.addAttachHandler(new AttachEvent.Handler() {
			
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				// TODO Auto-generated method stub
				// CSS de Materialize.css !!!
				if(event.isAttached()){
					$("#login-container .mgwt-TextBox input").css("border","0 solid red");
					$("#login-container .mgwt-TextBox input").css("box-shadow","0 0 0 0 red");
					$("#login-container .mgwt-TextBox input").css("height","inherit");
					$("#login-container .mgwt-TextBox input").css("font-size","inherit");
					$("#login-container .mgwt-TextBox input").css("margin","0 0 0 0");
				}
			}
		});
		
		
	}////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		return rootFlexPanel;
	}////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public HasTapHandlers getAddLogin() {
		// TODO Auto-generated method stub
		return btn;
	}

	@Override
	public void verMenu() {
		// TODO Auto-generated method stub
		//pr.InicializarSelect();
		
	}
	
}
