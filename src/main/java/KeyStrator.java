package main.java;

import jm.constants.ProgramChanges;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.music.tools.Mod;
import jm.util.Play;
import jm.util.View;

import java.text.StringCharacterIterator;
import java.util.Vector;

import static jm.constants.Durations.*;
import static jm.constants.Pitches.*;
import static jm.constants.ProgramChanges.AAH;
import static jm.constants.ProgramChanges.OOH;
import static jm.constants.ProgramChanges.PIANO;

public class KeyStrator {


    public static void main(String[] args) {
        //makeMusic();
        /////////////////////////
        loco();
//        CharSequence hola = "Como suele ser habitual  \n"
//                + "Me siento dispuesto a escribir  \n"
//                + "En el papel de Juan Sebastián  \n"
//                + "Insane sun, Einstein song ";
//
//
//        main.groovy.KeyMapper mapper = new main.groovy.AlphabeticMapper();
//        mapper.setStartNote(D4);
//        mapper.setScale(Scales.MAJOR_SCALE);
//
//        //Play.midi(new Note(C4, QN));
//
//        Phrase melody = new Phrase("Original");
//        int i = 0;
//        //double[] pattern0 = {1.0, 0.5, 0.5, 1.5, 0.5};
//        double[] pattern0 = {EIGHTH_NOTE,EIGHTH_NOTE,EIGHTH_NOTE,EIGHTH_NOTE,EIGHTH_NOTE,QUARTER_NOTE,EIGHTH_NOTE,EIGHTH_NOTE,MINIM};
//        while (i < hola.length()) {
//            char c = hola.charAt(i++);
//            int nNoteNumber = mapper.getNote(c);
//            //Play.midi(new Note(nNoteNumber, QN));
////            Note n = new Note(nNoteNumber, QUAVER);
//            Note n = new Note(nNoteNumber, pattern0[i%pattern0.length]);
//            melody.addNote(n);
//            System.out.println("char:" + c + " i:" + i + "note number:" + nNoteNumber);
//        }
//
//        Score melScore = new Score(200);
//        Part melPart = new Part();
//        melPart.addPhrase(melody);
//        melScore.addPart(melPart);
//        Play.midi(melScore);


        //View.show(melScore);
    }

    private static void loco(){
        Phrase melody = new Phrase("Original");
        int[] notes1 = {C5,B4,C5,D5,E5,D5,C5,D5,C5,A4};
        double[] rythm1 = {C,Q,C,C,C,C,Q,Q,Q,C};
        int[] notes2 = {CS5,B4,CS5,D5,CS5,B4,G4,A4,BF4};
        double[] rythm2 = {C,Q,C,C,C,CD,Q,Q,C};
        Phrase mainMelody = new Phrase();
        mainMelody.addNoteList(notes1,rythm1);
        mainMelody.addNoteList(notes1,rythm1);
        mainMelody.addNoteList(notes2,rythm2);

        Phrase echo = mainMelody.copy();
        echo.setStartTime(1.0);

        Score melScore = new Score(120);
        Part melPart = new Part("Piano",PIANO);
        Part echoPart  = new Part("echo",ProgramChanges.ATMOSPHERE);
        melPart.addPhrase(mainMelody);
        echoPart.add(echo);
        melScore.addPart(melPart);
        melScore.add(echoPart);
        Play.midi(melScore);
        //View.show(melScore);
    }
    public static void accents(Phrase phrase) {
        double beatCounter = phrase.getStartTime();
        Vector v = phrase.getNoteList();
        for(int i=0;i<v.size();i++) {
            Note n = (Note)v.elementAt(i);
            if (beatCounter%12 == 0.0 || beatCounter%12 == 3.5 ||
                    beatCounter%12 == 7.0 || beatCounter%12 == 10.5)
                n.setDynamic(127);
            beatCounter += n.getRhythmValue();
        }
    }
    private static void chorale(){
// set up the pitches and durations of each line
        int[] pitchSop = {C5,G4,E4,D4,G4,A4,C4,D4,E4,D4,F4,E4,A4,G4,E4};
        double[] rhythmSop = {C,C,DC,Q,C,C,C,C,C,C,C,C,M,C,C};
        int[] pitchAlto = {E4,D4,C4,A3,B3,C4,D4,C4,D4,CS4};
        double[] rhythmAlto = {C,C,SB,C,C,C,C,SB,C,C};
        int[] pitchTenor = {G3,C4,B3,A3,G3,F3,E3,G3,G3,C4,B3,A3,B3,A3};
        double[] rhythmTenor = {M,C,Q,Q,C,C,C,C,M,C,C,M,C,C};
        int[] pitchBass = {C3,B2,A2,G2,F2,E2,F2,A2,G2,C3,B2,A2,G2,F2,F3,E3,A3};
        double[] rhythmBass = {C,C,C,Q,Q,C,C,C,C,C,C,C,C,C,C,C,C};

        //create the jMusic phrase objects
        Phrase soprano = new Phrase();
        Phrase alto = new Phrase();
        Phrase tenor = new Phrase();
        Phrase bass = new Phrase();

        // add the notes to each phrase
        soprano.addNoteList(pitchSop, rhythmSop);
        alto.addNoteList(pitchAlto, rhythmAlto);
        tenor.addNoteList(pitchTenor, rhythmTenor);
        bass.addNoteList(pitchBass, rhythmBass);

        // create jMusic parts
        Part s = new Part("Soprano", OOH, 1);
        Part a = new Part("Alto", AAH, 2);
        Part t = new Part("Tenor", OOH, 3);
        Part b = new Part("Bass", AAH, 4);

        // add the phrases to the parts
        s.addPhrase(soprano);
        a.addPhrase(alto);
        t.addPhrase(tenor);
        b.addPhrase(bass);

        //create a score
        Score score = new Score("Chorale");

        //add the parts to the score
        score.addPart(s);
        score.addPart(a);
        score.addPart(t);
        score.addPart(b);

        Play.midi(score);
    }

