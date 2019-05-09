package com.example.rebo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.r0adkll.slidr.Slidr;

import com.yinglan.shadowimageview.ShadowImageView;
import info.hoang8f.widget.FButton;

public class ActivityDetail extends AppCompatActivity {
    private TextView text, xemthem, name, author;
    private FButton read_book,tai_sach;
    //private ImageView img;
    private ShadowImageView img;
    private ViewGroup transitionsContainer;
    private LinearLayout layout_doc_sach, layout_chi_tiet;
    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_book);
        // slide activity
        Slidr.attach(this);

        //khoi tao gan ID
        transitionsContainer = (ViewGroup) findViewById(R.id.transition_container);
        text = (TextView) transitionsContainer.findViewById(R.id.title_detail);
        xemthem = (TextView) transitionsContainer.findViewById(R.id.txt_xemthem);
        layout_doc_sach = (LinearLayout) transitionsContainer.findViewById(R.id.layout_doc_sach);
        layout_chi_tiet = (LinearLayout) transitionsContainer.findViewById(R.id.layout_chi_tiet);

        // LIKE
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view);
        lottieAnimationView.setOnClickListener(new View.OnClickListener(){
                @Override
            public void onClick(View v) {
                    if(lottieAnimationView.getFrame() == 0) {

                        lottieAnimationView.playAnimation();
                        lottieAnimationView.setRepeatCount(0);

                    }
                    else{
                        //104 frame
                        lottieAnimationView.pauseAnimation();
                        lottieAnimationView.setFrame(0);


                    }
            }
        });


        // Nhan du lieu
        img = (ShadowImageView) findViewById(R.id.img_detail);
        name = (TextView) findViewById(R.id.name_detail);
        author = (TextView) findViewById(R.id.author_detail);
        Intent recv_from_online = getIntent();
        img.setImageResource(recv_from_online.getIntExtra("img", 0));
        name.setText(recv_from_online.getIntExtra("title",0));
        author.setText(recv_from_online.getIntExtra("author",0));
        // chuyen sang doc sach
        read_book = (FButton) findViewById(R.id.doc_sach);

        read_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityDetail.this, ReadBook.class);
                startActivity(intent);
            }
        });
        // tai sach
        tai_sach = (FButton) findViewById(R.id.tai_sach);
        xemthem.setOnClickListener(new View.OnClickListener() {

            boolean visible;

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionsContainer);
                visible = !visible;
                if (visible) {
                    text.setText(R.string.detail_naruto_long);
                    text.setVisibility(View.VISIBLE);
                } else {
                    text.setText(R.string.detail_naruto);
                    text.setVisibility(View.VISIBLE);
                }

            }

        });


    }

}