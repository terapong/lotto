package toto.lotto.jsf.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
//import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import toto.lotto.ejb.entity.Role;
import toto.lotto.ejb.session.LottoSession;


@Named("rolebean")
@SessionScoped
//@ApplicationScoped
public class RoleBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Calendar cal;
	private Role selectedRow;
	private List<Role> roles;
	
	@Inject
	private IndexBean indexbean;
	
	@Inject
	private LottoSession session;
	
	@PostConstruct
	private void init() {
		cal = Calendar.getInstance();
		roles = session.getAllRole();
	}
	
	@PreDestroy
	private void destroy() {
	}
	
	public void btnNewClick() {
		selectedRow = new Role();
		selectedRow.setCreateDate(cal.getTime());
		selectedRow.setUpdateDate(cal.getTime());
		selectedRow.setCreateUser(indexbean.getUser());
	}
	
	public void btnEditClick(Role r) {
		selectedRow = r;
		selectedRow.setUpdateDate(cal.getTime());
	}
	
	public void btnDeleteClick(Role r) {
		selectedRow = r;
	}
	
	public void btnSaveClick() {
		session.updateRole(selectedRow);
		init();
	}
	
	public void confirmDeleteClick() {
		try {
			session.deleteRole(selectedRow);
			init();
		} catch(Exception ex) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary("ไม่สามารถ ลบ ได้");
			msg.setDetail("ไม่สามารถ ลบ ได้ เพราะมี ข้อมูลที่เกี่ยวข้อง");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public Role getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(Role selectedRow) {
		this.selectedRow = selectedRow;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
