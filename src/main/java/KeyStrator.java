package main.java;

import main.groovy.AlphabeticMapper;
import main.groovy.KeyMapper;

import javax.sound.midi.*;

public class KeyStrator {
    public static void main(String[] args) {
        KeyMapper mapper = new AlphabeticMapper();
        CharSequence hola = "puto el que lee, puto el que lee,puto el que lee, puto el que lee p"
                + "Los talleres  \n"
                + "Garages del oficio  \n"
                + "Gajes del ofidio  \n"
                + "Calles del anfibio  \n"
                + "Como el videjueguito de la rana  \n"
                + "  \n"
                + "El sapo está en su juego \n"
                + "El venado está en su salsa  \n"
                + "El pavo está en el saco  \n"
                + "Disculpe  tiene fuego? p";
        Synthesizer synth = null;
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
        } catch (MidiUnavailableException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        int nChannelNumber = 4;
        int nNoteNumber = 60;
        int nVelocity = 100;
        MidiChannel[] channels = synth.getChannels();
        System.out.println("channels:"+channels.length);

        int instrument = 41;
        Sequence sequence = null;
        try {
            sequence = new Sequence(Sequence.PPQ, 1);
            Track track = sequence.createTrack();
            ShortMessage sm = new ShortMessage();
            sm.setMessage(ShortMessage.PROGRAM_CHANGE, 9, instrument, 0); //9 ==> is the channel 10.
            track.add(new MidiEvent(sm, 0));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        MidiChannel channel = channels[nChannelNumber];
        int i = 0;
        while (i < hola.length()) {
            char c = hola.charAt(i++);
            nNoteNumber = mapper.getNote(c);
            channel.noteOn(nNoteNumber, nVelocity);
            System.out.println("char:" + c + " i:" + i + "note number:" + nNoteNumber);
            /*
             * Wait for the specified amount of time
             * (the duration of the note).
             */
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
            }
        }
        /*
         * Close the synthesizer.
         */
        synth.close();
    }
}
