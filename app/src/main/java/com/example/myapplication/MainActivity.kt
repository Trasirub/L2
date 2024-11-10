package com.example.imagegenerator

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageGeneratorApp()
        }
    }
}

@Composable
fun ImageGeneratorApp() {
    val imageUrls = listOf(
        "https://picsum.photos/400/250",
        "https://picsum.photos/400/251",
        "https://picsum.photos/400/252",
        "https://picsum.photos/400/253",
        "https://picsum.photos/400/254"
    )

    var currentImageUrl by remember { mutableStateOf(imageUrls[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Відображення зображення
        Image(
            painter = rememberImagePainter(currentImageUrl),
            contentDescription = "Random Image",
            modifier = Modifier
                .height(200.dp)
                .width(400.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // URL зображення
        Text(
            text = currentImageUrl,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current
        // Кнопки
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { openUrl(context, currentImageUrl) }) {
                Text("Перейти до картинки")
            }

            Button(onClick = {
                currentImageUrl = imageUrls[Random.nextInt(imageUrls.size)]
            }) {
                Text("Згенерувати нову картинку")
            }
        }
    }
}

// Функція для відкриття URL в браузері
fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        // Повідомлення користувачеві, якщо немає додатків для відкриття URL
        println("No application found to open the URL.")
    }
}
