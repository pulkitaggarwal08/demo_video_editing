package com.demo_video_editing.pulkit.video_editing.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.demo_video_editing.pulkit.R;
import com.demo_video_editing.pulkit.video_editing.video_trimmer.VideoTrimmer;
import com.demo_video_editing.pulkit.video_editing.video_trimmer.interfaces.OnVideoListener;
import com.demo_video_editing.pulkit.video_editing.video_trimmer.interfaces.OnTrimVideoListener;

import java.io.File;
import java.util.ArrayList;


public class TrimmerActivity extends AppCompatActivity implements OnTrimVideoListener, OnVideoListener {

    private ProgressDialog mProgressDialog;
    private VideoTrimmer mVideoTrimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trimmer);

        findIds();
        init();

    }

    private void findIds() {
        mVideoTrimmer = findViewById(R.id.timeLine);
    }

    private void init() {

        Intent extraIntent = getIntent();
        String path = "";

        if (extraIntent != null) {
            path = extraIntent.getStringExtra(VideoEditing.EXTRA_VIDEO_PATH);
        }

        //setting progressbar
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.trimming_progress));

        Uri videoUri = Uri.parse(path);

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        //use one of overloaded setDataSource() functions to set your data source
        retriever.setDataSource(this, videoUri);
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        int timeInMins = (((int) Long.parseLong(time)) / 1000) + 1000;

        if (mVideoTrimmer != null) {

            mVideoTrimmer.setMaxDuration(timeInMins);
            mVideoTrimmer.setOnTrimVideoListener(this);
            mVideoTrimmer.setOnK4LVideoListener(this);
            //mVideoTrimmer.setDestinationPath("/storage/emulated/0/DCIM/CameraCustom/");
            mVideoTrimmer.setVideoURI(Uri.parse(path));
            mVideoTrimmer.setVideoInformationVisibility(true);
        }
    }

    @Override
    public void onVideoPrepared() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TrimmerActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTrimStarted() {
        mProgressDialog.show();
    }

    @Override
    public void getResult(final Uri uri) {

        Log.d("TAG", uri.getPath());
        indexFile(uri.getPath());

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                mProgressDialog = new ProgressDialog(TrimmerActivity.this);
//                mProgressDialog.setMessage("Please wait while the video is being saved");
//                mProgressDialog.setTitle("Please wait");
//                mProgressDialog.setIndeterminate(true);
                mProgressDialog.show();
            }
        });
    }

    private void indexFile(String SAVEPATH) {
        //Create a new ArrayList and add the newly created video file path to it
        ArrayList<String> toBeScanned = new ArrayList<>();
        toBeScanned.add(SAVEPATH);
        String[] toBeScannedStr = new String[toBeScanned.size()];
        toBeScannedStr = toBeScanned.toArray(toBeScannedStr);

        //Request MediaScannerConnection to scan the new file and index it
        MediaScannerConnection.scanFile(this, toBeScannedStr, null, new MediaScannerConnection.OnScanCompletedListener() {

            @Override
            public void onScanCompleted(String path, Uri uri) {
                Log.i("TAG", "SCAN COMPLETED: " + path);
                mProgressDialog.cancel();
                setResult(VideoEditing.VIDEO_EDIT_RESULT_CODE);
                finish();
            }
        });
    }

    @Override
    public void cancelAction() {
//        mProgressDialog.cancel();
        mVideoTrimmer.destroy();
        finish();
    }

    @Override
    public void onError(final String message) {
        mProgressDialog.cancel();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TrimmerActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
