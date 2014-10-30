import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * @author Atrix1987
 *
 */
public class TriangleView extends LinearLayout {

	/**
	 * @param context
	 */
	public TriangleView(Context context) {
		super(context);
		commonConstructor(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public TriangleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		commonConstructor(context);
	}

	/**
	 *
	 */
	Paint trianglePaint;
	/**
	 *
	 */
	Path trianglePath;

	/**
	 * @param context
	 */
	private void commonConstructor(Context context) {
		trianglePaint = new Paint();
		trianglePaint.setStyle(Style.FILL);
		trianglePaint.setColor(Color.RED);
		Point point = new Point();
		point.x = 80;
		point.y = 80;
		trianglePath = getEquilateralTriangle(point, 70, Direction.SOUTH);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Log.i("Sample", "inside ondraw");
		//avoid creating objects in onDraw
		canvas.drawPath(trianglePath, trianglePaint);
	}

	private Path getEquilateralTriangle(Point p1, int width, Direction direction) {
		Log.i("Sample", "inside getEqui");
		Point p2 = null, p3 = null;

		if (direction == Direction.NORTH) {
			p2 = new Point(p1.x + width, p1.y);
			p3 = new Point(p1.x + (width / 2), p1.y - width);
		} else if (direction == Direction.SOUTH) {
			p2 = new Point(p1.x + width, p1.y);
			p3 = new Point(p1.x + (width / 2), p1.y + width);
		} else if (direction == Direction.EAST) {
			p2 = new Point(p1.x, p1.y + width);
			p3 = new Point(p1.x - width, p1.y + (width / 2));
		} else if (direction == Direction.WEST) {
			p2 = new Point(p1.x, p1.y + width);
			p3 = new Point(p1.x + width, p1.y + (width / 2));
		}

		Path path = new Path();
		path.moveTo(p1.x, p1.y);
		path.lineTo(p2.x, p2.y);
		path.lineTo(p3.x, p3.y);

		return path;
	}

	public enum Direction {
		NORTH, SOUTH, EAST, WEST;
	}

}