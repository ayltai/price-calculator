package com.github.ayltai.pricecalculator.widget;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ayltai.pricecalculator.R;
import com.github.ayltai.pricecalculator.databinding.ViewItemBinding;
import com.github.ayltai.pricecalculator.model.Item;
import com.github.ayltai.pricecalculator.util.Converter;
import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

public final class ItemViewHolder extends RecyclerView.ViewHolder {
    //region Variables

    private final FlowableProcessor<Item> clears  = PublishProcessor.create();
    private final FlowableProcessor<Item> removes = PublishProcessor.create();

    private final ViewItemBinding binding;

    //endregion

    public ItemViewHolder(@NonNull @lombok.NonNull final ViewItemBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    //region Events

    @NonNull
    public Flowable<Item> clears() {
        return this.clears;
    }

    @NonNull
    public Flowable<Item> removes() {
        return this.removes;
    }

    //endregion

    public void bind(@NonNull @lombok.NonNull final Item item) {
        this.binding.setItem(item);
        this.binding.executePendingBindings();

        this.binding.actionClear.setOnClickListener(view -> this.clears.onNext(item));
        this.binding.actionRemove.setOnClickListener(view -> this.removes.onNext(item));

        this.binding.unit.setOnClickListener(view -> this.showUnitPicker(view.getContext(), item));
    }

    private void showUnitPicker(@NonNull @lombok.NonNull final Context context, @NonNull @lombok.NonNull final Item item) {
        final List<String> items = Arrays.asList(this.binding.unit.getResources().getStringArray(item.getUnit().getType().getItemsResId()));

        new AlertDialog.Builder(context, R.style.DialogTheme)
            .setTitle(item.getUnit().getType().getResId())
            .setSingleChoiceItems(item.getUnit().getType().getItemsResId(), items.indexOf(item.getUnit().getName()), ((dialog, which) -> {
                item.setUnit(Converter.stringToUnit(items.get(which)));

                this.binding.setItem(item);
                this.binding.executePendingBindings();

                dialog.dismiss();
            }))
            .show();
    }
}
