package com.example.inr_management_md3.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.inr_management_md3.R

/**
 *  Write comment dialog as reusable stateless component
 */

@Composable
fun WriteCommentDialog(
    title: String,
    dialogState: MutableState<Boolean>,
    text: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    maxLines: Int,
    cancelButton: () -> Unit,
    okButton: () -> Unit
) {
    Dialog(
        onDismissRequest = { dialogState.value = false },
        content = {
            WriteCommentDialogContent(
                title = title,
                okButton = okButton,
                cancelButton = cancelButton,
                content = {
                    CommentBodyContent(
                        text = text,
                        onValueChange = onValueChange,
                        modifier = modifier,
                        maxLines = maxLines,
                        label = label
                    )
                }
            )
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteCommentDialogContent(
    title: String,
    content: @Composable () -> Unit,
    cancelButton: () -> Unit,
    okButton: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxHeight(0.85f)
            .fillMaxWidth(1f),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Title(title)
            AddCommentBody(content)
            CommentBottomButtons(
                okButton = okButton,
                cancelButton = cancelButton
            )
        }
    }
}

@Composable
fun CommentBottomButtons(
    cancelButton: () -> Unit,
    okButton: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(20.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(
            onClick = cancelButton,
            modifier = Modifier
                .width(100.dp)
                .padding(end = 5.dp)
        ) {
            Text(
                text = stringResource(R.string.cancel_text_button),
                fontSize = 16.sp
            )
        }
        TextButton(
            onClick = okButton
        ) {
            Text(
                text = stringResource(R.string.ok_text_button),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun AddCommentBody(content: @Composable () -> Unit) {
    Box(modifier = Modifier.padding(20.dp)) {
        content()
    }
}

@Composable
fun Title(title: String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
fun CommentBodyContent(
    text: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    maxLines: Int
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxSize(),
        maxLines = maxLines,
        label = label
    )
}
