package com.github.ayltai.pricecalculator.app;

import java.util.ArrayList;
import java.util.Map;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.MenuItem;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.ayltai.pricecalculator.Components;
import com.github.ayltai.pricecalculator.R;
import com.github.ayltai.pricecalculator.config.UserConfigurations;
import com.github.ayltai.pricecalculator.databinding.ActivityMainBinding;
import com.github.ayltai.pricecalculator.model.UnitType;
import com.github.ayltai.pricecalculator.util.Converter;
import com.github.ayltai.pricecalculator.util.DevUtils;
import com.github.ayltai.pricecalculator.widget.ItemAdapter;

public final class MainActivity extends ThemedActivity implements Toolbar.OnMenuItemClickListener {
    //region Constants

    private static final String KEY_ITEMS = "ITEMS";

    private static final Map<UnitType, Integer> UNIT_TYPE_TITLES = new ArrayMap<>();
    private static final Map<UnitType, Integer> UNIT_TYPE_ICONS  = new ArrayMap<>();

    //endregion

    //region Variables

    private ActivityMainBinding binding;
    private ItemAdapter         adapter;

    //endregion

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        MainActivity.UNIT_TYPE_TITLES.put(UnitType.NONE, R.string.action_unit_type_none);
        MainActivity.UNIT_TYPE_TITLES.put(UnitType.AREA, R.string.action_unit_type_area);
        MainActivity.UNIT_TYPE_TITLES.put(UnitType.LENGTH, R.string.action_unit_type_length);
        MainActivity.UNIT_TYPE_TITLES.put(UnitType.TIME, R.string.action_unit_type_time);
        MainActivity.UNIT_TYPE_TITLES.put(UnitType.VOLUME, R.string.action_unit_type_volume);
        MainActivity.UNIT_TYPE_TITLES.put(UnitType.WEIGHT, R.string.action_unit_type_weight);

        MainActivity.UNIT_TYPE_ICONS.put(UnitType.NONE, R.drawable.ic_unit_type_none);
        MainActivity.UNIT_TYPE_ICONS.put(UnitType.AREA, R.drawable.ic_unit_type_area_white_24dp);
        MainActivity.UNIT_TYPE_ICONS.put(UnitType.LENGTH, R.drawable.ic_unit_type_length);
        MainActivity.UNIT_TYPE_ICONS.put(UnitType.TIME, R.drawable.ic_unit_type_time_black_24dp);
        MainActivity.UNIT_TYPE_ICONS.put(UnitType.VOLUME, R.drawable.ic_unit_type_volume);
        MainActivity.UNIT_TYPE_ICONS.put(UnitType.WEIGHT, R.drawable.ic_unit_type_weight);
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        this.setContentView(this.binding.getRoot());

        this.adapter = new ItemAdapter(savedInstanceState == null ? null : savedInstanceState.getParcelableArrayList(MainActivity.KEY_ITEMS));
        this.getLifecycle().addObserver(this.adapter);

        this.binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.binding.recyclerView.setAdapter(this.adapter);

        this.binding.toolbar.setOnMenuItemClickListener(this);
        this.binding.appBar.setOnMenuItemClickListener(this);

        this.binding.actionButton.setOnClickListener(view -> {
            this.adapter.append();
            this.binding.recyclerView.scrollToPosition(this.adapter.getItemCount() - 1);
        });

        this.refresh();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (this.adapter != null) this.getLifecycle().removeObserver(this.adapter);
    }

    @CallSuper
    @Override
    protected void onSaveInstanceState(@NonNull @lombok.NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);

        if (this.adapter != null) outState.putParcelableArrayList(MainActivity.KEY_ITEMS, new ArrayList<>(this.adapter.getItems()));
    }

    @CallSuper
    @Override
    protected void onRestoreInstanceState(@NonNull @lombok.NonNull final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (this.adapter != null) this.getLifecycle().removeObserver(this.adapter);

        this.adapter = new ItemAdapter(savedInstanceState.getParcelableArrayList(MainActivity.KEY_ITEMS));
        this.getLifecycle().addObserver(this.adapter);

        this.binding.recyclerView.setAdapter(this.adapter);
    }

    @Override
    public boolean onMenuItemClick(@NonNull @lombok.NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                this.adapter.refresh();
                return true;

            case R.id.action_unit_type:
                this.showUnitTypePicker();
                return true;

            case R.id.action_clear_all:
                this.binding.recyclerView.setAdapter(this.adapter = new ItemAdapter());
                return true;

            case R.id.action_theme:
                final UserConfigurations configurations = Components.getInstance()
                    .getConfigComponent()
                    .userConfigurations();

                configurations.setIsDarkTheme(!configurations.isDarkTheme());
                if (!DevUtils.isRunningInstrumentedTest()) this.recreate();

                return true;

            default:
                return false;
        }
    }

    private void showUnitTypePicker() {
        final UserConfigurations configurations = Components.getInstance()
            .getConfigComponent()
            .userConfigurations();

        new AlertDialog.Builder(this, R.style.DialogTheme)
            .setTitle(R.string.label_unit)
            .setSingleChoiceItems(R.array.label_units, configurations.getUnitType().getId(), (dialog, which) -> {
                configurations.setUnitType(Converter.integerToUnitType(which));

                this.refresh();

                dialog.dismiss();
            })
            .show();
    }

    private void refresh() {
        this.adapter.refresh();

        final UnitType unitType = Components.getInstance().getConfigComponent().userConfigurations().getUnitType();
        final MenuItem item     = this.binding.appBar.getMenu().findItem(R.id.action_unit_type);

        final Integer titleId = MainActivity.UNIT_TYPE_TITLES.get(unitType);
        if (titleId != null) item.setTitle(titleId);

        final Integer iconId = MainActivity.UNIT_TYPE_ICONS.get(unitType);
        if (iconId != null) item.setIcon(iconId);
    }
}
