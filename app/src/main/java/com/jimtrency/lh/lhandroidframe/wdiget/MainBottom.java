package com.jimtrency.lh.lhandroidframe.wdiget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jimtrency.lh.androidframe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\11\27 0027.
 */

public class MainBottom extends LinearLayout {

    private List<View> views;

    private Context mContext;
    private BottomClickListener bottomClickListener;
    private List<ImageView> imageViews;
    private List<TextView> textViews;
    private List<Integer> beforeImageList;
    private List<Integer> afterIamgeList;

    private int afterColor;
    private int beforeColor;

    public MainBottom(Context context) {
        super(context);
        init(context);
    }

    public MainBottom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MainBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext) {
        views = new ArrayList<>();
        beforeImageList = new ArrayList<Integer>();
        afterIamgeList = new ArrayList<Integer>();
        imageViews = new ArrayList<>();
        textViews = new ArrayList<>();

        if (beforeColor==0){
            beforeColor=Color.parseColor("#666666");
        }
        if (afterColor==0) {
            beforeColor = Color.parseColor("#0ec8dd");
        }

        this.mContext = mContext;
        setOrientation(HORIZONTAL);
    }

    //设置文件的color
    public MainBottom setFocusTextColor(int beforeColor, int afterColor){
        this.afterColor=afterColor;
        this.beforeColor=beforeColor;
        return this;
    }


    //注册回调监听
    public MainBottom setBottomClickListener(BottomClickListener bottomClickListener) {
        this.bottomClickListener = bottomClickListener;
        return this;
    }

    //添加底部每个buttom
    public MainBottom setSingleView(int beforeImgId, int afterImgId, String text, int opsition) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.buttom_item, null);
        view.setTag(opsition);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != bottomClickListener) {
                    bottomClickListener.click(v);
                    changeState((Integer) v.getTag());
                }
            }
        });
        Bitmap bitmap;
        int color;
        if (opsition == 0) {
            bitmap = BitmapFactory.decodeResource(mContext.getResources(), afterImgId);
            color=afterColor;
        } else {
            bitmap = BitmapFactory.decodeResource(mContext.getResources(), beforeImgId);
            color=beforeColor;
        }

        afterIamgeList.add(afterImgId);
        beforeImageList.add(beforeImgId);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_main_item);
        imageView.setImageBitmap(bitmap);
        imageViews.add(imageView);

        TextView textView = (TextView) view.findViewById(R.id.tv_main_item);
        textView.setText(text);
        textView.setTextColor(color);
        textViews.add(textView);

        //设置权重
        view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        ;
        views.add(view);
        addView(view);

        return this;
    }

    //设置显示当前那个Item
    public MainBottom setCurrentItem(int position) {
        changeState(position);
        bottomClickListener.click(views.get(position));
        return this;
    }

    //设置图片的大小
    public MainBottom setImageSize(int wdith, int height) {

        for(int i=0;i<imageViews.size();i++){
            LayoutParams layoutParams= (LayoutParams) imageViews.get(i).getLayoutParams();
            layoutParams.height = height;
            layoutParams.width = wdith;
            imageViews.get(i).setLayoutParams(layoutParams);
        }

        return this;
    }

    //设置文字的大小

    /**
     * textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,45); //设置45PX
     * textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,45); //设置45SP
     * textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,45);//设置45DIP
     */
    public MainBottom setTextSize(int size){
        for (int i=0;i<textViews.size();i++){
            textViews.get(i).setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
        }

        return this;
    }




    public interface BottomClickListener {
        void click(View v);
    }

    //改变了，每个底部button的状态
    public void changeState(int position) {

        for (int i = 0; i < imageViews.size(); i++) {
            if (i == position) {
                releaseImageViewResouce(imageViews.get(position));
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), afterIamgeList.get(position));
                imageViews.get(position).setImageBitmap(bitmap);
                textViews.get(position).setTextColor(afterColor);
            } else {
                releaseImageViewResouce(imageViews.get(i));
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), beforeImageList.get(i));
                imageViews.get(i).setImageBitmap(bitmap);
                textViews.get(i).setTextColor(beforeColor);
            }
        }
    }

    //清除图片内容
    public static void releaseImageViewResouce(ImageView imageView) {
        if (imageView == null) return;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }

}
