
package radiomapserver;

public class Zone {

	private final float min;
	private final float max;
	
	Zone(float min, float max){
		this.min=min;
		this.max=max;
	}

	public float getMin() {
		return min;
	}

	public float getMax() {
		return max;
	}
}
