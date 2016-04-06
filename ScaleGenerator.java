
import javax.sound.midi.*;
public class ScaleGenerator {
	public static void main (String args []) {
		ScaleGenerator sObj = new ScaleGenerator();
		int rootNote = Integer.parseInt(args[0]);
		int scaleType = Integer.parseInt(args[1]);
		sObj.play(rootNote,scaleType);
	}
	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a,tick);
		} catch (Exception ex) {ex.printStackTrace();}
		return event;
	}
	public void play(int rootNote, int scaleType) {
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			
			Sequence seq = new Sequence(Sequence.PPQ,4);
			Track track = seq.createTrack();
			
			int middleC = 60;
			int note = middleC + rootNote;
			
			for (int i = 0; i <= 28; i+=4) {
				if (scaleType == 1) note = getNextMajorNote(i,note);
				if (scaleType == 2) note = getNextNaturalMinorNote(i, note);
				if (scaleType == 3) note = getNextHarmonicMinorNote(i, note);
				track.add(makeEvent(144,1,note,100,i));
				track.add(makeEvent(128,1,note,100,i+2));
			}
			
			sequencer.setSequence(seq);
			sequencer.start();
		} catch(Exception ex) {ex.printStackTrace();};
	}
	public static int getNextMajorNote(int count, int note) {
		count = count/4;
		if (count == 1 || count == 2 || count == 4 || count == 5 || count == 6) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else {
			return note + 1;
		}
	}
	public static int getNextNaturalMinorNote(int count, int note) {
		count = count/4;
		if (count == 1 || count == 3 || count == 4 || count == 6 || count == 7) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else {
			return note + 1;
		}
	}
	public static int getNextHarmonicMinorNote(int count, int note) {
		count = count/4;
		if (count == 1 || count == 3 || count == 4 || count == 6) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else {
			return note + 1;
		}
	}
		
		
	}
	    

		
			
			
		
		
	
 