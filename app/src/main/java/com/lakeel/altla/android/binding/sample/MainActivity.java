package com.lakeel.altla.android.binding.sample;

import com.lakeel.altla.android.binding.BinderFactory;
import com.lakeel.altla.android.binding.Converter;
import com.lakeel.altla.android.binding.annotation.BindProperties;
import com.lakeel.altla.android.binding.annotation.BindProperty;
import com.lakeel.altla.android.binding.annotation.ConverterName;
import com.lakeel.altla.android.binding.annotation.OnClickCommand;
import com.lakeel.altla.android.binding.annotation.OnLongClickCommand;
import com.lakeel.altla.android.binding.command.RelayCommand;
import com.lakeel.altla.android.binding.converter.RelayConverter;
import com.lakeel.altla.android.binding.property.BooleanProperty;
import com.lakeel.altla.android.binding.property.IntProperty;
import com.lakeel.altla.android.binding.property.ObjectProperty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

public final class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final ViewModel viewModel = new ViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BinderFactory binderFactory = new BinderFactory(this);
        binderFactory.create(viewModel).bind();
    }

    private final class ViewModel {

        @BindProperty(id = R.id.radio_group_button, name = "checkedButton")
        IntProperty radioGroupChecked = new IntProperty() {

            int value = R.id.radio_button_button_disabled;

            @Override
            public int get() {
                return value;
            }

            @Override
            public void set(int value) {
                if (this.value != value) {
                    this.value = value;
                    raiseOnValueChanged();
                    commandClick.raiseOnCanExecuteChanged();
                }
            }
        };

        @OnClickCommand(R.id.button_on_click)
        RelayCommand commandClick = new RelayCommand(
                () -> Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_SHORT).show(),
                () -> radioGroupChecked.get() == R.id.radio_button_button_enabled);

        @BindProperty(id = R.id.text_view_set_text, name = "text")
        ObjectProperty<String> textViewText = new ObjectProperty<String>() {

            String value;

            @Nullable
            @Override
            public String get() {
                return value;
            }

            @Override
            public void set(@Nullable String value) {
                if (!Objects.equals(this.value, value)) {
                    this.value = value;
                    raiseOnValueChanged();
                }
            }
        };

        @OnClickCommand(R.id.button_set_text)
        RelayCommand commandTextViewText = new RelayCommand(() -> textViewText.set("Text was set."));

        @OnClickCommand(R.id.button_clear_text)
        RelayCommand commandClearTextViewText = new RelayCommand(() -> textViewText.set(null));

        @BindProperties({ @BindProperty(id = R.id.edit_text, name = "text"),
                          @BindProperty(id = R.id.text_view_edit_text_result, name = "text") })
        ObjectProperty<String> editTextText = new ObjectProperty<String>() {

            String value;

            @Nullable
            @Override
            public String get() {
                return value;
            }

            @Override
            public void set(@Nullable String value) {
                if (!Objects.equals(this.value, value)) {
                    Log.v(TAG, "set: " + value);
                    this.value = value;
                    raiseOnValueChanged();
                }
            }
        };

        @OnClickCommand(R.id.button_edit_text_clear_text)
        RelayCommand commandClearEditTextText = new RelayCommand(() -> editTextText.set(null));

        @OnLongClickCommand(R.id.text_view_on_long_click)
        RelayCommand commandLongClick = new RelayCommand(
                () -> Toast.makeText(MainActivity.this, "onLongClick", Toast.LENGTH_SHORT).show());

        @BindProperties({ @BindProperty(id = R.id.toggle_button, name = "checked"),
                          @BindProperty(id = R.id.text_view_toggle_button_result, name = "text",
                                        converter = "objectStringConverter"),
                          @BindProperty(id = R.id.edit_text_toggle_enabled, name = "enabled") })
        BooleanProperty toggleButtonChecked = new BooleanProperty() {

            boolean value;

            @Override
            public boolean get() {
                return value;
            }

            @Override
            public void set(boolean value) {
                if (this.value != value) {
                    this.value = value;
                    raiseOnValueChanged();
                }
            }
        };

        @ConverterName("objectStringConverter")
        Converter objectStringConverter = new RelayConverter(value -> value == null ? null : value.toString(), null);
    }
}