    private static void makeMusic() {
//        CharSequence hola = "mambrú se fue a la guerra chiribin chiribin chin chin\n"
//                + "mambrú se fue a la guerra y ya no volverá ajaja ajaja\n";
        //the passage from which the melody will be generated
        String passage = "Como suele ser habitual  \n"
                + "Me siento dispuesto a escribir  \n"
                + "En el papel de Juan Sebastián  \n"
                + "Insane sun, Einstein song ";
        Score score = new Score("wordMusic", 180);
        Part piano = new Part("Piano", ProgramChanges.PIANO, 0);
        Phrase phrase = new Phrase(0.0);
        Note nextNote = new Note();

        //change all the letters in the passage variable to
        //Upper Case to remove case sensitivity
        passage = passage.toUpperCase();

        //assign a StringCharacterIterator to the passage
        StringCharacterIterator iter = new StringCharacterIterator(passage);

        //the variable used to count the number of non-vowels
        //(ie consonants and symbols) between each vowel
        //this number is sent to setNoteLength() every time
        //a new note is made
        int nonVowelCounter = 0;

        //this variable is set each new note is created, and
        //sets the Pitch of the new note (this is chosen at
        //random from the 3 or 4 options allowed for that vowel
        //in d'Arezzo's lookup chart)
        int notePitch;

        //the variable used to make the random pitch selection
        int randNum;

        //the main iteration loop which goes through passage
        //and makes the notes these notes are then added to
        //the Phrase phrase
        for(int i=0; i<passage.length(); i++) {

            iter.setIndex(i);
            char nextChar = iter.current();
            System.out.println("CHAR:"+ i + ": " + nextChar);

            switch (nextChar) {
                case 'A':

                    randNum = ((int)(java.lang.Math.random()*4));

                    if (randNum == 0){
                        notePitch = G2;}
                    else if (randNum == 1){
                        notePitch = E3;}
                    else if (randNum == 2){
                        notePitch = C4;}
                    else {
                        notePitch = a4;}

                    nextNote = new Note(notePitch,setNoteLength(
                            nonVowelCounter));
                    phrase.addNote(nextNote);
                    nonVowelCounter = 0;
                    break;

                case 'E':

                    randNum = ((int)(java.lang.Math.random()*3));

                    if (randNum == 0){
                        notePitch = A2;}
                    else if (randNum == 1){
                        notePitch = F3;}
                    else {
                        notePitch = D4;}

                    nextNote = new Note(notePitch,setNoteLength(
                            nonVowelCounter));
                    phrase.addNote(nextNote);
                    nonVowelCounter = 0;
                    break;

                case 'I':

                    randNum = ((int)(java.lang.Math.random()*3));

                    if (randNum == 0){
                        notePitch = B2;}
                    else if (randNum == 1){
                        notePitch = G3;}
                    else {
                        notePitch = E4;}

                    nextNote = new Note(notePitch,setNoteLength(
                            nonVowelCounter));
                    phrase.addNote(nextNote);
                    nonVowelCounter = 0;
                    break;

                case 'O':

                    randNum = ((int)(java.lang.Math.random()*3));

                    if (randNum == 0){
                        notePitch = C3;}
                    else if (randNum == 1){
                        notePitch = A3;}
                    else {
                        notePitch = F4;}

                    nextNote = new Note(notePitch,setNoteLength(
                            nonVowelCounter));
                    phrase.addNote(nextNote);
                    nonVowelCounter = 0;
                    break;

                case 'U':

                    randNum = ((int)(java.lang.Math.random()*3));

                    if (randNum == 0){
                        notePitch = D3;}
                    else if (randNum == 1){
                        notePitch = B3;}
                    else {
                        notePitch = G4;}

                    nextNote = new Note(notePitch,setNoteLength(
                            nonVowelCounter));
                    phrase.addNote(nextNote);
                    nonVowelCounter = 0;
                    break;

                //for all other letters (ie all the consonants)
                //and syllables, no notes are created
                default:
                    //add 1 to the consonant counter
                    nonVowelCounter++;
                    break;
            }
        }

        piano.addPhrase(phrase);
        //View.print(piano);

        //add part (instrument) to the score
        score.addPart(piano);
        Play.midi(score);
    }

    //an internal method which decides on the length of each note
    //depending on the number of consonants (as well as spaces
    //and other symbols) between vowels
    static double setNoteLength(int counter){

        double noteLength;
        switch (counter) {
            case (0):
                noteLength = Q;
                break;
            case (1):
                noteLength = Q;
                break;
            case (2):
                noteLength = C;
                break;
            case (3):
                noteLength = C;
                break;
            case (4):
                noteLength = M;
                break;
            default: //(ie more than 4 consonants)
                noteLength = MD;
                break;
        }
        return noteLength;
    }
}
