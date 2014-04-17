package org.emmerich.rrt.view;

import org.emmerich.rrt.data.RepetitionType;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class RepetitionView extends TextView {

	private RepetitionType type;
	
	public RepetitionView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RepetitionView(Context context) {
		super(context);
	}

	public RepetitionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
		
	@Override
	public void setText(CharSequence text, BufferType type) {
		String formattedText = (String) text;
		formattedText += " reps";
//		
//		switch(this.type) {
//			case COUNT:
//				formattedText = text + " reps";
//				break;
//			case TIME:
//				formattedText = text + " secs";
//				break;
//			case TO_FAIL:
//				formattedText = "To Fail";
//				break;
//			default:
//				throw new IllegalArgumentException("Cannot format repetition: " + text);
//		}
		
		super.setText(formattedText, type);
	}
	
	public void setRepetitionType(RepetitionType type) {
		this.type = type;
	}

}
