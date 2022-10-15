package org.taehyeon.welcome_pet_khackathon.Community;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.taehyeon.welcome_pet_khackathon.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.MyHolder> {
    Context context;
    List<commentInfo> commentList;
    String myUid,postId;

    public commentAdapter(Context context, List<commentInfo> commentList, String myUid, String postId) {
        this.context = context;
        this.commentList = commentList;
        this.myUid = myUid;
        this.postId = postId;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_cardview,viewGroup,false);

        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {

        final String uid = commentList.get(i).getUid();
        String name = commentList.get(i).getName();
        //String email = commentList.get(i).getEmail();
        final String cid = commentList.get(i).getcId();
        String comment = commentList.get(i).getComment();
        String time = commentList.get(i).getTimeStamp();

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(time));
        String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa",calendar).toString();

        holder.nameTv.setText(name);
        holder.commentTv.setText(comment);
        holder.timeTv.setText(pTime);
        holder.avatarIv.setImageResource(R.drawable.user);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myUid.equals(uid)){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                    builder.setTitle("Delete");
                    builder.setMessage("이 댓글을 지우시겠습니까?");
                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteComment(cid);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                }
                else {
                    Toast.makeText(context, "Can't delete other's comment....", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void deleteComment(String cid) {
        final DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Posts").child(postId);
        ref.child("tcomments").child(cid).removeValue();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String comments = ""+ snapshot.child("comments").getValue();
                int newCommentVal = Integer.parseInt(comments) - 1;
                ref.child("comments").setValue(""+newCommentVal);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView avatarIv;
        TextView nameTv,commentTv,timeTv;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            avatarIv = itemView.findViewById(R.id.avatarIv);
            nameTv = itemView.findViewById(R.id.nameTv);
            commentTv = itemView.findViewById(R.id.commentTv);
            timeTv = itemView.findViewById(R.id.timeTv);

        }
    }
}
