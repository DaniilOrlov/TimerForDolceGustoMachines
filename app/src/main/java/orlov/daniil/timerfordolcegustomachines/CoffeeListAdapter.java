package orlov.daniil.timerfordolcegustomachines;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import orlov.daniil.timerfordolcegustomachines.data.Brew;

public class CoffeeListAdapter extends RecyclerView.Adapter<CoffeeListAdapter.CoffeeViewHolder> {
    private final LayoutInflater mInflater;
    private List<Brew> mBrews;
    private ArrayList<Brew> tmpListBrews = new ArrayList<>();

    CoffeeListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.coffee_item_main, parent, false);
        return new CoffeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        if (mBrews != null) {
            final Brew current = mBrews.get(position);
            holder.coffeeColorView.setBackgroundColor(Color.parseColor(current.color));
            holder.coffeeBeverageItemView.setText(current.name);
            brewOnClick(holder, current);
        } else {
            holder.coffeeBeverageItemView.setText("please, wait...");
        }
    }

    private void brewOnClick(CoffeeViewHolder holder, final Brew current){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailedCoffeeActivity.class);
                intent.putExtra("EXTRA_BREW_ID", current.id);
                intent.putExtra("EXTRA_BREW_NAME", current.name);
                intent.putExtra("EXTRA_BREW_COLOR", current.color);
                intent.putExtra("EXTRA_DOUBLE_CAP", current.doubleCup);
                view.getContext().startActivity(intent);
            }
        });
    }

    void setBrews(List<Brew> brews) {
        mBrews = brews;
        tmpListBrews.addAll(mBrews);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mBrews != null) {
            return mBrews.size();
        }
        return 0;
    }

    class CoffeeViewHolder extends RecyclerView.ViewHolder {
        private final View coffeeColorView;
        private final TextView coffeeBeverageItemView;

        private CoffeeViewHolder(View itemView) {
            super(itemView);
            coffeeColorView = itemView.findViewById(R.id.coffeeColorView);
            coffeeBeverageItemView = itemView.findViewById(R.id.coffeeBeverage);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase().trim();
                mBrews.clear();
                if (constraint.length() == 0) {
                    mBrews.addAll(tmpListBrews);
                } else {
                    for (Brew brew : tmpListBrews) {
                        if (brew.name.toLowerCase().contains(constraint)) {
                            mBrews.add(brew);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = mBrews;

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
            }
        };
    }
}
