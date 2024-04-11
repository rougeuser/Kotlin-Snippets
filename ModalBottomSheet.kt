import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

var openBottomSheet by rememberSaveable { mutableStateOf(false) }
var skipPartiallyExpanded by remember { mutableStateOf(false) }
var edgeToEdgeEnabled by remember { mutableStateOf(false) }
val scope = rememberCoroutineScope()
val bottomSheetState = rememberModalBottomSheetState(
    skipPartiallyExpanded = skipPartiallyExpanded
)

// App content
Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.spacedBy(4.dp)
) {
    Row(
        Modifier.toggleable(
            value = skipPartiallyExpanded,
            role = Role.Checkbox,
            onValueChange = { checked -> skipPartiallyExpanded = checked }
        )
    ) {
        Checkbox(checked = skipPartiallyExpanded, onCheckedChange = null)
        Spacer(Modifier.width(16.dp))
        Text("Skip partially expanded State")
    }
    Row(
        Modifier.toggleable(
            value = edgeToEdgeEnabled,
            role = Role.Checkbox,
            onValueChange = { checked -> edgeToEdgeEnabled = checked }
        )
    ) {
        Checkbox(checked = edgeToEdgeEnabled, onCheckedChange = null)
        Spacer(Modifier.width(16.dp))
        Text("Toggle edge to edge enabled")
    }
    Button(
        onClick = { openBottomSheet = !openBottomSheet },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        Text(text = "Show Bottom Sheet")
    }
}

// Sheet content
if (openBottomSheet) {
    val windowInsets = if (edgeToEdgeEnabled)
        WindowInsets(0) else BottomSheetDefaults.windowInsets

    ModalBottomSheet(
        onDismissRequest = { openBottomSheet = false },
        sheetState = bottomSheetState,
        windowInsets = windowInsets
    ) {

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                // you must additionally handle intended state cleanup, if any.
                onClick = {
                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                        if (!bottomSheetState.isVisible) {
                            openBottomSheet = false
                        }
                    }
                }
            ) {
                Text("Hide Bottom Sheet")
            }
        }
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(horizontal = 16.dp),
            label = { Text("Text field") }
        )
        LazyColumn {
            items(25) {
                ListItem(
                    headlineContent = { Text("Item $it") },
                    leadingContent = {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Localized description"
                        )
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                    ),
                )
            }
        }
    }
}
