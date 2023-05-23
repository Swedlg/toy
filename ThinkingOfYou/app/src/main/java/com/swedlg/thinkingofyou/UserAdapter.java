package com.swedlg.thinkingofyou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swedlg.thinkingofyou.Room.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<User> usersList;

    private AdapterListener adapterListener;

    public UserAdapter(Context context, AdapterListener adapterListener) {
        this.context = context;
        usersList = new ArrayList<>();
        this.adapterListener = adapterListener;
    }

    public void addUser(User user){
        usersList.add(user);
        notifyDataSetChanged();
    }

    public void removeUser(int position){
        usersList.remove(position);
        notifyDataSetChanged();
    }

    public void clearData(){
        usersList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.textView_login.setText(user.getLogin());
        holder.textView_password.setText(user.getPassword());

        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onUpdate(user);
            }
        });

        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onDelete(user.getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_login, textView_password;

        private ImageView imageView_edit, imageView_delete;

        public MyViewHolder(@NotNull View itemView){
            super(itemView);

            textView_login = itemView.findViewById(R.id.textView_login);
            textView_password = itemView.findViewById(R.id.textView_password);

            imageView_edit = itemView.findViewById(R.id.imageView_edit);
            imageView_delete = itemView.findViewById(R.id.imageView_delete);
        }
    }
}
