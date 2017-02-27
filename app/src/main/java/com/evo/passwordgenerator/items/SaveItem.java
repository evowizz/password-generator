package com.evo.passwordgenerator.items;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.evo.passwordgenerator.R;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

/**
 * Created by danielstone on 27/02/2017.
 */

public class SaveItem extends AbstractItem<SaveItem, SaveItem.ViewHolder> {
    public String password;
    public long id;

    public SaveItem(long id, String password) {
        this.id = id;
        this.password = password;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.save_item;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.save_item;
    }

    //The logic to bind your data to the view
    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        //call super so the selection is already handled for you
        super.bindView(viewHolder, payloads);

        viewHolder.password.setText(password);
    }

    //reset the view here (this is an optional method, but recommended)
    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.password.setText(null);
    }

    //The viewHolder used for this item. This viewHolder is always reused by the RecyclerView so scrolling is blazing fast
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView password;

        public ViewHolder(View view) {
            super(view);
            password = (TextView) view.findViewById(R.id.save_password);
        }
    }
}
