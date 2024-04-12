import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

var sliderPosition by remember { mutableStateOf(0f) }
val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
Column(modifier = Modifier.padding(horizontal = 16.dp)) {
    Slider(
        modifier = Modifier.semantics { contentDescription = "Localized Description" },
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        valueRange = 0f..100f,
        interactionSource = interactionSource,
        onValueChangeFinished = {
            // launch some business logic update with the state you hold
            // viewModel.updateSelectedSliderValue(sliderPosition)
        },
        thumb = {
            Label(
                label = {
                    PlainTooltip(
                        modifier = Modifier
                            .requiredSize(45.dp, 25.dp)
                            .wrapContentWidth()
                    ) {
                        Text("%.2f".format(sliderPosition))
                    }
                },
                interactionSource = interactionSource
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Color.Red
                )
            }
        }
    )
}
