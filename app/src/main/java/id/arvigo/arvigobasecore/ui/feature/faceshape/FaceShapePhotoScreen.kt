package id.arvigo.arvigobasecore.ui.feature.faceshape

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.PrimaryButton
import id.arvigo.arvigobasecore.ui.feature.faceshape.uistate.FaceshapeUiState
import id.arvigo.arvigobasecore.util.reduceFileImage
import id.arvigo.arvigobasecore.util.saveBitmapToFile
import id.arvigo.arvigobasecore.util.saveUriToFile
import id.arvigo.arvigobasecore.util.uriToFileConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.getViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaceShapePhotoScreen() {

    val viewModel: FaceShapeViewModel = getViewModel()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var visible by remember {
        mutableStateOf(false)
    }

    var cameraPermissionGranted by remember {
        mutableStateOf(false)
    }

    val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(), onResult = { isGranted ->
        if (isGranted) {
            Log.d("TAG" , "Permission $isGranted")
            cameraPermissionGranted = true
        }
    } )

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null)}
    var bitmap  by remember{ mutableStateOf<Bitmap?>(null)}
    var imgFile  by remember{ mutableStateOf<File?>(null)}
    val coroutineScope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()){ result ->
        if (result != null) {
            bitmap = result
            // Convert Bitmap to File
            imgFile = saveBitmapToFile(context, bitmap as Bitmap)
        }
    }

    val launcherGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){ result ->
        if (result != null) {
            imageUri = result
            // Convert Uri to File
            imgFile = saveUriToFile(context, imageUri as Uri)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {


        if (bitmap != null || imageUri != null) {

            imageUri?.let {
                bitmap = if(Build.VERSION.SDK_INT < 28){
                    MediaStore.Images.Media.getBitmap(context.contentResolver,it)
                }else {
                    val source = ImageDecoder.createSource(context.contentResolver,it)
                    ImageDecoder.decodeBitmap(source)
                }
            }
            Image(bitmap = bitmap?.asImageBitmap()!!, contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(320.dp)
                    .border(3.dp, Color.Gray, CircleShape)
                    .padding(10.dp)
                    .clip(
                        CircleShape
                    )
            )
            visible = true
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_face),
                contentDescription = "",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(320.dp)
                    .border(3.dp, Color.Gray, CircleShape)
                    .padding(10.dp)
                    .clip(
                        CircleShape
                    )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {
                if (!cameraPermissionGranted) {
                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
                } else {
                    launcher.launch()
                }
            }) {
                Text(text = "Dari Kamera")
            }
            Button(onClick = {
                if (!cameraPermissionGranted) {
                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
                } else {
                    launcherGallery.launch("image/*")
                }
            }) {
                Text(text = "Dari Galeri")
            }
        }

        Spacer(modifier = Modifier.padding(top = 48.dp))
        val response = viewModel.response.value
        if (visible) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        val file = reduceFileImage(imgFile as File)
                        Log.d("TAG", "File : $file")
                        viewModel.submitFaceShape(file)
                    }
                },
                shape = RoundedCornerShape(10),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(57.dp)
            ) {
                if (response is FaceshapeUiState.Loading) {
                    CircularProgressIndicator(
                        color = Color.White,
                    )
                } else {
                    Text(text = "Submit", style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontWeight = FontWeight.Bold))
                }
            }
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Catatan :", style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(text = "1. Pastikan wajah terlihat jelas")
                    Text(text = "2. Pastikan wajah tidak terhalang")
                    Text(text = "3. Pastikan wajah tidak terlalu dekat")
                }
            }
        }
        if(response is FaceshapeUiState.Success) {
            Text(text = response.data.result)
        }
        if(response is FaceshapeUiState.Failure) {
            Text(text = response.error.message.toString())
        }


    }

}