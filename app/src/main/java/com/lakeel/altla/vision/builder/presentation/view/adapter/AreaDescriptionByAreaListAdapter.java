package com.lakeel.altla.vision.builder.presentation.view.adapter;

import com.lakeel.altla.vision.builder.R;
import com.lakeel.altla.vision.builder.presentation.presenter.AreaDescriptionByAreaListPresenter;
import com.lakeel.altla.vision.builder.presentation.view.AreaDescriptionItemView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class AreaDescriptionByAreaListAdapter
        extends RecyclerView.Adapter<AreaDescriptionByAreaListAdapter.ViewHolder> {

    private final AreaDescriptionByAreaListPresenter presenter;

    private RecyclerView recyclerView;

    private LayoutInflater inflater;

    private View selectedItem;

    public AreaDescriptionByAreaListAdapter(@NonNull AreaDescriptionByAreaListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View itemView = inflater.inflate(R.layout.item_area_description, parent, false);
        itemView.setOnClickListener(v -> {
            if (recyclerView != null) {
                int position = recyclerView.getChildAdapterPosition(itemView);

                if (selectedItem == null) {
                    selectedItem = itemView;
                    selectedItem.setSelected(true);
                    presenter.onItemSelected(position);
                } else {
                    if (selectedItem == itemView) {
                        itemView.setSelected(false);
                        presenter.onItemSelected(-1);
                    } else {
                        selectedItem.setSelected(false);
                        selectedItem = itemView;
                        selectedItem.setSelected(true);
                        presenter.onItemSelected(position);
                    }
                }
            }
        });
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemPresenter.onBind(position);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements AreaDescriptionItemView {

        @BindView(R.id.text_view_name)
        TextView textViewName;

        @BindView(R.id.text_view_id)
        TextView textViewId;

        private AreaDescriptionByAreaListPresenter.ItemPresenter itemPresenter;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemPresenter = presenter.createItemPresenter();
            itemPresenter.onCreateItemView(this);
        }

        @Override
        public void onUpdateAreaDescriptionId(@NonNull String areaDescriptionId) {
            textViewId.setText(areaDescriptionId);
        }

        @Override
        public void onUpdateName(@Nullable String name) {
            textViewName.setText(name);
        }
    }
}
