package id.arvigo.arvigobasecore.ui.feature.search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.ui.component.ProductItemCard
import id.arvigo.arvigobasecore.ui.feature.search.uistate.SearchUiState
import id.arvigo.arvigobasecore.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(
    navController: NavController,

) {
    val viewModel: SearchViewModel = getViewModel()
    SearchScreenContent(
        navController = navController,
        viewModel = viewModel,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreenContent(
    navController: NavController,
    viewModel: SearchViewModel,
) {

    val queryState = viewModel.queryState.value

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus() // Request focus when the screen is displayed
        keyboardController?.show() // Open the keyboard
    }


    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                          //  .height(64.dp)
                            .padding(vertical = 8.dp, horizontal = 12.dp)
                            .focusRequester(focusRequester) // Use the FocusRequester
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) {
                                    keyboardController?.show() // Open the keyboard when focused
                                }
                            }
                            .clickable { },
                        value = queryState.text,
                        onValueChange = {
                            viewModel.setQuery(it)
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    viewModel.searchProduct()
                                }
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = androidx.compose.ui.text.input.ImeAction.Search,
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                viewModel.searchProduct()
                            }
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary,
                        ),
                        textStyle = TextStyle(fontSize = 18.sp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                        )
                    }
                },
            )
        }
    ) {
        val response = viewModel.response.value
        if (queryState.error?.isNotEmpty() == true) {
            Text(
                text = queryState.error,
                style = TextStyle(color = Color.Red),
                modifier = Modifier.padding(it),
            )
        }

        when(response) {
            is SearchUiState.Success -> {

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(180.dp),
                    state = LazyGridState(),
                    contentPadding = PaddingValues(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .padding(it)
                ){
                    items(response.data){ data ->
                        val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                        ProductItemCard(
                                name = data.name ,
                                image = data.image,
                                brand = data.brand,
                                itemSize = itemSize,
                                onClick = {
                            navController.navigate(Screen.ProductDetail.createRoute(data.id))
                        } )
                    }
                }
                Log.d("SearchScreenContent", "SearchScreenContent: ${response.data}")
            }
            is SearchUiState.Failure -> {
                Text(text = response.error.message ?: "Unknown Error")
            }
            SearchUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
            SearchUiState.Empty -> {
                Text(text = "Empty Data")
            }
        }

    }
}