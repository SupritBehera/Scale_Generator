
import javax.sound.midi.*;
public class NewScaleGenerator {
	public static void main (String args []) {
		NewScaleGenerator sObj = new NewScaleGenerator();
		int rootNote = Integer.parseInt(args[0]);
		int scaleType = Integer.parseInt(args[1]);
		int instrument = Integer.parseInt(args[2]);
		int numberOfNotes = sObj.getNumberOfNotes(scaleType); // Store the number of notes in the required scale/
		if (numberOfNotes == -1) System.err.println("Wrong Scale Input"); //getNumberOfNotes(int) returns -1 if input scale type number does not match.
		else sObj.run(rootNote,scaleType,numberOfNotes,instrument);
	}
	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) { // Function to generate a note given the attributes associated to it.
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage(); 
			a.setMessage(comd, chan, one, two);// comd (command) for example 144 for note on, 128 for note off, 172 for changing instrument, etc.; chan(channel)
			event = new MidiEvent(a,tick);     // one and two depend on the command type. Tick is the beat number when the note is played.
		} catch (Exception ex) {ex.printStackTrace();}
		return event;
	}
	public void run(int rootNote, int scaleType, int numberOfNotes, int instrument) {
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			
			Sequence seq = new Sequence(Sequence.PPQ,4); // A sequencer runs numerous sequences.
			Track track = seq.createTrack(); // A Sequence is made up of numerous tracks.
			
			int middleC = 60; // Note number of Middle C note on a standard 88 key piano.
			int note = middleC + rootNote; // Because rootNote is just the relative position of the note to Middle C.
			int limit = (numberOfNotes - 1) * 4; // (numberOfNotes - 1) because looping starts from i (counter variable) = 0. It is multiplied by 4 i increases by 4 each iteration.
			int finalNote = -1; // Initialization. finalNote stores the value of the last note in a scale.
			int beat = -1;// Initialization
			
			for (int i = 0; i <= limit; i+=4) { // Loop for playing the ascending scale.
				beat = beat + 4; 
				note = callCorrectMethod(i, note, scaleType); 
				track.add(makeEvent(192,1,instrument,0,beat)); // Set instrument
				track.add(makeEvent(144,1,note,100,beat)); // Play the note.     
				track.add(makeEvent(128,1,note,100,beat+2)); // Stop playing the note.
				if (i == limit) 
					finalNote = note; // In the last iteration, note will contain the value of the last note of the scale.
			}
			
			int startingNote = finalNote; // In descending scale, the first note played is the last note of the scale.
			//System.out.println(startingNote); // For testing purposes.
			for (int j = limit; j >= 4; j-=4) { // Loop for playing the descending scale
				beat = beat + 4;
				startingNote = startingNote + (startingNote - callCorrectMethod(j, startingNote, scaleType)); // callCorrectMethod returns (note + some int). In descending scale, the value of the next note should be lower than the previous one.
				
				track.add(makeEvent(192,1,instrument,0,beat)); // Set instrument
				track.add(makeEvent(144,1,startingNote,100,beat)); // Play the note.  
				track.add(makeEvent(128,1,startingNote,100,beat+2)); // Stop playing the note.
			}
			
			sequencer.setSequence(seq);
			sequencer.start();
			sequencer.setTempoInBPM(120); // Set the BPM (Beats Per Minute) while determines the speed with which it is played. 
		} catch(Exception ex) {ex.printStackTrace();};
	}
	
	public int getNumberOfNotes(int scaleType) { // returns the number of notes in the specific input scale type
		if (scaleType <= 10) 
			return 8;
		if (scaleType == 11 || scaleType == 14) 
			return 7;
		if (scaleType == 12 || scaleType == 13) 
			return 6;
		if (scaleType == 15 || scaleType == 16) 
			return 9;
		return -1; 
	}
	
	public static int callCorrectMethod(int count,int note, int scaleType) { // call the required function according to the scale type input.
		if (scaleType == 1) 
			note = getNextMajorNote(count,note);
		if (scaleType == 2) 
			note = getNextNaturalMinorNote(count, note);
		if (scaleType == 3) 
			note = getNextHarmonicMinorNote(count, note);
		if (scaleType == 4) 
			note = getNextIonianNote(count, note);
		if (scaleType == 5) 
			note = getNextDorianNote(count, note);
		if (scaleType == 6) 
			note = getNextPhrygianNote(count, note);
		if (scaleType == 7) 
			note = getNextLydianNote(count, note);
		if (scaleType == 8) 
			note = getNextMixolydianNote(count, note);
		if (scaleType == 9) 
			note = getNextAeolianNote(count, note);
		if (scaleType == 10) 
			note = getNextLocrianNote(count, note);
		if (scaleType == 11) 
			note = getNextBluesNote(count, note);
		if (scaleType == 12) 
			note = getNextMinorPentatonicNote(count, note);
		if (scaleType == 13) 
			note = getNextMajorPentatonicNote(count, note);
		if (scaleType == 14) 
			note = getNextWholeToneNote(count, note);
		if (scaleType == 15) 
			note = getNextWholeHalfDiminishedNote(count, note);
		if (scaleType == 16) 
			note = getNextHalfWholeDiminishedNote(count, note);
		return note;
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
		if (count == 1 || count == 3 || count == 4 ) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else if (count == 6) {
			return note + 3;
		}
		else {
			return note + 1;
		}
	}
	public static int getNextIonianNote(int count, int note) {
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
	public static int getNextPhrygianNote(int count, int note) {
		count = count/4;
		if (count == 2 || count == 3 || count == 4 || count == 6 || count == 7) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else {
			return note + 1;
		}
	}
	public static int getNextDorianNote(int count, int note) {
		count = count/4;
		if (count == 1 || count == 3 || count == 4 || count == 5 || count == 7) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else {
			return note + 1;
		}
	}
	public static int getNextLydianNote(int count, int note) {
		count = count/4;
		if (count == 1 || count == 2 || count == 3 || count == 5 || count == 6) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else {
			return note + 1;
		}
	}
	public static int getNextMixolydianNote(int count, int note) {
		count = count/4;
		if (count == 1 || count == 2 || count == 4 || count == 5 || count == 7) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else {
			return note + 1;
		}
	}
	public static int getNextAeolianNote(int count, int note) {
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
	public static int getNextLocrianNote(int count, int note) {
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
	public static int getNextWholeToneNote(int count, int note) {
		return note + 2;
	}
	public static int getNextWholeHalfDiminishedNote(int count, int note) {
		count = count/4;
		if (count == 1 || count == 3 || count == 5 || count == 7) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else {
			return note + 1;
		}
	}
	public static int getNextHalfWholeDiminishedNote(int count, int note) {
		count = count/4;
		if (count == 2 || count == 4 || count == 6 || count == 8) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else {
			return note + 1;
		}
	}
	
	
	public static int getNextBluesNote(int count, int note) {
		count = count/4;
		if (count == 2 || count == 6 ) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else if (count == 1 || count == 5) {
			return note + 3;
		}
		else {
			return note + 1;
		}
	}
	public static int getNextMajorPentatonicNote(int count, int note) {
		count = count/4;
		if (count == 1 || count == 2 || count == 4 ) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else if (count == 3 || count == 5) {
			return note + 3;
		}
		else {
			return note + 1;
		}
	}
	public static int getNextMinorPentatonicNote(int count, int note) {
		count = count/4;
		if (count == 2 || count == 3 || count == 5 ) {
			return (note + 2) ;
		}
		else if (count == 0)   {
			return note;
		}
		else if (count == 1 || count == 4) {
			return note + 3;
		}
		else {
			return note + 1;
		}
	}
	
}
		
		
	
	    

		
			
			
		
		
	
 