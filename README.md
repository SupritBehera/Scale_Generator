# Scale_Generator

This program is an improvised version of NoteGenerator. 
In this program, the user inputs the root note, the type of scale (sixteen types of scales are supported) and the instrument.
Hence the notes of the scale are played in both ascending and descending order.
## Input List

### 1. Root Note of the Scale

Root note is nothing but the starting note of the scale.
For simplicity, the user just has to input the realitve position (number of half steps) 
of the note from the Middle C note (C note in the fifth octave).
Input the following values to get the note of your choice (All notes in the fifth octave)

Note         | Value to input
------------ | -------------
C | 0
C# | 1
D | 2
D# | 3
E | 4
F | 5
F# | 6
G | 7
G# | 8
A | 9
A# | 10
B | 11



To increase or decrease the octave from the default fifth octave, add (12 * number of octaves increased) 
or substract (12 * number of octaves decreased) respectively to the values in the list given above.

### 2. Scale Type

Input the number in the list associated to the required scale type. Sixteen scale types are supported.

Scale        | Value to input
------------ | -------------
Major| 1
Natural Minor | 2
Harmonic Minor|3
Ionian|4
Dorian|5
Phrygian|6
Lydian|7
Mixolydian|8
Aeolian|9
Locrian|10
Blues|11
Minor Pentatonic|12
Major Pentatonic|13
Whole Tone|14
Whole Half Diminished|15
Half Whole Diminished|16

### 3. Instrument
The third number to be input is the instrument number. Enter any number betwween 1 to 128. 


For starters, 1 is the Acoustic Grand Piano; 13 is the Marimba;  20 is the Church Organ; 25 is the Acoustic Guitar; 41 is the Violin;
57 is the Trumpet; 74 is the Flute; 105 is the Sitar and so on.


The complete list can be found here : https://www.midi.org/specifications/item/gm-level-1-sound-set

## Changelogs
Changes in NewScaleGenerator :

     1. Added a total of 16 scale types.
     2. Both Ascending and Descending scales will be played.
     3. Everything has been commented for better understanding.
     4. Code is further refined with many new methods.
     5. A critical bug has been squashed due which  the time difference between the playing of two
         consecutive kept increasing as more notes were played        
