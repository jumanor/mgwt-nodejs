package pck.client.view.servicio;

import java.util.ArrayList;
import java.util.List;

import pck.client.presenter.servicio.TServicioPresenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FixedSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public class TServicioView implements TServicioPresenter.Display{
	
	public class Item{
		String ticketCliente;
		String ticketServicio;
		String direccionOrigen;
		String fecha;
		
		
		public Item(String ticketCliente,String ticketServicio,String direccionOrigen,String fecha){
			this.ticketCliente=ticketCliente;
			this.ticketServicio=ticketServicio;
			this.direccionOrigen=direccionOrigen;
			this.fecha=fecha;
		}
	}
	private CellList<Item> cellListWithHeader;
	private HTML backButton=new HTML("<i class='small mdi-hardware-keyboard-arrow-left'></i>");
	private RootFlexPanel rootFlexPanel=new RootFlexPanel();
	private TContenido ctn=new TContenido();
	public TServicioView(){
		
		 	HeaderPanel headerPanel = new HeaderPanel();
		 
	       HeaderTitle title = new HeaderTitle();
	       title.setText("SERVICIOS");
	       
	       //Image img=new Image(ImageHolder.get().previousItem());
	       headerPanel.add(backButton);
	       headerPanel.add(new FlexSpacer());
	       headerPanel.add(title);
	       headerPanel.add(new FlexSpacer());
	       headerPanel.add(new FixedSpacer(40));
	       
	       cellListWithHeader=new CellList<Item>(new TBasicCell<Item>() {

			
			@Override
			public boolean canBeSelected(Item model) {
				return true;
			}
	       
	       });
	       List<Item> item=new ArrayList<Item>();
	       item.add(new Item("TK100","SRV100","Coop Albara","12/23/1020"));
	       item.add(new Item("TK100","SRV100","Coop Albara","12/23/1020"));
	       item.add(new Item("TK100","SRV100","Coop Albara","12/23/1020"));
	       
	       cellListWithHeader.render(item);
	       cellListWithHeader.addCellSelectedHandler(new CellSelectedHandler() {
			
			@Override
			public void onCellSelected(CellSelectedEvent event) {
				// TODO Auto-generated method stub
				//Window.alert(event.getIndex()+"");
			}
	       });
	       
	       rootFlexPanel.add(headerPanel);
	       
	       ScrollPanel sp=new ScrollPanel();
	       sp.add(cellListWithHeader);
	       
	       rootFlexPanel.add(sp);
	}
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return rootFlexPanel;
	}
	@Override
	public void onAnimationEnd() {
		// TODO Auto-generated method stub
		//ctn.Inicializar();
		//Window.alert(History.getToken());
	}
	@Override
	public HasClickHandlers getAddBackMapa() {
		// TODO Auto-generated method stub
		return backButton;
	}
	
}
