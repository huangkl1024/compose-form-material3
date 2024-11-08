package com.github.huangkl1024.composeform.material3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.github.huangkl1024.composeform.material3.ui.theme.ComposeFormMaterial3Theme

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
//                    FormPage()
                }
            }
        }
    }
}

//@Composable
//fun FormPage() {
//    val viewModel = viewModel<MainViewModel>()
//
//    Scaffold { innerPadding ->
//        Box(modifier = Modifier.padding(innerPadding)) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                Row(
//                    modifier = Modifier
//                        .weight(1f)
//                        .verticalScroll(rememberScrollState())
//                ) {
//
//                    Column {
////                        TextField(
////                            modifier = Modifier.padding(bottom = 8.dp),
////                            label = "Name",
////                            form = viewModel.form,
////                            fieldState = viewModel.form.name,
////                            changed = {
////                                // log the name to show tat changed is called
////                                Log.d("Form", "Name changed: $it")
////                                // clear countries (for no reason - just to show that options list is now mutable)
////                                viewModel.form.country.options = mutableListOf()
////                            }
////                        ).Field()
//
//                        TextField(
//                            modifier = Modifier.padding(bottom = 8.dp),
//                            label = "Last Name",
//                            form = viewModel.form,
//                            fieldState = viewModel.form.lastName,
//                            isEnabled = false,
//                        ).Field()
//
//                        TextField(
//                            modifier = Modifier.padding(bottom = 8.dp),
//                            label = "E-Mail",
//                            form = viewModel.form,
//                            fieldState = viewModel.form.email,
//                            keyboardType = KeyboardType.Email
//                        ).Field()
//
////                        PasswordField(
////                            modifier = Modifier.padding(bottom = 8.dp),
////                            label = "Password",
////                            form = viewModel.form,
////                            fieldState = viewModel.form.password
////                        ).Field()
//
////                        PasswordField(
////                            modifier = Modifier.padding(bottom = 8.dp),
////                            label = "Password Confirm",
////                            form = viewModel.form,
////                            fieldState = viewModel.form.passwordConfirm
////                        ).Field()
//
//                        PickerField(
//                            modifier = Modifier.padding(bottom = 8.dp),
//                            label = "Country",
//                            form = viewModel.form,
//                            fieldState = viewModel.form.country
//                        ).Field()
//
//                        PickerField(
//                            modifier = Modifier.padding(bottom = 8.dp),
//                            label = "Country Not searchable",
//                            form = viewModel.form,
//                            fieldState = viewModel.form.countryNotSearchable,
//                            isSearchable = false
//                        ).Field()
//
//                        DateField(
//                            modifier = Modifier.padding(bottom = 8.dp),
//                            label = "Start Date",
//                            form = viewModel.form,
//                            fieldState = viewModel.form.startDate,
//                            formatter = ::dateShort
//                        ).Field()
//
////                        DateField(
////                            modifier = Modifier.padding(bottom = 8.dp),
////                            label = "End Date",
////                            form = viewModel.form,
////                            fieldState = viewModel.form.endDate,
////                            themeResId = R.style.customDatePickerStyle,
////                            formatter = ::dateLong
////                        ).Field()
//
//                        CheckboxField(
//                            modifier = Modifier.padding(bottom = 8.dp),
//                            fieldState = viewModel.form.agreeWithTerms,
//                            label = "I agree to Terms & Conditions",
//                            form = viewModel.form
//                        ).Field()
//                    }
//                }
//
//                ButtonRow(nextClicked = {
//                    viewModel.validate()
//                })
//
//            }
//        }
//    }
//}
//
//@Composable
//fun ButtonRow(nextClicked: () -> Unit) {
//    Row {
//        Button(
//            enabled = false,
//            modifier = Modifier.weight(1f),
//            onClick = {
//                // nothing
//            }
//        ) {
//            Text("Back")
//        }
//        Spacer(modifier = Modifier.width(16.dp))
//        Button(
//            modifier = Modifier.weight(1f),
//            onClick = {
//                nextClicked()
//            }
//        ) {
//            Text("Validate")
//        }
//    }
//}

//@Preview
//@Composable
//fun FormPagePreview() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        FormPage()
//    }
//}