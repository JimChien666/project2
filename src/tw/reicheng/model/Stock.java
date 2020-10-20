package tw.reicheng.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "stock")
public class Stock {
	private int stockid;
	private String stockname;
	private String stockcode;
	private Set<StockTransaction> stockTransactions= new HashSet<StockTransaction>(0);
	
	@Id @Column(name ="STOCKID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getStockid() {
		return stockid;
	}
	public void setStockid(int stockid) {
		this.stockid = stockid;
	}
	@Column(name ="STOCKNAME")
	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	@Column(name ="STOCKCODE")
	public String getStockcode() {
		return stockcode;
	}
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "stock",cascade = CascadeType.ALL)
	public Set<StockTransaction> getStockTransactions() {
		return stockTransactions;
	}
	public void setStockTransactions(Set<StockTransaction> stockTransactions) {
		this.stockTransactions = stockTransactions;
	}
	
	
	
}
