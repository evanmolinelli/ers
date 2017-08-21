package ers.revature.beans;

import java.sql.Timestamp;
import java.sql.Blob;

public class Reimbursement {

	private int reimbId;
	private double reimbAmt;
	private Timestamp reimbSubmitted;
	private Timestamp reimbResolved;
	private String reimbDescr;
	private Blob reimbReciept;
	private Users reimbAuthor;
	private Users reimbResolver;
	private String reimbStatus;
	private String reimbType;
	private String fullName;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int reimbId, double reimbAmt, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbDescr, Blob reimbReciept, Users reimbAuthor, Users reimbResolver, String reimbStatus,
			String reimbType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescr = reimbDescr;
		this.reimbReciept = reimbReciept;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}

	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public double getReimbAmt() {
		return reimbAmt;
	}
	public void setReimbAmt(double reimbAmt) {
		this.reimbAmt = reimbAmt;
	}
	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}
	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}
	public Timestamp getReimbResolved() {
		return reimbResolved;
	}
	public void setReimbResolved(Timestamp timestamp) {
		this.reimbResolved = timestamp;
	}
	public String getReimbDescr() {
		return reimbDescr;
	}
	public void setReimbDescr(String reimbDescr) {
		this.reimbDescr = reimbDescr;
	}
	public Blob getReimbReciept() {
		return reimbReciept;
	}
	public void setReimbReciept(Blob reimbReciept) {
		this.reimbReciept = reimbReciept;
	}

	public Users getReimbAuthor() {
		return reimbAuthor;
	}

	public void setReimbAuthor(Users reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public Users getReimbResolver() {
		return reimbResolver;
	}

	public void setReimbResolver(Users reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}
	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}
	public String getReimbType() {
		return reimbType;
	}
	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmt=" + reimbAmt + ", reimbSubmitted=" + reimbSubmitted
				+ ", reimbResolved=" + reimbResolved + ", reimbDescr=" + reimbDescr + ", reimbReciept=" + reimbReciept
				+ ", reimbAuthor= User, reimbResolver= Finance Man, reimbStatus=" + reimbStatus
				+ ", reimbType=" + reimbType + "]";
	}
	
	
}
