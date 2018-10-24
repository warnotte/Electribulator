package orw.warnotte.elecribulator.PresetManager;

import org.warnotte.waxaudiomiditools.CControlers.ControlElement;

public class ControlValue {
	ControlElement ce;
	float value = 63;
	
	ControlValue(ControlElement ce)
	{
		this.ce=ce;
		if (this.ce.getLabel().contains("OSC TYPE"))
			value=0;
	
	}

	public synchronized float getValue() {
		return value;
	}

	public synchronized void setValue(float value) {
		this.value = value;
	}

	public int getCC() {
		return ce.getCC();
	}

	public String getLabel() {
		return ce.getLabel();
	}
	
}
