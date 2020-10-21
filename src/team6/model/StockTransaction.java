package team6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "stockTransaction")
public class StockTransaction {
	private int stocktransid;
	private int tradevolume;
	private int stockid;	
	private Stock stock;
	
	@Id @Column(name = "STOCKTRANSID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getStocktransid() {
		return stocktransid;
	}
	public void setStocktransid(int stocktransid) {
		this.stocktransid = stocktransid;
	}
	@Column(name = "tradevolume")
	public int getTradevolume() {
		return tradevolume;
	}
	public void setTradevolume(int tradevolume) {
		this.tradevolume = tradevolume;
	}	
	@Transient
	public int getStockid() {
		return stockid;
	}
	public void setStockid(int stockid) {
		this.stockid = stockid;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCKID")
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
}
