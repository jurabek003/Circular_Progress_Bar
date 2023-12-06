package uz.turgunboyevjurabek.circularprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.turgunboyevjurabek.circularprogressbar.ui.theme.CircularProgressBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressBar(percentage = 1f, number = 100)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CircularProgressBarTheme {

    }
}

@androidx.compose.runtime.Composable
fun CircularProgressBar(
    percentage:Float,
    number: Int,
    fonSize:TextUnit=28.sp,
    radius: Dp=50.dp,
    color:Color= Color.Green,
    strokeWith:Dp=8.dp,
    animDuration:Int=1000,
    animDelay:Int=0
) {
    var animationPlayed by remember{
        mutableStateOf(false)
    }
    val curPercentage= animateFloatAsState(
        targetValue =if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ), label = ""
    )
    LaunchedEffect(key1 = true){
        animationPlayed=true
    }
    
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius*2f)
    ){
        Canvas(  modifier = Modifier.size(radius*2f)){
            drawArc(
                color=color,
                -90f,
                360*curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWith.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text =(curPercentage.value*number).toInt().toString(),
            color= Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize =fonSize
            )
    }

    
}