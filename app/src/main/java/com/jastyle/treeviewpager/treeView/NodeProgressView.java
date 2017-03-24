package com.jastyle.treeviewpager.treeView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;


import com.jastyle.treeviewpager.R;

import java.util.List;

/**
 * User: Daidingkang(ddk19941017@Gmail.com)
 * Date: 2016-06-28
 * Time: 09:43
 * FIXME
 */
public class NodeProgressView extends View {
    /**
     *进度条宽度
     */

    private float width;
    /**
     * 节点半径
     */
    private float nodeRadius;
    /**
     * 多点进度条画笔
     */
    private Paint paint;
    private TextPaint textPaint;
    /**
     * 节点间隔
     */
    private int nodeInterval;
    /**
     * 进度条与文字间距
     */
    private int progress_text_Interal;
    /**
     * 边距
     */
    private float left;
    private float top;
    /**
     * 用于行间距
     */
    private int dWidth;
    /**
     * //文字大小
     */
    private float textSize;
    private int dHeight;


    public NodeProgressView(Context context) {
        this(context,null);
    }

    public NodeProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NodeProgressView);
        width = typedArray.getDimension(R.styleable.NodeProgressView_width, 5);
        nodeRadius = typedArray.getDimension(R.styleable.NodeProgressView_nodeRadius, 6);
        init(context);
    }

    public NodeProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        top = DimensionUtils.getSizeInPixels(10,context);
        left = DimensionUtils.getSizeInPixels(5,context);
        progress_text_Interal = DimensionUtils.getSizeInPixels(5,context);
        nodeInterval = DimensionUtils.getSizeInPixels(40,context);
        textSize = DimensionUtils.pixelsToSp(context,10);
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        dWidth = wm.getDefaultDisplay().getWidth();
        dHeight = wm.getDefaultDisplay().getHeight();

        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.nodeColor));
        paint.setAntiAlias(true);

        textPaint = new TextPaint();
        textPaint.setColor(getResources().getColor(R.color.nodeTextColor));
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);
    }

    private NodeProgressAdapter nodeProgressAdapter;

    /**
     * 设置适配数据
     */
    public void setNodeProgressAdapter(NodeProgressAdapter nodeProgressAdapter) {
        this.nodeProgressAdapter = nodeProgressAdapter;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (nodeProgressAdapter == null || nodeProgressAdapter.getCount() == 0)
            return;
        List<LogisticsData> data = nodeProgressAdapter.getData();

        canvas.drawRect(left, top, width + left, nodeProgressAdapter.getCount() * nodeInterval + top, paint);
        for (int i = 0; i < nodeProgressAdapter.getCount(); i++) {

            if (i == 0) {
                //画圆
                canvas.drawCircle(width / 2 + left, i * nodeInterval + top, nodeRadius, paint);
                if (i== nodeProgressAdapter.getCount()-1) {
                    //画圆
                    canvas.drawCircle(width / 2 + left, (i+1) * nodeInterval + top-nodeRadius, nodeRadius, paint);
                }
                //画文字
                StaticLayout layout = new StaticLayout(data.get(i).getContext(), textPaint, (int) (dWidth * 0.8), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                canvas.save();
                canvas.translate(left + nodeRadius * 2 + progress_text_Interal, i * nodeInterval+top/2);
                layout.draw(canvas);
                canvas.restore();//重置
            } else {
                //画圆
                canvas.drawCircle(width / 2 + left, i * nodeInterval + top, nodeRadius, paint);
                if (i== nodeProgressAdapter.getCount()-1) {
                    //画圆
                    canvas.drawCircle(width / 2 + left, (i+1) * nodeInterval + top-nodeRadius, nodeRadius, paint);
                }
                //画文字
                StaticLayout layout = new StaticLayout(data.get(i).getContext(), textPaint, (int) (dWidth * 0.8), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                canvas.save();//很重要，不然会样式出错
                canvas.translate(left + nodeRadius * 2 + progress_text_Interal, i * nodeInterval + top/2);
                layout.draw(canvas);
                canvas.restore();//重置
            }

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (nodeProgressAdapter == null || nodeProgressAdapter.getCount() == 0)
            return;
        setMeasuredDimension(widthMeasureSpec, nodeProgressAdapter.getCount() * nodeInterval + (int) top);
    }

}
