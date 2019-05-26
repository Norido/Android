package com.example.rebo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// Tạo adapter gắn các sách vào list view
public class Adapter_Book_moi_nhat extends RecyclerView.Adapter<Adapter_Book_moi_nhat.ViewHolder> {

    private ArrayList<Book> data;
    public Adapter_Book_moi_nhat(ArrayList<Book> data) {
        this.data = data;
    }
    @Override
    public Adapter_Book_moi_nhat.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sach,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final Adapter_Book_moi_nhat.ViewHolder viewHolder, int position) {
        Book book = data.get(position);
        viewHolder.title.setText(book.getTenSach());
        viewHolder.author.setText(book.getTacGia());
        Picasso.get().load(book.getBiaSach()).into(viewHolder.img);
        // set event listen cho no khi duoc goi
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                    Intent intent = new Intent(view.getContext(),Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    view.getContext().startActivity(intent);
                }
                else {
                    Intent intent = new Intent(view.getContext(),ActivityDetail.class);
                    Book b = data.get(position);
                    intent.putExtra("tenSach",b.getTenSach());
//                  intent.putExtra("ngayXuatBan",b.getNgayXuatBan());
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title,author;
        public ImageView img;
        private ItemClickListener itemClickListener; //Khai báo interface
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById((R.id.item_title));
            author = itemView.findViewById((R.id.item_author));
            img = itemView.findViewById((R.id.item_book));
            //listen click
            itemView.setOnClickListener(this); //  set sự kiên onClick cho View
        }
        // Gọi interface , false là vì đây là onClick, true là longclick
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
        //Tạo setter cho biến itemClickListenenr
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }
    }
}
