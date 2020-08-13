package toto.lotto.jsf.view;

import java.io.Serializable;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import toto.lotto.ejb.entity.LotteryDraw;
import toto.lotto.ejb.session.LottoSession;

@Named("drawbean")
@SessionScoped
//@ApplicationScoped
public class DrawBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Calendar cal;
	private List<LotteryDraw> draws; 
	
	@Inject
	private IndexBean indexbean;
	
	@Inject
	private LottoSession session;

	@PostConstruct
	private void init() {
		cal = Calendar.getInstance();
		//draws = session.
	}
	
	@PreDestroy
	private void destroy() {
	}
	
	public void jenerateDrawDate(Date start, Date stop) {
		
	}

	public List<LotteryDraw> getDraws() {
		return draws;
	}

	public void setDraws(List<LotteryDraw> draws) {
		this.draws = draws;
	}
}
