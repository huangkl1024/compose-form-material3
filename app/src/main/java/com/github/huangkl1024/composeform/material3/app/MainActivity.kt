package com.github.huangkl1024.composeform.material3.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.huangkl1024.composeform.material3.app.ui.theme.ComposeFormMaterial3Theme
import com.github.huangkl1024.composeform.material3.fields.CheckboxField
import com.github.huangkl1024.composeform.material3.fields.DateField
import com.github.huangkl1024.composeform.material3.fields.DatePickerField
import com.github.huangkl1024.composeform.material3.fields.PasswordField
import com.github.huangkl1024.composeform.material3.fields.PickerField
import com.github.huangkl1024.composeform.material3.fields.TextField
import com.github.huangkl1024.composeform.material3.fields.TimePickerField
import com.github.huangkl1024.composeform.material3.formatters.dateLong
import com.github.huangkl1024.composeform.material3.formatters.dateShort
import com.github.huangkl1024.composeform.material3.formatters.time
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeFormMaterial3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceContainer
                ) {
                    FormPage()
                }
            }
        }
    }
}

@Composable
fun FormPage() {
    val viewModel = koinViewModel<MainViewModel>()

    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {

                    Column {
                        TextField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "Name",
                            form = viewModel.form,
                            fieldState = viewModel.form.name,
                            changed = {
                                // log the name to show tat changed is called
                                Log.d("Form", "Name changed: $it")
                                // clear countries (for no reason - just to show that options list is now mutable)
                                viewModel.form.country.options = mutableListOf()
                            }
                        ).Field()

                        TextField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "Last Name",
                            form = viewModel.form,
                            fieldState = viewModel.form.lastName,
                            isEnabled = false,
                        ).Field()

                        TextField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "E-Mail",
                            form = viewModel.form,
                            fieldState = viewModel.form.email,
                            keyboardType = KeyboardType.Email
                        ).Field()

                        PasswordField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "Password",
                            form = viewModel.form,
                            fieldState = viewModel.form.password
                        ).Field()

                        PasswordField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "Password Confirm",
                            form = viewModel.form,
                            fieldState = viewModel.form.passwordConfirm
                        ).Field()

                        PickerField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "Country",
                            form = viewModel.form,
                            fieldState = viewModel.form.country
                        ).Field()

                        PickerField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "Country Not searchable",
                            form = viewModel.form,
                            fieldState = viewModel.form.countryNotSearchable,
                            isSearchable = false
                        ).Field()

                        DateField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "Start Date",
                            form = viewModel.form,
                            fieldState = viewModel.form.startDate,
                            formatter = ::dateShort
                        ).Field()

                        DateField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "End Date",
                            form = viewModel.form,
                            fieldState = viewModel.form.endDate,
                            themeResId = R.style.customDatePickerStyle,
                            formatter = ::dateLong
                        ).Field()

                        DatePickerField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "Start Date1",
                            form = viewModel.form,
                            fieldState = viewModel.form.startDate1,
                            formatter = ::dateShort
                        ).Field()

                        DatePickerField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "End Date1",
                            form = viewModel.form,
                            fieldState = viewModel.form.endDate1,
                            formatter = ::dateLong
                        ).Field()

                        TimePickerField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "Start Time",
                            form = viewModel.form,
                            fieldState = viewModel.form.startTime,
                            formatter = ::time
                        ).Field()

                        TimePickerField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            label = "End Time",
                            form = viewModel.form,
                            fieldState = viewModel.form.endTime,
                            formatter = ::time
                        ).Field()

                        CheckboxField(
                            modifier = Modifier.padding(bottom = 8.dp),
                            fieldState = viewModel.form.agreeWithTerms,
                            label = "I agree to Terms & Conditions",
                            form = viewModel.form
                        ).Field()
                    }
                }

                ButtonRow(nextClicked = {
                    viewModel.validate()
                })

            }
        }
    }
}

@Composable
fun ButtonRow(nextClicked: () -> Unit) {
    Row {
        Button(
            enabled = false,
            modifier = Modifier.weight(1f),
            onClick = {
                // nothing
            }
        ) {
            Text("Back")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            modifier = Modifier.weight(1f),
            onClick = {
                nextClicked()
            }
        ) {
            Text("Validate")
        }
    }
}

@Preview
@Composable
fun FormPagePreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        FormPage()
    }
}