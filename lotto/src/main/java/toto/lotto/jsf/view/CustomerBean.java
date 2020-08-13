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

import toto.lotto.ejb.entity.Customer;
import toto.lotto.ejb.entity.Role;
import toto.lotto.ejb.session.LottoSession;
import toto.util.FWUtil;


@Named("customerbean")
@SessionScoped
//@ApplicationScoped
public class CustomerBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Calendar cal;
	private Role selectedMasterRow;
	private List<Role> roles;
	private Long selectedMasterId;
	
	private Customer selectedSlaveRow;
	private List<Customer> customers;
	
	private String tempPass;
	
	@Inject
	private IndexBean indexbean;
	
	@Inject
	private LottoSession session;
	
	@PostConstruct
	private void init() {
		cal = Calendar.getInstance();
		roles = session.getAllRole();
		selectedMasterRow = roles.get(0);
		customers = session.getCustomerByRole(selectedMasterRow);
	}
	
	@PreDestroy
	private void destroy() {
	}
	
	public void btnNewClick() {
		selectedSlaveRow = new Customer();
		selectedSlaveRow.setRole(selectedMasterRow);
		selectedSlaveRow.setCreateDate(cal.getTime());
		selectedSlaveRow.setUpdateDate(cal.getTime());
		selectedSlaveRow.setCreateUser(indexbean.getUser());
	}
	
	public void btnEditClick(Customer r) {
		selectedSlaveRow = r;
		selectedSlaveRow.setUpdateDate(cal.getTime());
	}
	
	public void btnDeleteClick(Customer r) {
		selectedSlaveRow = r;
	}
	
	public void btnSaveClick() {
		try {
			tempPass = FWUtil.byteArrayToHexString(FWUtil.computeHash(selectedSlaveRow.getPassword()));
			selectedSlaveRow.setPassword(tempPass);
			session.updateCustomer(selectedSlaveRow);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		init();
	}
	
	public void confirmDeleteClick() {
		try {
			session.deleteCustomer(selectedSlaveRow);
			init();
		} catch(Exception ex) {
			FacesMessage msg = new FacesMessage();
			msg.setSummary("ไม่สามารถ ลบ ได้");
			msg.setDetail("ไม่สามารถ ลบ ได้ เพราะมี ข้อมูลที่เกี่ยวข้อง");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void selMasterChange() {
		selectedMasterRow = session.getRoleById(selectedMasterId);
		customers = session.getCustomerByRole(selectedMasterRow);
	}

	public Role getSelectedMasterRow() {
		return selectedMasterRow;
	}

	public void setSelectedMasterRow(Role selectedMasterRow) {
		this.selectedMasterRow = selectedMasterRow;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Customer getSelectedSlaveRow() {
		return selectedSlaveRow;
	}

	public void setSelectedSlaveRow(Customer selectedSlaveRow) {
		this.selectedSlaveRow = selectedSlaveRow;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Long getSelectedMasterId() {
		return selectedMasterId;
	}

	public void setSelectedMasterId(Long selectedMasterId) {
		this.selectedMasterId = selectedMasterId;
	}
}
