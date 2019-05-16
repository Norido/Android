package com.example.rebo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.r0adkll.slidr.Slidr;

import com.squareup.picasso.Picasso;
import com.yinglan.shadowimageview.ShadowImageView;
import info.hoang8f.widget.FButton;

public class ActivityDetail extends AppCompatActivity {
    private ExpandableTextView expandableTextView;
    private TextView name, author;
    private FButton read_book,tai_sach;
    //private ImageView img;
    private ShadowImageView img;
    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_book);
        // slide activity
        Slidr.attach(this);

        //xem them
        expandableTextView = (ExpandableTextView) findViewById(R.id.expandable_text_view);
        expandableTextView.setText("Naruto shippuuden là một tác phẩm của Kishimoto Masashi. Nó cũng nói về Naruto, nhưng là 2 năm sau,sau khi cậu bé cùng sư phụ Jiraiya của mình đi tập luyện xa trở về làng Lá (phần sau của Naruto). Phần hai tiếp tục câu chuyện về cậu bé Naruto sau 3 năm tu luyện cùng Jiraiya nay đã trở về làng. Cậu và những người bạn lại tiếp tục lên đường làm nhiệm vụ, đồng thời cũng tìm cách để đưa Sasuke trở về. Madara hồi sinh, cùng với kế hoạch Nguyệt Nhãn quyết nhấn chìm thế giới vào ảo mộng. Đại chiến shinobi lần thứ tư nổ ra. Toàn thế giới nhẫn giả đã kết thành liên minh, cùng chung chí hướng dập tắt âm mưu của Madara và đồng bọn. Liệu đây có phải là cuộc chiến cuối cùng?");

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
//        img = (ImageView) findViewById(R.id.img_detail);
        name = (TextView) findViewById(R.id.name_detail);
        author = (TextView) findViewById(R.id.author_detail);
        Intent recv_from_online = getIntent();
//        Picasso.get().load(recv_from_online.getStringExtra("img")).into(img);
        name.setText(recv_from_online.getStringExtra("title"));
        author.setText(recv_from_online.getStringExtra("author"));

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

    }

}