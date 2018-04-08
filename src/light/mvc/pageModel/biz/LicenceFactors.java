package light.mvc.pageModel.biz;

import java.util.List;

public class LicenceFactors{
	private List<LicencePollutantInfo> list;

	public List<LicencePollutantInfo> getList() {
		return list;
	}

	public void setList(List<LicencePollutantInfo> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "LicenceFactors [list=" + list + "]";
	}
	
}
