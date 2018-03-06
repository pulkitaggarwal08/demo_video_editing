package com.demo_video_editing.pulkit.video_effects.activities;

import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

import com.demo_video_editing.pulkit.R;
import com.demo_video_editing.pulkit.video_effects.effects.AutoFixEffect;
import com.demo_video_editing.pulkit.video_effects.effects.BlackAndWhiteEffect;
import com.demo_video_editing.pulkit.video_effects.effects.BlurEffect;
import com.demo_video_editing.pulkit.video_effects.effects.BrightnessEffect;
import com.demo_video_editing.pulkit.video_effects.effects.ContrastEffect;
import com.demo_video_editing.pulkit.video_effects.effects.CrossProcessEffect;
import com.demo_video_editing.pulkit.video_effects.effects.DocumentaryEffect;
import com.demo_video_editing.pulkit.video_effects.effects.DuotoneEffect;
import com.demo_video_editing.pulkit.video_effects.effects.FillLightEffect;
import com.demo_video_editing.pulkit.video_effects.effects.GammaEffect;
import com.demo_video_editing.pulkit.video_effects.effects.GrainEffect;
import com.demo_video_editing.pulkit.video_effects.effects.GreyScaleEffect;
import com.demo_video_editing.pulkit.video_effects.effects.HueEffect;
import com.demo_video_editing.pulkit.video_effects.effects.InvertColorsEffect;
import com.demo_video_editing.pulkit.video_effects.effects.LamoishEffect;
import com.demo_video_editing.pulkit.video_effects.effects.NoEffect;
import com.demo_video_editing.pulkit.video_effects.effects.PosterizeEffect;
import com.demo_video_editing.pulkit.video_effects.effects.SaturationEffect;
import com.demo_video_editing.pulkit.video_effects.effects.SepiaEffect;
import com.demo_video_editing.pulkit.video_effects.effects.SharpnessEffect;
import com.demo_video_editing.pulkit.video_effects.effects.TemperatureEffect;
import com.demo_video_editing.pulkit.video_effects.effects.TintEffect;
import com.demo_video_editing.pulkit.video_effects.effects.VignetteEffect;
import com.demo_video_editing.pulkit.video_effects.view.VideoSurfaceView;

import java.io.File;
import java.io.IOException;

public class VideoEffectsActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {

    private static final String TAG = VideoEffectsActivity.class.getName();

    private Resources resources;
    private VideoSurfaceView videoSurfaceView = null;
    private MediaPlayer mediaPlayer = null;

