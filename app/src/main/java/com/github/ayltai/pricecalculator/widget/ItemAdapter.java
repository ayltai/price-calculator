package com.github.ayltai.pricecalculator.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ayltai.pricecalculator.Components;
import com.github.ayltai.pricecalculator.databinding.ViewItemBinding;
import com.github.ayltai.pricecalculator.model.Item;
import com.github.ayltai.pricecalculator.model.Unit;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public final class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> implements Disposable, LifecycleObserver {
    //region Variables

    private final CompositeDisposable disposables = new CompositeDisposable();

    @NonNull
    @lombok.NonNull
    private final List<Item> items = new ArrayList<>();

    //endregion

    //region Constructors

    public ItemAdapter() {
        this(null);
    }

    public ItemAdapter(@Nullable final List<Item> items) {
        if (items == null) {
            this.items.add(Item.create());
            this.items.add(Item.create());
        } else {
            this.items.addAll(items);
        }
    }

    //endregion

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @NonNull
    public List<Item> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    @Override
    public boolean isDisposed() {
        return this.disposables.isDisposed();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void dispose() {
        this.disposables.dispose();
    }

    public void append() {
        this.items.add(Item.create());
        this.notifyItemInserted(this.items.size() - 1);
    }

    public void remove(final int position) {
        this.items.remove(position);
        this.notifyItemRemoved(position);
    }

    public void refresh() {
        final Item bestItem = Item.findBest(this.items);

        for (int i = 0; i < this.items.size(); i++) {
            final Item item = this.items.get(i);
            item.setBestItem(bestItem);

            if (item.getUnit().getType() != Components.getInstance().getConfigComponent().userConfigurations().getUnitType()) item.setUnit(Unit.create());

            this.notifyItemChanged(i);
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @lombok.NonNull final ViewGroup parent, final int viewType) {
        final ItemViewHolder holder = new ItemViewHolder(ViewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

        this.disposables.add(holder.clears()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(item -> {
                item.clear();
                holder.bind(item);
            }));

        this.disposables.add(holder.removes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(item -> this.remove(this.items.indexOf(item))));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @lombok.NonNull final ItemViewHolder holder, final int position) {
        holder.bind(this.items.get(position));
    }
}
