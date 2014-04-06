package com.fourmob.colorpicker;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ColorPickerDialogIcs extends DialogFragment implements ColorPickerSwatch
        .OnColorSelectedListener {
    protected AlertDialog mAlertDialog;
    protected int[] mColors = null;
    protected int mColumns;
    protected ColorPickerSwatch.OnColorSelectedListener mListener;
    private ColorPickerPalette mPalette;
    private ProgressBar mProgress;
    protected int mSelectedColor;
    protected int mSize;
    protected String mTitle;

    private void refreshPalette() {
        if ((this.mPalette != null) && (this.mColors != null))
            this.mPalette.drawPalette(this.mColors, this.mSelectedColor);
    }

    public void initialize(int titleId, int[] colors, int selectedColor, int columns, int size) {
        setArguments(titleId, columns, size);
        setColors(colors, selectedColor);
    }

    public void initialize(String title, int[] colors, int selectedColor, int columns, int size) {
        setArguments(title, columns, size);
        setColors(colors, selectedColor);
    }

    public void onColorSelected(int selectedColor) {
        if (this.mListener != null)
            this.mListener.onColorSelected(selectedColor);
        if ((getTargetFragment() instanceof ColorPickerSwatch.OnColorSelectedListener))
            ((ColorPickerSwatch.OnColorSelectedListener) getTargetFragment()).onColorSelected(selectedColor);
        if (selectedColor != this.mSelectedColor) {
            this.mSelectedColor = selectedColor;
            this.mPalette.drawPalette(this.mColors, this.mSelectedColor);
        }
        dismiss();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            if (getArguments().containsKey("title")) {
                this.mTitle = getArguments().getString("title");
            } else if (getArguments().containsKey("title_id")) {
                this.mTitle = getString(getArguments().getInt("title_id"));
            } else {
                this.mTitle = getString(R.string.color_picker_default_title);
            }
            this.mColumns = getArguments().getInt("columns");
            this.mSize = getArguments().getInt("size");

        }
        if (bundle != null) {
            this.mColors = bundle.getIntArray("colors");
            this.mSelectedColor = ((Integer) bundle.getSerializable("selected_color")).intValue();
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.color_picker_dialog, null);
        this.mProgress = ((ProgressBar) view.findViewById(android.R.id.progress));
        this.mPalette = ((ColorPickerPalette) view.findViewById(R.id.color_picker));
        this.mPalette.init(this.mSize, this.mColumns, this);
        if (this.mColors != null)
            showPaletteView();
        this.mAlertDialog = new AlertDialog.Builder(getActivity()).setTitle(this.mTitle).setView(view).create();
        return this.mAlertDialog;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putIntArray("colors", this.mColors);
        bundle.putSerializable("selected_color", Integer.valueOf(this.mSelectedColor));
    }

    public void setArguments(int titleId, int columns, int size) {
        Bundle bundle = new Bundle();
        bundle.putInt("title_id", titleId);
        setArguments(bundle, columns, size);
    }

    public void setArguments(String title, int columns, int size) {
        Bundle bundle = new Bundle();
        bundle.putString("title_name", title);
        setArguments(bundle, columns, size);
    }

    private void setArguments(Bundle bundle, int columns, int size) {
        bundle.putInt("columns", columns);
        bundle.putInt("size", size);
        setArguments(bundle);
    }

    public void setColors(int[] colors, int selected) {
        if ((this.mColors != colors) || (this.mSelectedColor != selected)) {
            this.mColors = colors;
            this.mSelectedColor = selected;
            refreshPalette();
        }
    }

    public void setOnColorSelectedListener(ColorPickerSwatch.OnColorSelectedListener onColorSelectedListener) {
        this.mListener = onColorSelectedListener;
    }

    public void showPaletteView() {
        if ((this.mProgress != null) && (this.mPalette != null)) {
            this.mProgress.setVisibility(View.GONE);
            refreshPalette();
            this.mPalette.setVisibility(View.VISIBLE);
        }
    }

    public void showProgressBarView() {
        if ((this.mProgress != null) && (this.mPalette != null)) {
            this.mProgress.setVisibility(View.VISIBLE);
            this.mPalette.setVisibility(View.GONE);
        }
    }
}