package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    static final String TAG = "OpenGLES20Activity";
    GLSurfaceView mGLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		// Створюємо і підключаємо компонент
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause(); // Зупиняємо потік малювання
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume(); // Старт потоку малювання
    }

	/**
     * Метод для компіляції OpenGL шейдеру
     * @param type - тип шейдера
     * @param shaderCode - текст шейдера
     * @return - повертає id для шейдера
     */
    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

	/**
     * Метод відладки OpenGL викликів
     * @param glOperation - назва OpenGL виклику для перевірки
     */
    public static void checkGlError(String glOperation) {
        int error; // одержання усіх помилок у циклі
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
			// створення виключення
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
}