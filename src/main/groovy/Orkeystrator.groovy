package main.groovy

import javax.sound.midi.MidiChannel
import javax.sound.midi.MidiSystem
import javax.sound.midi.Sequencer
import javax.sound.midi.Synthesizer


class Orkeystrator {
    Sequencer sequencer
    String hola = 'Hola Mundo!'

    static void main(String[] args) {
        //        ShortMessage myMsg = new ShortMessage()
        //        // Play the note Middle C (60) moderately loud
        //        // (velocity = 93)on channel 4 (zero-based).
        //        myMsg.setMessage(ShortMessage.NOTE_ON, 4, 60, 93)
        //        Synthesizer synth = MidiSystem.getSynthesizer()
        //        Receiver synthRcvr = MidiSystem.getReceiver()

        Synthesizer synth = MidiSystem.getSynthesizer()
        synth.open()
        def nChannelNumber = 4
        def nNoteNumber = 60
        def nVelocity = 200
        MidiChannel[]   channels = synth.getChannels()
        MidiChannel channel = channels[nChannelNumber]
        println 'playing'
        channel.noteOn(nNoteNumber, nVelocity)

        /*
         *  Wait for the specified amount of time
         *  (the duration of the note).
         */
        try
        {
            Thread.sleep(2500)
        }
        catch (InterruptedException e)
        {
        }

        /* Close the synthesizer.
         */
        synth.close()

        println 'end'



    }
}
