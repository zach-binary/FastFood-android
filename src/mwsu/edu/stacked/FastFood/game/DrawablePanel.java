package mwsu.edu.stacked.FastFood.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class DrawablePanel 
	extends SurfaceView 
	implements SurfaceHolder.Callback, ISurface {
	
	private AnimationThread thread;
	
	public DrawablePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		
		this.thread = new AnimationThread(getHolder(), this);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	/** What happens when the panel is first created */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	/** When the panel is closed */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	    boolean retry = true;
	    thread.setRunning(false);
	    while (retry) {
	        try {
	            thread.join();
	            retry = false;
	        } catch (InterruptedException e) {
	            // we will try it again and again...
	        }
	    }			
	}
}
