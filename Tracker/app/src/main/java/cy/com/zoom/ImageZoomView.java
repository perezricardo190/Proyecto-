

package cy.com.zoom;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import cy.com.Inlocate.BooleanObservable;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

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
	private ArrayList<PointF> points = new ArrayList<PointF>();
	private final Paint p = new Paint();
	private BooleanObservable trackMe;

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

			if (curClick.get().x >= 0 && curClick.get().x <= bitmapWidth && curClick.get().y >= 0 && curClick.get().y <= bitmapHeight) {

				boolean isAlreadyMarked = false;
				PointF point = null;
				// Check if this point is already marked
				for (int i = 0; i < points.size(); ++i) {
					point = (PointF) points.get(i);

					if (curClick.get().equals(point.x, point.y)) {
						isAlreadyMarked = true;

						PointF pointLast = (PointF) points.get(points.size() - 1);

						if (pointLast != point) {
							point = (PointF) points.set(i, pointLast);
							points.set(points.size() - 1, point);
						}
						break;
					}
				}

				if (!isAlreadyMarked)
					points.add(new PointF(curClick.get().x, curClick.get().y));
			}

			p.setColor(Color.RED);
			int radius = 4;

			for (int i = 0; i < points.size(); ++i) {

				if (i == points.size() - 1) {
					if (trackMe.get())
						p.setColor(Color.GREEN);
					else
						p.setColor(0xff47e3ff);
					radius = 7;
				}

				PointF temp = points.get(i);

				if (temp.x != -1 && temp.y != -1 && temp.x >= mRectSrc.left - 5 && temp.x <= mRectSrc.right + 5 && temp.y >= mRectSrc.top - 5
						&& temp.y <= mRectSrc.bottom + 5) {

					canvas.drawCircle((temp.x - mRectSrc.left) / Xana + mRectDst.left, (temp.y - mRectSrc.top) / Yana + mRectDst.top, radius, this.p);

					if (i == points.size() - 1) {
						p.setColor(Color.RED);
						canvas.drawCircle((temp.x - mRectSrc.left) / Xana + mRectDst.left, (temp.y - mRectSrc.top) / Yana + mRectDst.top, 2, this.p);
						p.setColor(Color.BLUE);
						p.setStyle(Style.STROKE);
						canvas.drawCircle((temp.x - mRectSrc.left) / Xana + mRectDst.left, (temp.y - mRectSrc.top) / Yana + mRectDst.top, radius,
								this.p);
						p.setStyle(Style.FILL_AND_STROKE);
					}
				}
			}
		}
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

	public void clearPoints() {
		points.clear();
		invalidate();
	}

	public void setTrackMe(BooleanObservable trackMe) {
		if (this.trackMe != null) {
			this.trackMe.deleteObserver(this);
		}
		this.trackMe = trackMe;
		this.trackMe.addObserver(this);
	}

}
