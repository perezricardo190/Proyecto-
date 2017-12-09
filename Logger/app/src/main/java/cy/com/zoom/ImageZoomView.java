
package cy.com.zoom;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * View capable of drawing an image at different zoom state levels
 */
public class ImageZoomView extends View implements Observer {

	/** Paint object used when drawing bitmap. */
	private final Paint mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);

	/** Rectangle used (and re-used) for cropping source image. */
	private final Rect mRectSrc = new Rect();

	/** Rectangle used (and re-used) for specifying drawing area on canvas. */
	private final Rect mRectDst = new Rect();

	/** Object holding aspect quotient */
	private final AspectQuotient mAspectQuotient = new AspectQuotient();

	/** The bitmap that we're zooming in, and drawing on the screen. */
	private Bitmap mBitmap;

	/** State of the zoom. */
	private ZoomState mState;

	// Public methods
	private ClickPoint curClick;
	private final PointF prevClick = new PointF(0, 0);
	private final ClickPoint clickPoint = new ClickPoint(-1, -1);

	private ArrayList<PointF> points = new ArrayList<PointF>();

	private Context c = null;

	/**
	 * Constructor
	 */
	public ImageZoomView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setCurClick(ClickPoint click) {

		if (curClick != null) {
			curClick.deleteObserver(this);
		}
		curClick = click;
		curClick.addObserver(this);

		invalidate();
	}

	public void setContext(Context c) {
		this.c = c;
	}

	/**
	 * Set image bitmap
	 * 
	 * @param bitmap
	 *            The bitmap to view and zoom into
	 */
	public void setImage(Bitmap bitmap) {
		mBitmap = bitmap;

		mAspectQuotient.updateAspectQuotient(getWidth(), getHeight(), mBitmap.getWidth(), mBitmap.getHeight());
		mAspectQuotient.notifyObservers();

		points.clear();

		invalidate();
	}

	/**
	 * Set object holding the zoom state that should be used
	 * 
	 * @param state
	 *            The zoom state
	 */
	public void setZoomState(ZoomState state) {
		if (mState != null) {
			mState.deleteObserver(this);
		}

		mState = state;
		mState.addObserver(this);

		invalidate();
	}

	/**
	 * Gets reference to object holding aspect quotient
	 * 
	 * @return Object holding aspect quotient
	 */
	public AspectQuotient getAspectQuotient() {
		return mAspectQuotient;
	}

	// Superclass overrides

	@Override
	protected void onDraw(Canvas canvas) {
		if (mBitmap != null && mState != null) {
			final float aspectQuotient = mAspectQuotient.get();

			final int viewWidth = getWidth();
			final int viewHeight = getHeight();
			final int bitmapWidth = mBitmap.getWidth();
			final int bitmapHeight = mBitmap.getHeight();

			final float panX = mState.getPanX();
			final float panY = mState.getPanY();
			final float zoomX = mState.getZoomX(aspectQuotient) * viewWidth / bitmapWidth;
			final float zoomY = mState.getZoomY(aspectQuotient) * viewHeight / bitmapHeight;

			// Setup source and destination rectangles
			mRectSrc.left = (int) (panX * bitmapWidth - viewWidth / (zoomX * 2));
			mRectSrc.top = (int) (panY * bitmapHeight - viewHeight / (zoomY * 2));
			mRectSrc.right = (int) (mRectSrc.left + viewWidth / zoomX);
			mRectSrc.bottom = (int) (mRectSrc.top + viewHeight / zoomY);

			mRectDst.left = getLeft();
			mRectDst.top = getTop();
			mRectDst.right = getRight();
			mRectDst.bottom = getBottom();

			// Adjust source rectangle so that it fits within the source image.
			if (mRectSrc.left < 0) {
				mRectDst.left += -mRectSrc.left * zoomX;
				mRectSrc.left = 0;
			}
			if (mRectSrc.right > bitmapWidth) {
				mRectDst.right -= (mRectSrc.right - bitmapWidth) * zoomX;
				mRectSrc.right = bitmapWidth;
			}
			if (mRectSrc.top < 0) {
				mRectDst.top += -mRectSrc.top * zoomY;
				mRectSrc.top = 0;
			}
			if (mRectSrc.bottom > bitmapHeight) {
				mRectDst.bottom -= (mRectSrc.bottom - bitmapHeight) * zoomY;
				mRectSrc.bottom = bitmapHeight;
			}

			float Xana = (float) (mRectSrc.right - mRectSrc.left) / (float) (mRectDst.right - mRectDst.left);
			float Yana = (float) (mRectSrc.bottom - mRectSrc.top) / (float) (mRectDst.bottom - mRectDst.top);

			canvas.drawBitmap(mBitmap, mRectSrc, mRectDst, mPaint);
			
			
			Paint p = new Paint();
			p.setColor(Color.RED);

			float clickPixelsX = (float) mRectSrc.left + (curClick.get().x - mRectDst.left) * Xana;
			float clickPixelsY = (float) mRectSrc.top + (curClick.get().y - mRectDst.top) * Yana;

			if (clickPixelsX >= 0 && clickPixelsX <= bitmapWidth && clickPixelsY >= 0 && clickPixelsY <= bitmapHeight) {
				if (!curClick.get().equals(prevClick.x, prevClick.y)) {
					prevClick.x = curClick.get().x;
					prevClick.y = curClick.get().y;
					clickPoint.setClickPoint(clickPixelsX, clickPixelsY);
					clickPoint.notifyObservers();
				}
			} else if (!curClick.get().equals(prevClick.x, prevClick.y)) {
				Toast.makeText(c, "Click a point on the floorplan image!", Toast.LENGTH_SHORT).show();
			}

			for (int i = 0; i < points.size(); ++i) {

				PointF temp = points.get(i);

				if (temp.x != -1 && temp.y != -1 && temp.x >= mRectSrc.left - 5 && temp.x <= mRectSrc.right + 5 && temp.y >= mRectSrc.top - 5
						&& temp.y <= mRectSrc.bottom + 5) {
					canvas.drawCircle((temp.x - mRectSrc.left) / Xana + mRectDst.left, (temp.y - mRectSrc.top) / Yana + mRectDst.top, 5, p);
				}
			}

			p.setColor(Color.GREEN);
			if (clickPoint.get().x != -1 && clickPoint.get().y != -1 && clickPoint.get().x >= mRectSrc.left - 5
					&& clickPoint.get().x <= mRectSrc.right + 5 && clickPoint.get().y >= mRectSrc.top - 5
					&& clickPoint.get().y <= mRectSrc.bottom + 5) {
				canvas.drawCircle((clickPoint.get().x - mRectSrc.left) / Xana + mRectDst.left, (clickPoint.get().y - mRectSrc.top) / Yana
						+ mRectDst.top, 5, p);
			}

		}
	}

	public void setPoint() {
		points.add(new PointF(clickPoint.get().x, clickPoint.get().y));
	}

	public ClickPoint getClickPoint() {
		return clickPoint;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		if (mBitmap != null) {
			mAspectQuotient.updateAspectQuotient(right - left, bottom - top, mBitmap.getWidth(), mBitmap.getHeight());
			mAspectQuotient.notifyObservers();
		}
	}

	// implements Observer
	public void update(Observable observable, Object data) {
		invalidate();
	}

}
