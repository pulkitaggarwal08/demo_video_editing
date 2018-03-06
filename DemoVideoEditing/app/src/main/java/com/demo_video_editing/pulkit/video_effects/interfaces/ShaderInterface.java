package com.demo_video_editing.pulkit.video_effects.interfaces;

import android.opengl.GLSurfaceView;

/**
 * Created by pulkit on 2/1/18.
 */
/**
 * An interface that every effect must implement so that there is a common
 * getShader method that every effect class is force to override
 *
 */
public interface ShaderInterface {
    /**
     * Returns Shader code
     *
     * @param mGlSurfaceView
     *            send this for every shader but this will only be used when the
     *            shader needs it.
     * @return complete shader code in C
     */
    public String getShader(GLSurfaceView mGlSurfaceView);
}
