package toto.lotto.jsf.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;


@Named("menubean")
//@SessionScoped
@ApplicationScoped
public class MenuBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IndexBean indexbean;
	
	@PostConstruct
	private void init() {
	}
	
	@PreDestroy
	private void destroy() {
	}
	
	public String handleClose(CloseEvent event) {
		indexbean.setContentCenter("blank.xhtml");
		return "main?facesRedirect=true";
	}
	
	public void addRoleClick() {
		indexbean.setContentCenter("role.xhtml");
	}
	
	public void addCustomClick() {
		indexbean.setContentCenter("customer.xhtml");
	}
	
	public void changPriceClick() {
		indexbean.setContentCenter("price.xhtml");
	}
	
	public void drawlClick() {
		indexbean.setContentCenter("draw.xhtml");
	}
}
