package com.demo_video_editing.pulkit.video_effects.effects;

import android.opengl.GLSurfaceView;

import com.demo_video_editing.pulkit.video_effects.interfaces.ShaderInterface;

/**
 * Displays the normal video without any effect.
 *
 */
public class NoEffect implements ShaderInterface {
    /**
     * Initialize
     */
    public NoEffect() {
    }

    @Override
    public String getShader(GLSurfaceView mGlSurfaceView) {

        String shader = "#extension GL_OES_EGL_image_external : require\n"
                + "precision mediump float;\n"
                + "varying vec2 vTextureCoord;\n"
                + "uniform samplerExternalOES sTexture;\n" + "void main() {\n"
                + "  gl_FragColor = texture2D(sTexture, vTextureCoord);\n"
                + "}\n";

        return shader;

    }
}