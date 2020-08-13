package toto.lotto.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="booking")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, name="count", length = 2)
    private int count;
    
    @Column(nullable = false, name="totalprice", precision = 10, scale = 2, length = 14)
    private BigDecimal totalprice = BigDecimal.valueOf(0);
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", length=19)
    private Date createDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", length=19)
    private Date updateDate;
    
    @ManyToOne
	@JoinColumn(name = "bankaccount_id")
	private BankAccount bankaccount;
    
    @OneToOne
    private LotteryDraw lotteryDraw;
    
    @OneToOne
    private LotteryPrice lotteryPrice;
    
    @OneToOne
    private Customer createUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public BankAccount getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(BankAccount bankaccount) {
		this.bankaccount = bankaccount;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public LotteryDraw getLotteryDraw() {
		return lotteryDraw;
	}

	public void setLotteryDraw(LotteryDraw lotteryDraw) {
		this.lotteryDraw = lotteryDraw;
	}

	public LotteryPrice getLotteryPrice() {
		return lotteryPrice;
	}

	public void setLotteryPrice(LotteryPrice lotteryPrice) {
		this.lotteryPrice = lotteryPrice;
	}

	public Customer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Customer createUser) {
		this.createUser = createUser;
	}
}
