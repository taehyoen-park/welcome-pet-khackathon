package org.taehyeon.welcome_pet_khackathon.Community;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.taehyeon.welcome_pet_khackathon.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder>{

    static ArrayList<WriteInfo> list = new ArrayList<WriteInfo>();
    onDataItemClickListener OnDataItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View item = inflater.inflate(R.layout., parent, false);
        return new ViewHolder(item,OnDataItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        WriteInfo p = list.get(position);
        viewHolder.setItem(p);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void remove(int position)
    {
        try{
            list.remove(position);
            notifyDataSetChanged();
        }
        catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public void addItem(WriteInfo item) {
        list.add(item);
    }

    public WriteInfo  getItem(int position){
        return list.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView texttitle;
        TextView textpublisher;
        TextView textcontents;

        public ViewHolder(View itemView, final onDataItemClickListener listener) {
            super(itemView);

            texttitle = itemView.findViewById(R.id.textView);
            textpublisher = itemView.findViewById(R.id.textView2);
            textcontents = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this,view,pos);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = getAdapterPosition();
                    remove(pos);
                    return true;
                }
            });
        }

        public void setItem(WriteInfo  item) {
            textname.setText(item.getTitle());
            textmobile.setText(item.getPublisher());
            textemail.setText(item.getContents());
        }




}
