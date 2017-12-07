
package radiomapserver;

public class WeightRecord {

	private float wx;
	private float wy;

	public WeightRecord(float weightX, float weightY) {
		this.wx = weightX;
		this.wy = weightY;
	}

	public float getWeightX() {
		return wx;
	}

	public float getWeightY() {
		return wy;
	}
}
