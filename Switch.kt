import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

var checked by remember { mutableStateOf(true) }
// Icon isn't focusable, no need for content description
val icon: (@Composable () -> Unit)? = if (checked) {
    {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = null,
            modifier = Modifier.size(SwitchDefaults.IconSize),
        )
    }
} else {
    null
}

Switch(
    modifier = Modifier.semantics { contentDescription = "Demo with icon" },
    checked = checked,
    onCheckedChange = { checked = it },
    thumbContent = icon
)
