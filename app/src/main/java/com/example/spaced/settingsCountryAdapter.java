package com.example.spaced;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spaced.CountryInventory.CountryModel;

import java.util.ArrayList;

public class settingsCountryAdapter extends RecyclerView.Adapter<settingsCountryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CountryModel> arrayList;
    private boolean isNewRadioButtonChecked = false;
    private int lastCheckedPosition = -1;

    public settingsCountryAdapter(Context context, ArrayList<CountryModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public settingsCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item_country, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull settingsCountryAdapter.ViewHolder holder, int position) {
        CountryModel countryModel = arrayList.get(position);
        holder.txtView_country.setText(countryModel.getName());

        if (isNewRadioButtonChecked)
        {
            holder.rb_country.setChecked(countryModel.isSelected());
        }
        else
        {
            if (holder.getAdapterPosition() == 0)
            {
                holder.rb_country.setChecked(true);
                lastCheckedPosition = 0;
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtView_country;
        private ConstraintLayout rowItem;
        private RadioButton rb_country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtView_country = itemView.findViewById(R.id.txtView_country);
            this.rowItem = itemView.findViewById(R.id.rowItem);
            this.rb_country = itemView.findViewById(R.id.rb_country);

            rb_country.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleRadioButtonChecks(getAdapterPosition());
                }
            });

            rowItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, String.valueOf(arrayList.get(getAdapterPosition())), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleRadioButtonChecks(int adapterPosition) {
        isNewRadioButtonChecked = true;
        arrayList.get(lastCheckedPosition).setSelected(false);
        arrayList.get(adapterPosition).setSelected(true);
        lastCheckedPosition = adapterPosition;
        notifyDataSetChanged();
    }
}
