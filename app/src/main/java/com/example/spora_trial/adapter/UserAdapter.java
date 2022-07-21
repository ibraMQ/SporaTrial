package com.example.spora_trial.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spora_trial.R;
import com.example.spora_trial.UserViewModel;
import com.example.spora_trial.db.UserEntity;
import com.example.spora_trial.ui.RegisterActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<ViewHolder>{
    private List<UserEntity> users;
    private Context context;
    private UserViewModel mUserViewModel;

    public UserAdapter(List<UserEntity> users, Context context,UserViewModel uvm) {
        this.users = users;
        this.context = context;
        this.mUserViewModel=uvm;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_user,parent,false);
        return new ViewHolder(v).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtUser.setText(users.get(position).name);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println(users.get(holder.getAdapterPosition()).name);
                mUserViewModel.delete(users.get(holder.getAdapterPosition()).id);
                users.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                if(users.isEmpty()){
                    Intent i = new Intent(context, RegisterActivity.class);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    TextView txtUser;
    ImageButton btn;

    public UserAdapter getAdapter() {
        return adapter;
    }

    private UserAdapter adapter;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.txtUser = itemView.findViewById(R.id.txtName);
        this.btn = itemView.findViewById(R.id.btnDel);
    }
    public ViewHolder linkAdapter(UserAdapter adapter){
        this.adapter=adapter;
        return this;
    }
}
