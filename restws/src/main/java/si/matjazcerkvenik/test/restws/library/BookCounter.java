package si.matjazcerkvenik.test.restws.library;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookCounter {

	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}