    private Button btn_reset, btn_blackAndWhiteEffect, btn_CrossProcessEffect, btn_DocumentaryEffect, btn_DuotoneEffect,
            btn_GreyScaleEffect, btn_InvertColorsEffect, btn_LamoishEffect, btn_PosterizeEffect, btn_SepiaEffect, btn_TintEffect;
    private SeekBar seekbar_saturation_effect, seekbar_autofixeffect, seekbar_BrightnessEffect, seekbar_ContrastEffect,
            seekbar_FillLightEffect, seekbar_GammaEffect, seekbar_GrainEffect, seekbar_HueEffect, seekbar_SharpnessEffect,
            seekbar_TemperatureEffect, seekbar_VignetteEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_effects);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findIds();
        init();
    }

    private void findIds() {

        videoSurfaceView = findViewById(R.id.mVideoSurfaceView);

        seekbar_autofixeffect = findViewById(R.id.seekbar_autofixeffect);
        seekbar_BrightnessEffect = findViewById(R.id.seekbar_BrightnessEffect);
        seekbar_ContrastEffect = findViewById(R.id.seekbar_ContrastEffect);
        seekbar_FillLightEffect = findViewById(R.id.seekbar_FillLightEffect);
        seekbar_GammaEffect = findViewById(R.id.seekbar_GammaEffect);
        seekbar_GrainEffect = findViewById(R.id.seekbar_GrainEffect);
        seekbar_HueEffect = findViewById(R.id.seekbar_HueEffect);
        seekbar_saturation_effect = findViewById(R.id.seekbar_saturation_effect);
        seekbar_SharpnessEffect = findViewById(R.id.seekbar_SharpnessEffect);
        seekbar_TemperatureEffect = findViewById(R.id.seekbar_TemperatureEffect);
        seekbar_VignetteEffect = findViewById(R.id.seekbar_VignetteEffect);

        btn_reset = findViewById(R.id.btn_reset);
        btn_blackAndWhiteEffect = findViewById(R.id.btn_blackAndWhiteEffect);
        btn_CrossProcessEffect = findViewById(R.id.btn_CrossProcessEffect);
        btn_DocumentaryEffect = findViewById(R.id.btn_DocumentaryEffect);
        btn_DuotoneEffect = findViewById(R.id.btn_DuotoneEffect);
        btn_GreyScaleEffect = findViewById(R.id.btn_GreyScaleEffect);
        btn_InvertColorsEffect = findViewById(R.id.btn_InvertColorsEffect);
        btn_LamoishEffect = findViewById(R.id.btn_LamoishEffect);
        btn_PosterizeEffect = findViewById(R.id.btn_PosterizeEffect);
        btn_SepiaEffect = findViewById(R.id.btn_SepiaEffect);
        btn_TintEffect = findViewById(R.id.btn_TintEffect);

    }

    private void init() {

        resources = getResources();
        mediaPlayer = new MediaPlayer();

        // Load video file from SD Card
//        loadFileFromGallery();

        // Load video file from Assets directory
        loadFileFromAssets();
        setListners();

        videoSurfaceView.init(mediaPlayer, new NoEffect());

    }

    private void setListners() {

        btn_reset.setOnClickListener(this);
        btn_blackAndWhiteEffect.setOnClickListener(this);
        btn_CrossProcessEffect.setOnClickListener(this);
        btn_DocumentaryEffect.setOnClickListener(this);
        btn_DuotoneEffect.setOnClickListener(this);
        btn_GreyScaleEffect.setOnClickListener(this);
        btn_InvertColorsEffect.setOnClickListener(this);
        btn_LamoishEffect.setOnClickListener(this);
        btn_PosterizeEffect.setOnClickListener(this);
        btn_SepiaEffect.setOnClickListener(this);
        btn_TintEffect.setOnClickListener(this);

        seekbar_autofixeffect.setOnSeekBarChangeListener(this);
        seekbar_BrightnessEffect.setOnSeekBarChangeListener(this);
        seekbar_ContrastEffect.setOnSeekBarChangeListener(this);
        seekbar_FillLightEffect.setOnSeekBarChangeListener(this);
        seekbar_GammaEffect.setOnSeekBarChangeListener(this);
        seekbar_GrainEffect.setOnSeekBarChangeListener(this);
        seekbar_HueEffect.setOnSeekBarChangeListener(this);
        seekbar_saturation_effect.setOnSeekBarChangeListener(this);
        seekbar_SharpnessEffect.setOnSeekBarChangeListener(this);
        seekbar_TemperatureEffect.setOnSeekBarChangeListener(this);
        seekbar_VignetteEffect.setOnSeekBarChangeListener(this);

    }

    private void loadFileFromGallery() {

        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(directory, "sample.mp4");
        try {
            mediaPlayer.setDataSource(file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFileFromAssets() {

        try {
            AssetFileDescriptor assetFileDescriptor = getAssets().openFd("sample.mp4");
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage(), e);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        videoSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mediaPlayer.stop();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_reset) {
            videoSurfaceView.init(mediaPlayer, new NoEffect());
            videoSurfaceView.onResume();

            seekbar_autofixeffect.setProgress(0);
            seekbar_BrightnessEffect.setProgress(0);
            seekbar_ContrastEffect.setProgress(0);
            seekbar_FillLightEffect.setProgress(0);
            seekbar_GammaEffect.setProgress(0);
            seekbar_GrainEffect.setProgress(0);
            seekbar_HueEffect.setProgress(0);
            seekbar_saturation_effect.setProgress(0);
            seekbar_SharpnessEffect.setProgress(0);
            seekbar_TemperatureEffect.setProgress(0);
            seekbar_VignetteEffect.setProgress(0);

        } else if (view.getId() == R.id.btn_blackAndWhiteEffect) {
            videoSurfaceView.init(mediaPlayer, new BlackAndWhiteEffect());
            videoSurfaceView.onResume();

        } else if (view.getId() == R.id.btn_CrossProcessEffect) {
            videoSurfaceView.init(mediaPlayer, new CrossProcessEffect());
            videoSurfaceView.onResume();

        } else if (view.getId() == R.id.btn_DocumentaryEffect) {
            videoSurfaceView.init(mediaPlayer, new DocumentaryEffect());
            videoSurfaceView.onResume();

        } else if (view.getId() == R.id.btn_DuotoneEffect) {
            videoSurfaceView.init(mediaPlayer, new DuotoneEffect(Color.parseColor("#3498DB"), Color.YELLOW));
            videoSurfaceView.onResume();

        } else if (view.getId() == R.id.btn_GreyScaleEffect) {
            videoSurfaceView.init(mediaPlayer, new GreyScaleEffect());
            videoSurfaceView.onResume();

        } else if (view.getId() == R.id.btn_InvertColorsEffect) {
            videoSurfaceView.init(mediaPlayer, new InvertColorsEffect());
            videoSurfaceView.onResume();

        } else if (view.getId() == R.id.btn_LamoishEffect) {
            videoSurfaceView.init(mediaPlayer, new LamoishEffect());
            videoSurfaceView.onResume();

        } else if (view.getId() == R.id.btn_PosterizeEffect) {
            videoSurfaceView.init(mediaPlayer, new PosterizeEffect());
            videoSurfaceView.onResume();

        } else if (view.getId() == R.id.btn_SepiaEffect) {
            videoSurfaceView.init(mediaPlayer, new SepiaEffect());
            videoSurfaceView.onResume();

        } else if (view.getId() == R.id.btn_TintEffect) {
            videoSurfaceView.init(mediaPlayer, new TintEffect(Color.parseColor("#3498DB")));
            videoSurfaceView.onResume();

        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (seekBar.getId() == R.id.seekbar_autofixeffect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new AutoFixEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        } else if (seekBar.getId() == R.id.seekbar_BrightnessEffect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new BrightnessEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        } else if (seekBar.getId() == R.id.seekbar_ContrastEffect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new ContrastEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        } else if (seekBar.getId() == R.id.seekbar_FillLightEffect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new FillLightEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        } else if (seekBar.getId() == R.id.seekbar_GammaEffect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new GammaEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        } else if (seekBar.getId() == R.id.seekbar_GrainEffect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new GrainEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        } else if (seekBar.getId() == R.id.seekbar_HueEffect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new HueEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        } else if (seekBar.getId() == R.id.seekbar_saturation_effect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new SaturationEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        } else if (seekBar.getId() == R.id.seekbar_SharpnessEffect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new SharpnessEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        } else if (seekBar.getId() == R.id.seekbar_TemperatureEffect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new TemperatureEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        } else if (seekBar.getId() == R.id.seekbar_VignetteEffect) {
            String videoProgress = String.valueOf(seekBar.getProgress());
            videoSurfaceView.init(mediaPlayer, new VignetteEffect(Float.parseFloat(videoProgress + "f")));
            videoSurfaceView.onResume();

        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}
