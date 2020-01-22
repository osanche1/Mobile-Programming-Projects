package com.example.program4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ExpenseHolder> {
    private List<Expense> myExpenses = new ArrayList<>();
    private onItemClickListener myListener;

    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_layout, parent, false);
         return new ExpenseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder holder, int i) {
        Expense currentExpense = myExpenses.get(i);
        holder.amount.setText(String.valueOf(currentExpense.getAmount()));
        holder.category.setText(currentExpense.getCategory());
        holder.date.setText(currentExpense.getDate());
        holder.name.setText(currentExpense.getName());
        holder.note.setText(currentExpense.getNote());
    }

    @Override
    public int getItemCount() {
        return myExpenses.size();
    }


    public void setOnItemClickListener(onItemClickListener listener) {
        this.myListener = listener;
    }

    public Expense getExpenseAt(int position) {
        return myExpenses.get(position);
    }

    public interface onItemClickListener {
        void onItemClick(Expense expense);
    }

    public void setExpenses(List<Expense> expenses) {
        this.myExpenses = expenses;
        notifyDataSetChanged();
    }

    class ExpenseHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView category;
        private TextView date;
        private TextView amount;
        private TextView note;


        public ExpenseHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            category = itemView.findViewById(R.id.category);
            date = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amount);
            note = itemView.findViewById(R.id.note);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    if (myListener != null && index != RecyclerView.NO_POSITION) {
                        myListener.onItemClick(myExpenses.get(index));
                    }
                }
            });
        }
    }

}

