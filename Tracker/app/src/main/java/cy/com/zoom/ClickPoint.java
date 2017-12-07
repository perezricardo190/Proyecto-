

package cy.com.zoom;

import java.util.Observable;

import android.graphics.PointF;

public class ClickPoint extends Observable {

	private PointF clickPoint;

	public ClickPoint(float x, float y) {
		clickPoint = new PointF(x, y);
	}

	public PointF get() {
		return clickPoint;
	}

	public void setClickPoint(float x, float y) {

		if (x != clickPoint.x || y != clickPoint.y) {
			clickPoint.x = x;
			clickPoint.y = y;
			setChanged();
		}
	}
}