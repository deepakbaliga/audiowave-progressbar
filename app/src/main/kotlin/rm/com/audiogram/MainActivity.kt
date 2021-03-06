package rm.com.audiogram

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.Button
import rm.com.audiowave.AudioWaveView

class MainActivity : AppCompatActivity() {

  val wave by lazy { findViewById(R.id.wave) as AudioWaveView }
  val play by lazy { findViewById(R.id.play) as Button }
  val list by lazy { findViewById(R.id.list) as Button }

  val progressAnim: ObjectAnimator by lazy {
    ObjectAnimator.ofFloat(wave, "progress", 0F, 100F).apply {
      interpolator = LinearInterpolator()
      duration = 1000
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    play.setOnClickListener {
      inflateWave()
    }

    list.setOnClickListener {
      startActivity(Intent(this, RecordListActivity::class.java))
    }

    wave.onStopTracking = {
      Log.e("wave", "Progress set: $it")
    }
  }

  fun inflateWave() {
    wave.setRawData(assets.open("End.mp3").readBytes()) { progressAnim.start() }
  }
}