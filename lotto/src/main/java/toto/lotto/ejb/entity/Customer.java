package toto.lotto.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, name="username", length = 50)
    private String username;
    
    @Column(nullable = false, name="password", length = 50)
    private String password;
    
    @Column(nullable = false, name="usernameLotto", length = 50)
    private String username_lotto;
    
    @Column(nullable = false, name="password_lotto", length = 50)
    private String passwordLotto;
    
    @Column(nullable = false, name="name", length = 50)
    private String name;
    
    @Column(nullable = false, name="surname", length = 50)
    private String surname;
    
    @Column(nullable = false, name="idcard", length = 50)
    private String idCard;
    
    @Column(nullable = false, name="isadmin", length = 1)
    private boolean admin;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", length=19)
    private Date createDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", length = 10)
    private Date updateDate;
    
    @ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
    
    @OneToOne
    private Customer createUser;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	private List<BankAccount> bankAccounts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername_lotto() {
		return username_lotto;
	}

	public void setUsername_lotto(String username_lotto) {
		this.username_lotto = username_lotto;
	}

	public String getPasswordLotto() {
		return passwordLotto;
	}

	public void setPasswordLotto(String passwordLotto) {
		this.passwordLotto = passwordLotto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Customer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Customer createUser) {
		this.createUser = createUser;
	}
}
