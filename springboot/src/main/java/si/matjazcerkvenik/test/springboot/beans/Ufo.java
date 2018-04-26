package si.matjazcerkvenik.test.springboot.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype") // this object will not be singleton anymore
public class Ufo {
	
	public void fire() {
		System.out.println("fireing laser beams from #" + this.toString());
	}
	
}
