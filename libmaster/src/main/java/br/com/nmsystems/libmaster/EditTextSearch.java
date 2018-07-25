package br.com.nmsystems.libmaster;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Custom Widget implementing a clear button and a border using a shape drawable
 * Future enhancements:
 * 1) BarCode Reader
 * 2) Border control
 * 3) Drawable (Left/Right) control
 * 4) Masks
 * 5) NFC
 */
public class EditTextSearch extends LinearLayout {

    private Context context;

    /**
     * hint to be displayed
     */
    private String mHint;

    /**
     * text
     */
    private String mValue;

    /**
     * enable/disable control
     */
    private boolean mEnabled;

    protected transient LinearLayout llBackground;
    protected transient EditText etValue;

    public String getmHint() {
        return mHint;
    }

    public void setmHint(String mHint) {
        this.mHint = mHint;
    }

    public String getmValue() {
        return mValue;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    public boolean ismEnabled() {
        return mEnabled;
    }

    public void setmEnabled(boolean mEnabled) {
        this.mEnabled = mEnabled;
        //
        cfgStatus(mEnabled);
    }

    public interface IEditTextSearchChangeText {
        void reportTextChange(String text);
    }

    private IEditTextSearchChangeText delegateTextChange;

    public void setOnReportTextChangeListener(IEditTextSearchChangeText delegateTextChange) {
        this.delegateTextChange = delegateTextChange;
    }

    public EditTextSearch(Context context) {
        super(context);
        //
        initialize(context, null);
    }

    public EditTextSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        //
        initialize(context, attrs);
    }

    public EditTextSearch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        this.context = context;

        if (!isInEditMode()) {
            setDefaultValues();
        }

        if (attrs != null) {
            TypedArray a = this.context.obtainStyledAttributes(attrs,
                    R.styleable.EditTextSearch, 0, 0);
            final int N = a.getIndexCount();

            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);

                if (attr == R.styleable.EditTextSearch_mEnabled) {
                    mEnabled = a.getBoolean(attr, true);
                } else if (attr == R.styleable.EditTextSearch_mHint) {
                    mHint = a.getString(attr);
                } else if (attr == R.styleable.EditTextSearch_mValue) {
                    mValue = a.getString(attr);
                } else {
                }
            }
            a.recycle();
        }

        LayoutInflater inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.edittextsearch, this);

        llBackground = findViewById(R.id.edittextsearch_ll_background);
        etValue = findViewById(R.id.edittextsearch_tv_value);

        setControlsValues();
    }

    protected void setDefaultValues() {
        mHint = "";
        mValue = "";
        mEnabled = true;
    }

    protected void setControlsValues() {
        etValue.setHint(mHint);
        etValue.setText(mValue);

        if (etValue.getText().toString().length() > 0) {
            setRightIconVisibility(etValue, true);
        } else {
            setRightIconVisibility(etValue, false);
        }

        etValue.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                try {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        if (motionEvent.getRawX() >= (etValue.getRight() - etValue.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                            setRightIconVisibility(etValue, false);
                            etValue.setText("");

                            return true;
                        }
                    }

                    return false;


                } catch (Exception e) {
                    return false;
                }
            }
        });

        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    setRightIconVisibility(etValue, true);
                } else {
                    setRightIconVisibility(etValue, false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (delegateTextChange != null) {
                    mValue = editable.toString();
                    delegateTextChange.reportTextChange(mValue);
                }
            }
        });
    }

    private void setRightIconVisibility(EditText et_search, boolean bStatus) {
        if (bStatus) {
            Drawable x = getResources().getDrawable(R.drawable.ic_backspace_24dp);

            x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());

            et_search.setCompoundDrawables(et_search.getCompoundDrawables()[0], null, x, null);

        } else {
            et_search.setCompoundDrawables(et_search.getCompoundDrawables()[0], null, null, null);
        }
    }

    private void cfgStatus(boolean enabled) {
        etValue.setEnabled(enabled);
        //
        if (!mEnabled) {
            etValue.setTextColor(0xFF000000);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        setControlsValues();
        //
        super.onAttachedToWindow();
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        super.dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(
            SparseArray<Parcelable> container) {
        super.dispatchThawSelfOnly(container);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss._mHint = mHint;
        ss._mValue = mValue;
        ss._mEnabled = mEnabled;
        //
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        //
        setmHint(ss._mHint);
        setmValue(ss._mValue);
        setmEnabled(ss._mEnabled);
    }

    /**
     * ***************************************************************
     * Saved State inner static class
     * ***************************************************************
     */
    private static class SavedState extends BaseSavedState {
        String _mHint;
        String _mValue;
        boolean _mEnabled;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);

            _mHint = in.readString();
            _mValue = in.readString();
            _mEnabled = (in.readInt() == 0) ? false : true;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);

            out.writeString(_mHint);
            out.writeString(_mValue);
            out.writeInt(_mEnabled ? 1 : 0);
        }

        public static final Creator<SavedState> CREATOR
                = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }


}
