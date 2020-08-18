package entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Ammeter implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ammeterId;
	private String ammeterDate;
	private String ammeterTime;
	private BigDecimal ammeterAmount;

	public Ammeter() {
	}

	public Ammeter(String ammeterId, String ammeterDate) {
		this.ammeterId = ammeterId;
		this.ammeterDate = ammeterDate;
	}

	public Ammeter(String ammeterId, String ammeterDate, String ammeterTime, BigDecimal ammeterAmount) {
		this.ammeterId = ammeterId;
		this.ammeterDate = ammeterDate;
		this.ammeterTime = ammeterTime;
		this.ammeterAmount = ammeterAmount;
	}

	public String getAmmeterId() {
		return ammeterId;
	}

	public void setAmmeterId(String ammeterId) {
		this.ammeterId = ammeterId;
	}

	public String getAmmeterDate() {
		return ammeterDate;
	}

	public void setAmmeterDate(String ammeterDate) {
		this.ammeterDate = ammeterDate;
	}

	public String getAmmeterTime() {
		return ammeterTime;
	}

	public void setAmmeterTime(String ammeterTime) {
		this.ammeterTime = ammeterTime;
	}

	public BigDecimal getAmmeterAmount() {
		return ammeterAmount;
	}

	public void setAmmeterAmount(BigDecimal ammeterAmount) {
		this.ammeterAmount = ammeterAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ammeterDate == null) ? 0 : ammeterDate.hashCode());
		result = prime * result + ((ammeterId == null) ? 0 : ammeterId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ammeter other = (Ammeter) obj;
		if (ammeterDate == null) {
			if (other.ammeterDate != null)
				return false;
		} else if (!ammeterDate.equals(other.ammeterDate))
			return false;
		if (ammeterId == null) {
			if (other.ammeterId != null)
				return false;
		} else if (!ammeterId.equals(other.ammeterId))
			return false;
		return true;
	}

}
