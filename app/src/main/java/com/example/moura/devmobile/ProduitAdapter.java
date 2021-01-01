package com.example.moura.devmobile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.moura.devmobile.Produit;
import com.example.moura.devmobile.R;

import java.util.List;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.MyViewHolder> {


    private List<Produit> produitsList;
    private ConsultProductFragment consultProductFragment;
    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {



        public TextView name,color,model,prix,id;
        private CheckBox checkBox ;
        private RelativeLayout relativeLayout;
        private ConsultProductFragment consultProductFragment;
        public MyViewHolder(View view,ConsultProductFragment consultProductFragment) {
            super(view);
            name= (TextView) view.findViewById(R.id.name);
            color = (TextView) view.findViewById(R.id.color);
            model = (TextView) view.findViewById(R.id.model);
            prix=view.findViewById(R.id.price);

            id=view.findViewById(R.id.id);
            checkBox=view.findViewById(R.id.product_checked);
            relativeLayout=view.findViewById(R.id.relative_layout_for_products_row);
            relativeLayout.setOnLongClickListener(consultProductFragment);
            checkBox.setOnClickListener(this);
            this.consultProductFragment=consultProductFragment;


        }

        @Override
        public void onClick(View v) {
            consultProductFragment.prepareSelection(v,getAdapterPosition());
        }
    }

    public ProduitAdapter(List<Produit> produitList, ConsultProductFragment consultProductFragment) {
        this.produitsList = produitList;

        this.consultProductFragment=consultProductFragment;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.produit_row_layout, parent, false);

        return new MyViewHolder(itemView,consultProductFragment);
    }
    @Override
    public void onBindViewHolder(ProduitAdapter.MyViewHolder holder, int position) {
        Produit produit = produitsList.get(position);
        holder.name.setText(produit.getName());
        holder.model.setText(produit.getModel());
        holder.color.setText(produit.getColor());
         holder.id.setText(produit.getid());
         holder.prix.setText(produit.getPrix());

        if(!holder.consultProductFragment.is_in_action_mode)
        {
            holder.checkBox.setVisibility(View.GONE);
        }else
        {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
        }




    }
    @Override
    public int getItemCount() {
        return produitsList.size();
    }



    public void updateAdapter(List<Produit>list)
    {
        for (Produit produit : list)
        {
            produitsList.remove(produit);

        }

        notifyDataSetChanged();

    }





}










