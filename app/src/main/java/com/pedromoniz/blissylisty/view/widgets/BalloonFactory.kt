package com.pedromoniz.blissylisty.view.widgets

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.pedromoniz.blissylisty.R
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon

class BalloonFactory : Balloon.Factory() {

    override fun create(context: Context, lifecycle: LifecycleOwner): Balloon {
        return createBalloon(context) {
            //setLayout(R.layout.layout_check_server_health_again)
            setArrowSize(10)
            setArrowPosition(0.5f)
            setWidthRatio(0.55f)
            setHeight(250)
            setCornerRadius(4f)
            setBalloonAnimation(BalloonAnimation.CIRCULAR)
            setLifecycleOwner(lifecycle)
        }
    }
}