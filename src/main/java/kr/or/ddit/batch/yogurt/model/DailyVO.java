package kr.or.ddit.batch.yogurt.model;

public class DailyVO {
	private int cid;	// 고객번호
	private int pid;	// 제품번호
	private String dt;	// 실적일자
	private int cnt;	// 애음수량
	
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "DailyVO [cid=" + cid + ", pid=" + pid + ", dt=" + dt + ", cnt=" + cnt + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cid;
		result = prime * result + cnt;
		result = prime * result + ((dt == null) ? 0 : dt.hashCode());
		result = prime * result + pid;
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
		DailyVO other = (DailyVO) obj;
		if (cid != other.cid)
			return false;
		if (cnt != other.cnt)
			return false;
		if (dt == null) {
			if (other.dt != null)
				return false;
		} else if (!dt.equals(other.dt))
			return false;
		if (pid != other.pid)
			return false;
		return true;
	}
	
	
	
	
	
}
