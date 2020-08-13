package toto.lotto.jsf.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
//import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.Calendar;
import java.util.ResourceBundle;

import toto.lotto.ejb.entity.*;
import toto.lotto.ejb.session.LottoSession;
import toto.util.FWUtil;

@Named("indexbean")
@SessionScoped
//@ApplicationScoped
public class IndexBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final ResourceBundle resources = ResourceBundle.getBundle("resources.Index");
	
	private Calendar cal;
	private String userName;
	private String password;
	private Customer user;
	
	private String contentCenter = "blank.xhtml";
	
	@Inject
	private LottoSession session;
	
	@PostConstruct
	private void init() {
		cal = Calendar.getInstance();
	}
	
	@PreDestroy
	private void destroy() {
	}
	
	public String loginClick() {
		try {
			userName = "admin";
			user = session.getCustomerByName(userName);
			if(user != null) {
				password = FWUtil.byteArrayToHexString(FWUtil.computeHash(password));
				if(password.equals(user.getPassword())) {
					user.setUpdateDate(cal.getTime());
					session.updateCustomer(user);
					contentCenter = "blank.xhtml";
					return "main.jsf";
				} else {
					FacesMessage msg = new FacesMessage();
					msg.setSummary(resources.getString("LoginFail.Summary"));
					msg.setDetail(resources.getString("LoginFail.Description"));
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);	
				}
			} else {
				FacesMessage msg = new FacesMessage();
				msg.setSummary(resources.getString("LoginFail.Summary"));
				msg.setDetail(resources.getString("LoginFail.Description"));
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch(Exception ex) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary(resources.getString("LoginFail.Summary"));
			msg.setDetail(resources.getString("LoginFail.Description"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			ex.printStackTrace();
		}
		return "null";
	}
	
	public String logout() {
		//employee = vasessionbean.getEmployee();
		FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed!"));
        } 
        context.getExternalContext().invalidateSession();
        return "index?facesRedirect=true";
    }
	
	public void RegisterClick() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	public String getContentCenter() {
		return contentCenter;
	}

	public void setContentCenter(String contentCenter) {
		this.contentCenter = contentCenter;
	}
}