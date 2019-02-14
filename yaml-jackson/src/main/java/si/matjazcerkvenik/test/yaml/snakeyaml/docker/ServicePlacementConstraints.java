package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

import java.util.List;

public class ServicePlacementConstraints {
	
	private List<String> constraints;

	public List<String> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}

	@Override
	public String toString() {
		return "Placement [constraints=" + constraints + "]";
	}
	
	
	
}
