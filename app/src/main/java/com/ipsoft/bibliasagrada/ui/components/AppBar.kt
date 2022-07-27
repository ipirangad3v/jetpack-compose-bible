package com.ipsoft.bibliasagrada.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ipsoft.bibliasagrada.R

@Composable
fun AppBar(
    title: String = stringResource(id = R.string.app_name),
    icon: ImageVector? = null,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    "",
                    Modifier
                        .padding(horizontal = 12.dp)
                        .clickable { onBackClick.invoke() }
                )
            }
        },
        title = { Text(text = title) },
        backgroundColor = Color.White
    )


}