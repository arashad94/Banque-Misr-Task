package com.banquemisr.designsystem

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import com.banquemisr.designsystem.BMText.HeadingL

private val ICON_SIZE = 24.dp
private val ICON_PADDING = 16.dp
private val END_MARGIN = ICON_PADDING + ICON_SIZE

@Composable
fun HeaderBar(
    title: String,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        constraintSet = decoupleConstraints(),
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            colorFilter = ColorFilter.tint(ColorPalette.black),
            contentDescription = "",
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp)
                .clickable { onBackClicked() }
        )
        HeadingL(
            text = title.uppercase(),
            textModifier = BMTextModifier(
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.layoutId("title")
            )
        )
    }
}

private fun decoupleConstraints(): ConstraintSet {
    return ConstraintSet {
        val arrowIcon = createRefFor("arrow")
        val title = createRefFor("title")

        constrain(arrowIcon) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
        }

        constrain(title) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(arrowIcon.end)
            end.linkTo(parent.end, END_MARGIN)
            width = Dimension.fillToConstraints
        }
    }
}
